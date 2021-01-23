package com.barley.batch.jobs;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;

import com.barley.batch.jobs.timetask.PrinterTask;

/**
 * 负责定义系统batch
 * 
 * @author peculiar.1@163.com
 * @version $ID: JobConfiguration.java, V1.0.0 2020年11月27日 下午11:01:38 $
 */
public class JobConfiguration {
	public static final String QUARTZ_DETAIL_GROUP = "JOB_GROUP";
	public static final String QUARTZ_TRIGGER_GROUP_USER = "trg_group_basic";
	public static final String QUARTZ_TRIGGER_NAME = "TRG1";

	// job details
	@Bean
	public JobDetail myJobDetail() {
		JobDetail jobDetail = JobBuilder.newJob(PrinterTask.class).withIdentity("myJob1", QUARTZ_DETAIL_GROUP)
				.withDescription("testing print job")
				.storeDurably().build();
		return jobDetail;
	}

	// =================================
	// triggers
	// =================================
	@Bean
	public Trigger basicTrigger() {
		Trigger trigger = TriggerBuilder.newTrigger().forJob(myJobDetail())
				.withIdentity(QUARTZ_TRIGGER_NAME, QUARTZ_TRIGGER_GROUP_USER)
				.usingJobData(QUARTZ_TRIGGER_NAME, QUARTZ_TRIGGER_GROUP_USER).startNow()
				.withSchedule(CronScheduleBuilder.cronSchedule("0/30 * * * * ?")).build();
		return trigger;
	}

}
