package com.barley.batch.query;

import java.util.List;
import java.util.Set;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.barley.batch.BatchRuntimeManager;
import com.barley.batch.core.CornJob;
import com.barley.batch.core.dayend.DefaultDayendJobContext;

/**
 * query
 * 
 * @author peculiar.1@163.com
 * @version $ID: JobQueryService.java, V1.0.0 2020年11月7日 上午11:25:08 $
 */
@Service
@DependsOn({ "dayendJobContext" })
public class JobQueryService {

	/**
	 * view
	 * 
	 * @return
	 */
	public JobDashboard viewDashboard() {
		JobDashboard dashboard = new JobDashboard();
		dashboard.setDayEndRuning(dayendJobContext.isRunning());
		dashboard.setAccpetSubmit(runtime.isAccept());
		dashboard.setProcessDate(dayendJobContext.getProcessDate());
		return dashboard;
	}

	/**
	 * 
	   查看当前正在运行的job列表
	 * 
	 * @return
	 */
	public Set<JobExecution> runingJobs() {
		return daoBatchQuery.findAllRunningJobExecutions();
	}
	
	
	/**
	 查看当前正在运行的定时任务
	 * @return
	 */
	public List<CornJob> runingCronJobs() {
		
		return null;
	}
	
	
	/**
	 * 获取JOB执行记录
	 * 
	 * @return
	 */
	public List<CornJob> loadingJobRuntime() {
		String sql = "select * from t_job";
		BeanPropertyRowMapper<CornJob> rowMapper = new BeanPropertyRowMapper<CornJob>(CornJob.class);
		return jdbctemplate.query(sql, rowMapper);
	}
	
	
	@Autowired
	private JobRegistry jobRegistry;
	@Autowired
	private JobOperator jobOpertate;
	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	private JobExplorer jobExplorer;
	@Autowired
	private BatchRuntimeManager runtime;
	@Autowired
	private JdbcTemplate jdbctemplate;
	@Autowired
	private JobBatchQueryDao daoBatchQuery;
	@Autowired
	private DefaultDayendJobContext dayendJobContext;
}
