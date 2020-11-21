/**
 * 
 */
package com.barley.batch.web;

import java.util.Set;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.barley.batch.query.GridResponse;
import com.barley.batch.query.JobDashboard;
import com.barley.batch.query.JobQueryService;
import com.barley.batch.query.Response;

import lombok.extern.slf4j.Slf4j;

/**
 * 负责系统batch相关查询
 * 
 * @author peculiar.1@163.com
 * @version $ID: BatchController.java, V1.0.0 2020年10月14日 下午10:20:21 $
 */
@RestController
@RequestMapping("/batch")
@Slf4j
public class BatchQueryController {

	/**
	 * 加载系统中当前正在运行的batch列表
	 */
	@RequestMapping("/run")
	@ResponseBody
	public Response<Set<JobExecution>> batchJobRunning() {
		Set<JobExecution> data = servQuery.runingJobs();
		return new GridResponse<Set<JobExecution>>().setTotalCount(data.size()).result(data);
	}

	/**
	 * 
	 * 查看一个job的运行历史记录
	 * 
	 * @param jobId
	 */
	@RequestMapping("/qry/his")
	public void batchExecutionHistory(Long jobId) {

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
	private JobExplorer servExplorer;
	@Autowired
	private ApplicationContext context;
}
