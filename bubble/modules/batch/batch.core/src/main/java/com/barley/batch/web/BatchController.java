/**
 * 
 */
package com.barley.batch.web;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.barley.batch.query.JobDashboard;
import com.barley.batch.query.JobQueryService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author peculiar.1@163.com
 * @version $ID: BatchController.java, V1.0.0 2020年10月14日 下午10:20:21 $
 */
@RestController
@RequestMapping("/batch")
@Slf4j
public class BatchController {

	@RequestMapping("/run")
	public void batchJobRun() {
		System.err.println(jobOpertate);
		System.out.println(123123123);
		System.err.println(context.getBean("importUserJob"));
		Job job = (Job) context.getBean("importUserJob");
		try {
			jobLauncher.run(job, new JobParameters());
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			e.printStackTrace();
		}
	}

	@GetMapping("/dashboard")
	@ResponseBody
	public JobDashboard dashboard() {
		System.out.println("batch server dashboard");
		System.out.println("JOB NAMES:");
		System.out.println(jobOpertate.getJobNames());

		log.info("application {}", context.getApplicationName());
		log.info("bean {}", context.getBean(BatchConfigurer.class));
		;
		log.info("bean {}", context.getBean(JobOperator.class));
		;
		log.info("bean {}", context.getBean("test job"));
		log.info("bean {}", jobRegistry.getJobNames());
		System.out.println(jobRegistry);
		/* log.info("bean {}",context.getBean("entityManagerFactory"));; */
		System.out.println(context.getBeansOfType(BatchConfigurer.class).values());

		log.info("bean {}", context.getBean(SchedulerFactoryBean.class));
		return servQuery.viewDashboard();
	}

	@Autowired
	private JobRegistry jobRegistry;
	@Autowired
	private JobOperator jobOpertate;
	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	private JobQueryService servQuery;
	@Autowired
	private ApplicationContext context;
}
