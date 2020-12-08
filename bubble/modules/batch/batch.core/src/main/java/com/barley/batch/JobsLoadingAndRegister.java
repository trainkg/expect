package com.barley.batch;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.DateBuilder.evenMinuteDate;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;
import java.util.List;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.barley.batch.core.CornJob;
import com.barley.batch.core.JobException;
import com.barley.batch.core.JobService;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JobsLoadingAndRegister implements InitializingBean {

	@Override
	public void afterPropertiesSet() throws Exception {
		register();
	}

	private void register() {
		List<CornJob> timingJobs = jobService.findAllTimingJob();
		for (CornJob cornJob : timingJobs) {
			log.info("add job {} , freqency {}", cornJob.getJobName(), cornJob.getJobFreq());
			addJobToScheduler(cornJob);
		}
	}

	private void addJobToScheduler(CornJob cornJob) {
		JobDataMap dataMap = new JobDataMap();
		JobDetail jobDetail = null;
		try {
			jobDetail = JobBuilder.newJob(Class.forName(cornJob.getJobClass()).asSubclass(QuartzJobBean.class))
					.setJobData(dataMap).withIdentity(cornJob.getJobName(), "JOB_GROUP_" + cornJob.getParentId())
					.withDescription(cornJob.getJobDesc()).storeDurably(true).build();
		} catch (ClassNotFoundException e1) {
			throw new JobException(e1);
		}

		Date runTime = evenMinuteDate(new Date());

		Trigger trigger = newTrigger().withIdentity(cornJob.getJobName(), "TRIGGER_GROUP_" + cornJob.getListId())
				.withSchedule(cronSchedule(cornJob.getJobFreq())).startAt(runTime).build();

		/*
		 * CronTrigger trigger = newTrigger().withIdentity(DAYEND_JOB_TRIGGER_IDENTITY,
		 * DAYEND_JOB_GROUP) .withSchedule(cronSchedule("50 * * * * ?")).build();
		 */

		try {
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException e) {
			throw new JobException(e);
		}
	}

	@Autowired
	private Scheduler scheduler;
	@Autowired
	private JobService jobService;
}
