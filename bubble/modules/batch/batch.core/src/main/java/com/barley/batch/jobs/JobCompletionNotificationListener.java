package com.barley.batch.jobs;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {
	private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("!!! JOB FINISHED! Time to verify the results");
			log.info("JOB ID {}, Job Configuration name {} ", jobExecution.getJobId(), jobExecution.getJobConfigurationName());
			Collection<StepExecution> executions = jobExecution.getStepExecutions();
			for (StepExecution stepExecution : executions) {
				log.info("step name {}, Exceptions {} ", stepExecution.getStepName(), stepExecution.getFailureExceptions());
			}
			/*
			 * jdbcTemplate.query("SELECT first_name, last_name FROM people", (rs, row) ->
			 * new Person(rs.getString(1), rs.getString(2))) .forEach(person ->
			 * log.info("Found <" + person + "> in the database."));
			 */
		}
	}
}
