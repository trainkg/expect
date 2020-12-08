/**
 * 
 */
package com.barley.batch.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.matchers.GroupMatcher;
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
import org.springframework.web.bind.annotation.RestController;

import com.barley.batch.core.CornJob;
import com.barley.batch.core.JobException;
import com.barley.batch.core.JobService;
import com.barley.batch.query.GridResponse;
import com.barley.batch.query.JobDashboard;
import com.barley.batch.query.JobQueryService;
import com.barley.batch.query.QuartzJobDetail;
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
	public Response<Set<JobExecution>> batchJobRunning() {
		Set<JobExecution> data = servQuery.runingJobs();
		return new GridResponse<Set<JobExecution>>().setTotalCount(data.size()).result(data);
	}

	/**
	 * 查看当前quartz的定时任务列表
	 * 
	 * @return
	 */
	@RequestMapping("/qtz/run")
	public Response<List<QuartzJobDetail>> listQuartzJobs() {
		List<QuartzJobDetail> result = new ArrayList<QuartzJobDetail>();
		try {
			log.info("loading quartz job list , scheduler is {}", scheduler);
			for (String group : scheduler.getJobGroupNames()) {
				// enumerate each job in group
				for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.groupEquals(group))) {
					List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
					if (triggers != null && triggers.size() > 0) {
						for (Trigger trigger : triggers) {
							QuartzJobDetail detail = new QuartzJobDetail();
							JobDetail jobDetail = scheduler.getJobDetail(jobKey);
							detail.setJobDetail(jobDetail);
							detail.setTrigger(trigger);
							result.add(detail);
						}
					} else {
						QuartzJobDetail detail = new QuartzJobDetail();
						JobDetail jobDetail = scheduler.getJobDetail(jobKey);
						detail.setJobDetail(jobDetail);
						result.add(detail);
					}
				}
			}
		} catch (SchedulerException e) {
			throw new JobException("loading failed", e);
		}
		return new GridResponse<List<QuartzJobDetail>>().setTotalCount(result.size()).result(result);
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

	/**
	 * @description Job 基本信息面板
	 * @return
	 */
	@GetMapping("/dashboard")
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
	private Scheduler scheduler;
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
	@Autowired
	private JobService serviceJob;
	
}
