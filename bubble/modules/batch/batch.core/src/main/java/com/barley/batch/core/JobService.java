package com.barley.batch.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobExecutionNotRunningException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.batch.core.launch.NoSuchJobExecutionException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
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
	 * find job with job name
	 * 
	 * @param jobName
	 * @return cornjob (unique)
	 */
	public CornJob findJobByName(@NotNull String jobName) {
		final String queryByName = "select * from t_job jb where jb.job_name = ?";
		BeanPropertyRowMapper<CornJob> rowMapper = new BeanPropertyRowMapper<CornJob>(CornJob.class);
		List<CornJob> listJob = jdbctemplate.query(queryByName, new String[] { jobName }, rowMapper);
		if (listJob != null && listJob.size() > 1) {
			throw new JobException("No unique job name");
		}
		if (listJob == null || listJob.size() == 0) {
			return null;
		}
		return listJob.get(0);
	}

	/**
	 * 
	 * @return
	 */
	public List<CornJob> findJobs(final JobSearchVO searchvo) {
		return jdbctemplate.query(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				String sql = "select * from t_job job where 1=1 ";
				if (searchvo.getEnabled() != null) {
					if (searchvo.getEnabled()) {
						sql += "and job.JOB_STATUS = 'ENABLE'";
					} else {
						sql += "and job.JOB_STATUS = 'DISABLE'";
					}
				}
				if (searchvo.getDayend() != null) {
					if (searchvo.getDayend()) {
						sql += "and (job.DAYEND_INDI = 'Y' or job.job_net = 'Y')";
					} else {
						sql += "and job.DAYEND_INDI = 'N'";
					}
				}
				return con.prepareStatement(sql);
			}
		}, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
			}
		}, new ResultSetExtractor<List<CornJob>>() {
			@Override
			public List<CornJob> extractData(ResultSet rs) throws SQLException, DataAccessException {
				final BeanPropertyRowMapper<CornJob> rowMapper = new BeanPropertyRowMapper<CornJob>(CornJob.class);
				final List<CornJob> resultList = new ArrayList<CornJob>(50);
				while (rs.next()) {
					resultList.add(rowMapper.mapRow(rs, rs.getRow()));
				}
				return resultList;
			}
		});
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

	/**
	 * 系统所有JOB定义
	 * 
	 * @return
	 */
	public List<CornJob> findAllJob() {
		String sql = "select * from t_job";
		BeanPropertyRowMapper<CornJob> rowMapper = new BeanPropertyRowMapper<CornJob>(CornJob.class);
		return jdbctemplate.query(sql, rowMapper);
	}

	/**
	 * 系统激活的,可执行的JOB定义列表
	 * 
	 * @return
	 */
	public List<CornJob> findAllActiveJob() {
		String sql = "select * from t_job job where job.job_status = '" + JobStatus.ENABLE + "' and job.job_net = 'N'";
		BeanPropertyRowMapper<CornJob> rowMapper = new BeanPropertyRowMapper<CornJob>(CornJob.class);
		return jdbctemplate.query(sql, rowMapper);
	}

	/**
	 * disable job
	 * 
	 * @param jobName
	 */
	public void disableJob(final Long jobId) {
		final String sql = "update t_job jb set jb.job_status = '" + JobStatus.DISABLE + "' where jb.list_id = ?";
		jdbctemplate.execute(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				return con.prepareStatement(sql);
			}
		}, new PreparedStatementCallback<Object>() {
			@Override
			public Object doInPreparedStatement(PreparedStatement ps) throws SQLException, DataAccessException {
				ps.setLong(1, jobId);
				ps.execute();
				return null;
			}
		});
	}

	/**
	 * loading all enable timing jobs.
	 * 
	 * @return
	 */
	public List<CornJob> findAllTimingJob() {
		JobSearchVO searchVO = new JobSearchVO();
		searchVO.setDayend(false);
		searchVO.setEnabled(true);
		return findJobs(searchVO);
	}

	/**
	 * 关闭接受提交batch job
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
		} catch (NoSuchJobExecutionException e) {
			throw new JobException("stop job error, execution id is " + executionId, e);
		} catch (JobExecutionNotRunningException e2) {
			// ignore
		}
		JobExecution jobExecution = jobExplorer.getJobExecution(executionId);
		jobExecution.setEndTime(new Date());
		jobRepository.update(jobExecution);
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
			JobParametersBuilder builder = new JobParametersBuilder(parameters);
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
	@Autowired
	private JobRepository jobRepository;
	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	private ApplicationContext context;
	@Autowired
	private BatchRuntimeManager servRuntime;
	@Autowired
	private JobOperator jobOpertate;
	@Autowired
	private JobExplorer jobExplorer;

}
