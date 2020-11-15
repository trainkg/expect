package com.barley.batch.core.dayend;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.CronExpression;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.metrics.BatchMetrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.barley.batch.core.CornJob;
import com.barley.batch.core.JobException;
import com.barley.batch.core.JobSearchVO;
import com.barley.batch.core.JobService;
import com.barley.batch.core.JobStatus;
import com.barley.batch.core.NodeData;
import com.barley.batch.prepare.Node;
import com.barley.batch.prepare.TreeConvertion;

import lombok.extern.slf4j.Slf4j;

/**
 * Dayend job
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: DayendJob.java, V1.0.0 2020年11月7日 上午10:00:10 $
 */
@Slf4j
public class DayendJob extends QuartzJobBean {

	public static final String PARAM_KEY_DATE = "DAY_END_PROCESS_DATE";

	@Autowired
	@NonNull
	private JobLauncher launcher;

	@Autowired
	@NonNull
	private JobService jobService;

	// @Value("barley.dayend.errorForErrorExpression")
	private boolean errorForErrorExpression = Boolean.TRUE;

	@Autowired
	private DefaultDayendJobContext dayendJobContext;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		log.info("day end job context {}, status {}", dayendJobContext, dayendJobContext.isRunning());
		if (!dayendJobContext.isRunning()) {
			dayendJobContext.markStart();
		} else {
			throw new JobException("day end not end.");
		}

		Date processDate = getProcessDate(context);
		List<CornJob> jobs = extractDayEndJobs(processDate);

		if (jobs == null || jobs.isEmpty()) {
			log.info("jobs size is 0");
			dayendJobContext.markEnd();
			return;
		}

		jobs = filterJobList(jobs, processDate);

		Node<NodeData<Long>, Long> rootNote = new TreeConvertion().convertion(jobs);

		internalHanlder(rootNote);

		jobs.forEach(job -> {
			System.out.println(job.getJobName());
		});
	}

	private void internalHanlder(Node<NodeData<Long>, Long> rootNote) {
		dayendJobContext.setRootNote(rootNote);
		internalScheduler(rootNote.getChilds());
	}

	private void internalScheduler(List<Node<NodeData<Long>, Long>> childs) {
		if (childs == null) {
			return;
		}
		log.info("day end job context {}, status {}", dayendJobContext, dayendJobContext.isRunning());
		if (!dayendJobContext.isRunning()) {
			dayendJobContext.markStart();
		}

		List<Node<NodeData<Long>, Long>> subJobs = new ArrayList<Node<NodeData<Long>, Long>>();
		for (Node<NodeData<Long>, Long> node : childs) {
			CornJob job = (CornJob) node.getTarget();
			if (!job.isJobNet()) {
				dayendJobContext.addJobToQuene(job);
			} else {
				subJobs.add(node);
			}
		}

		for (CornJob cornJob : dayendJobContext.getJobQuene()) {
			Map<String, JobParameter> parameterMap = new HashMap<String, JobParameter>(3);
			parameterMap.put("process date", new JobParameter(dayendJobContext.getProcessDate()));
			JobParameters parameters = new JobParameters(parameterMap);
			jobService.submitJob(cornJob.getListId(), parameters, JobService.JOB_SUBMIT_TYPE_DAYEND);
		}

		Date startDate = new Date(System.currentTimeMillis());
		while (dayendJobContext.isRunning() && !dayendJobContext.queneIsEmptry()) {
			// waiting complete.
			// log.info("wating....");
		}
		Date endDate = new Date(System.currentTimeMillis());
		Duration jobExecutionDuration = BatchMetrics.calculateDuration(startDate, endDate);
		log.info("dayend run time waiting {}",(jobExecutionDuration == null ? "" : " in " + BatchMetrics.formatDuration(jobExecutionDuration)));
		for (Node<NodeData<Long>, Long> node : subJobs) {
			internalScheduler(node.getChilds());
		}

	}

	private Date getProcessDate(JobExecutionContext context) {
		long time = context.getJobDetail().getJobDataMap().getLong(PARAM_KEY_DATE);
		return new Date(time);
	}

	/**
	 * 查找所有参与day end 的Job和对应的目录
	 */
	public List<CornJob> extractDayEndJobs(Date date) {
		JobSearchVO searchvo = new JobSearchVO();
		searchvo.setDayend(true);
		searchvo.setJobNet(true);
		List<CornJob> list = jobService.findJobs(searchvo);
		return filterJobList(list, date);
	}

	/*
	 * filter job.
	 */
	private List<CornJob> filterJobList(List<CornJob> list, Date date) {
		if (list == null) {
			return null;
		}

		List<CornJob> _list = new ArrayList<CornJob>(list.size());
		for (CornJob cornJob : list) {
			if (cornJob.isJobNet()) {
				_list.add(cornJob);
				continue;
			} else if (JobStatus.DISABLE.equals(cornJob.getJobStatus())) {
				continue;
			}

			/*
			 * if (!cornJob.isDayEnd()) { continue; }
			 */

			if (!CronExpression.isValidExpression(cornJob.getJobFreq())) {
				if (errorForErrorExpression) {
					throw new JobException("illegel cron expression " + cornJob.getJobFreq());
				} else {
					log.warn("illegel cron expression {}", cornJob.getJobFreq());
				}
			}
			/*
			 * try { if (new CronExpression(cornJob.getJobFreq()).isSatisfiedBy(date)) {
			 * _list.add(cornJob); } } catch (ParseException e) { throw new JobException(e);
			 * }
			 */
			_list.add(cornJob);
		}
		return _list;
	}

}
