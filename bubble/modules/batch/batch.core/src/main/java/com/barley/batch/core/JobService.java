package com.barley.batch.core;

import java.util.List;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobExecutionNotRunningException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.barley.batch.BatchRuntimeManager;

/**
 * Job 服务
 * 
 * @author peculiar.1@163.com
 * @version $ID: JobService.java, V1.0.0 2020年10月27日 下午10:18:20 $
 */
@Service(JobService.DEFAULT_BEAN_NAME)
public class JobService {

	public static final String DEFAULT_BEAN_NAME = "barly.jobService";
	public static final String JOB_SUBMIT_TYPE_DAYEND = "JOB_TYPE_DAYEND";
	public static final String JOB_SUBMIT_TYPE_ONLINE = "JOB_TYPE_ONLINE";
	public static final String JOB_PARAMETER_SUBMT_TYPE = "BATCH_SUBMIT_TYPE";
	
	@Autowired
	private JdbcTemplate jdbctemplate;

	/**
	 * 
	 * @return
	 */
	public List<CornJob> findJobs(JobSearchVO searchvo) {
		String sql = "select * from t_job";
		BeanPropertyRowMapper<CornJob> rowMapper = new BeanPropertyRowMapper<CornJob>(CornJob.class);
		return jdbctemplate.query(sql, rowMapper);
	}

	/**
	 * loading job
	 * 
	 * @param jobId
	 * @return
	 */
	public CornJob findJob(Long jobId) {
		String sql = "select * from t_job jb where jb.list_id = ?";
		BeanPropertyRowMapper<CornJob> rowMapper = new BeanPropertyRowMapper<CornJob>(CornJob.class);
		return jdbctemplate.queryForObject(sql, new Object[] { jobId }, rowMapper);
	}

	public List<CornJob> findAllJob() {
		String sql = "select * from t_job";
		BeanPropertyRowMapper<CornJob> rowMapper = new BeanPropertyRowMapper<CornJob>(CornJob.class);
		return jdbctemplate.query(sql, rowMapper);
	}

	/**
	   关闭接受提交batch job
	 */
	public void closeSubmit() {
		servRuntime.setAccept(false);
	}

	/**
	 * 开启接受提交batch job
	 */
	public void openSubmit() {
		servRuntime.setAccept(true);
	}

	/**
	 * 暂停一个 Job 执行
	 * 
	 * @param executionId
	 */
	public void stopJob(long executionId) {
		try {
			jobOpertate.stop(executionId);
		} catch (NoSuchJobExecutionException | JobExecutionNotRunningException e) {
			throw new JobException("stop job error, execution id is " + executionId, e);
		}
	}

	public void restart(long executionId) {
		try {
			jobOpertate.restart(executionId);
		} catch (JobInstanceAlreadyCompleteException | NoSuchJobException | JobRestartException
				| JobParametersInvalidException | NoSuchJobExecutionException e) {
			throw new JobException("restart job error, execution id is " + executionId, e);
		}
	}

	/**
	 * 提交一个batch
	 * 
	 * @param JobId
	 * @param parameters
	 * @return 同步方法
	 * @throws JobException
	 */
	public boolean submitJob(Long jobId, JobParameters parameters, String submitType) {

		checkRuntime(submitType);

		CornJob job = findJob(jobId);
		// check status
		if (JobStatus.ENABLE != job.getJobStatus()) {
			throw new JobException("Job not avaliable. " + job.getJobName());
		}

		org.springframework.batch.core.Job jobBean = (org.springframework.batch.core.Job) context
				.getBean(job.getJobName());
		try {
			JobParametersBuilder  builder = new JobParametersBuilder(parameters);
			builder.addString(JOB_PARAMETER_SUBMT_TYPE, submitType);
			jobLauncher.run(jobBean, builder.toJobParameters());
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			throw new JobException(e);
		}
		//
		return true;
	}

	/**
	 * 如果是手动方式的提交, 需要检查当前执行环境是否允许提交
	 */
	private void checkRuntime(String submitType) {
		if (JOB_SUBMIT_TYPE_ONLINE.equals(submitType)) {
			if (!servRuntime.isAccept()) {
				throw new JobException("not accept submit job.");
			}	
		}
		 
	}

	// use for modular = true
	// @Autowired
	// private JobRepository jobRepository;
	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	private ApplicationContext context;
	@Autowired
	private BatchRuntimeManager servRuntime;
	@Autowired
	private JobOperator jobOpertate;
}
