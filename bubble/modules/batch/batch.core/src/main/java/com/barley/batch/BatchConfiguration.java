package com.barley.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.barley.batch.core.dayend.DayEndJobExecutionListener;
import com.barley.batch.core.dayend.DayJobBuilderFactory;
import com.barley.batch.core.dayend.DayendJobContext;
import com.barley.batch.core.dayend.DefaultDayendJobContext;

@Configuration("dayendJobConfiguration")
@EnableBatchProcessing
public class BatchConfiguration {

	/**
	 * User spring 默认的threadpooltaskexecuter, 控制spring batch job的调度（非 step）
	 * 
	 * @param executor
	 * @return
	 */
	@Bean
	public ExtBatchConfig extBatchConfigurer(TaskExecutor executor) {
		return new ExtBatchConfig(executor);
	}

	@Bean(name = "dayendJobContext")
	public DayendJobContext dayendJob(SchedulerFactoryBean schedulerFactoryBean) {
		DefaultDayendJobContext dayend = new DefaultDayendJobContext();
		dayend.setSchedulerFactoryBean(schedulerFactoryBean);
		return dayend;
	}

	@Bean
	public DayEndJobExecutionListener dayEndJobExecutionListener(DayendJobContext context) {
		return new DayEndJobExecutionListener(context);
	}

	@Bean
	public DayJobBuilderFactory dayEndJobBuilderFactory(JobRepository jobRepository,
			DayEndJobExecutionListener listener) {
		return new DayJobBuilderFactory(jobRepository, listener);
	}

	@Bean
	public BatchRuntimeManager batchRuntimeManager(DayendJobContext dayendJobContext) {
		return new BatchRuntimeManager(dayendJobContext);
	}

}
