package com.barley.batch.core.dayend;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.DateBuilder.evenMinuteDate;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;
import java.util.concurrent.ConcurrentLinkedDeque;

import org.apache.commons.lang3.time.DateUtils;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.barley.batch.core.CornJob;
import com.barley.batch.core.JobException;
import com.barley.batch.core.NodeData;
import com.barley.batch.prepare.Node;

import jline.internal.Log;
import lombok.Getter;
import lombok.Setter;


import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author peculiar.1@163.com
 * @version $ID: DefaultDayendJob.java, V1.0.0 2020年11月5日 下午8:39:47 $
 */
@Slf4j
public class DefaultDayendJobContext implements DayendJobContext, InitializingBean, DisposableBean {

	private final static String DAYEND_JOB_GROUP = "baley.dayend";
	private final static String DAYEND_JOB_IDENTITY = "baley.dayend.execute.job";
	private final static String DAYEND_JOB_TRIGGER_IDENTITY = "baley.dayend.execute.trigger";

	public boolean completeSubmit = Boolean.FALSE;

	@Setter
	private SchedulerFactoryBean schedulerFactoryBean;

	private volatile boolean running = Boolean.FALSE;

	/**
	 * 
	 * 提交JOB时候，如果job的cron表达式定义错误，是否抛出异常
	 * 
	 */
	@Setter
	private boolean errorForErrorExpression = Boolean.TRUE;

	/**
	 * day end process date
	 */
	@Getter
	@Setter
	private volatile Date processDate = new java.util.Date();

	/**
	 * 队列大小
	 */
	private int queneSize = 10;

	/**
	 * 记录当前day end batch正在执行的队列
	 */
	private ConcurrentLinkedDeque<CornJob> jobQuene = new ConcurrentLinkedDeque<CornJob>();

	@Getter
	@Setter
	private Node<NodeData<Long>, Long> rootNote;

	private Object lockObj = new Object();

	@Override
	public boolean isRunning() {
		return running;
	}

	@Override
	public void executeDayend() {
		Log.info("execute day end job...");
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		JobDataMap dataMap = new JobDataMap();
		dataMap.putAsString(DayendJob.PARAM_KEY_DATE, processDate.getTime());
		JobDetail jobDetail = JobBuilder.newJob(DayendJob.class).setJobData(dataMap)
				.withIdentity(DAYEND_JOB_IDENTITY, DAYEND_JOB_GROUP).build();

		Date runTime = evenMinuteDate(new Date());

		Trigger trigger = newTrigger().withIdentity(DAYEND_JOB_TRIGGER_IDENTITY, DAYEND_JOB_GROUP).startAt(runTime)
				.build();

		/*
		 * CronTrigger trigger = newTrigger().withIdentity(DAYEND_JOB_TRIGGER_IDENTITY,
		 * DAYEND_JOB_GROUP) .withSchedule(cronSchedule("50 * * * * ?")).build();
		 */

		try {
			scheduler.scheduleJob(jobDetail, trigger);
			/*
			 * if (scheduler.isStarted()) {
			 * 
			 * } else { log.warn("quartz scheduler not start"); }
			 */
		} catch (SchedulerException e) {
			throw new JobException(e);
		}
	}

	@Override
	public void destroy() throws Exception {
		log.info("dan end job destroy");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		executeDayend();
	}

	private void resetDayend() {
		completeSubmit = Boolean.FALSE;
		markEnd();
		jobQuene.clear();
		rootNote = null;
	}

	/**
	 * 标记队列中batch已经完成
	 * 
	 * @param jobName
	 */
	public void completeJob(String jobName) {
		synchronized (lockObj) {
			for (CornJob cornJob : jobQuene) {
				if (cornJob.getJobName().equals(jobName)) {
					jobQuene.remove(cornJob);
					break;
				}
			}

			if (completeSubmit && jobQuene.isEmpty()) {
				resetDayend();
				processDate = DateUtils.addDays(processDate, 1);
			}
		}
	}

	public void addJobToQuene(CornJob job) {
		if (isRunning()) {
			jobQuene.add(job);
		} else {
			throw new JobException("day end batch not start.");
		}
	}

	@Override
	public void markStart() {
		this.running = Boolean.TRUE;
	}

	@Override
	public void markEnd() {
		this.running = Boolean.FALSE;
	}

	protected Boolean queneIsEmptry() {
		return jobQuene.isEmpty() ? true : false;
	}

	public ConcurrentLinkedDeque<CornJob> getJobQuene() {
		return jobQuene;
	}

	/**
	 * 标记所有DAY END JOB已经完成提交
	 */
	public void completeSubmit() {
		completeSubmit = true;
	}

}
