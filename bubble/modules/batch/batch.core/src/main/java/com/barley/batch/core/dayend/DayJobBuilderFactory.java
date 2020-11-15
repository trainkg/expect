package com.barley.batch.core.dayend;

import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;

/**
 * 
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: DayJobBuilderFactory.java, V1.0.0 2020年11月7日 下午8:46:29 $
 */
public class DayJobBuilderFactory extends JobBuilderFactory {

	private JobRepository jobRepository;

	private DayEndJobExecutionListener listener;

	public DayJobBuilderFactory(JobRepository jobRepository) {
		super(jobRepository);
		this.jobRepository = jobRepository;
	}

	public DayJobBuilderFactory(JobRepository jobRepository, DayEndJobExecutionListener listener) {
		super(jobRepository);
		this.jobRepository = jobRepository;
		this.listener = listener;
	}

	public JobBuilder get(String name) {
		JobBuilder builder = new JobBuilder(name).repository(jobRepository);
		builder.listener(listener);
		return builder;
	}
}
