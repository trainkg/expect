package com.barley.batch.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameter.ParameterType;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.repository.dao.AbstractJdbcBatchMetadataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

/**
 * spring batch execution query extension.
 * 
 * @author peculiar.1@163.com
 * @version $ID: SpringBatchQueryDao.java, V1.0.0 2020年11月16日 下午11:07:45 $
 */

@Slf4j
@Repository
public class JobBatchQueryDao extends AbstractJdbcBatchMetadataDao {

	@Autowired
	private JobExplorer jobExplorer;

	public JobBatchQueryDao(JdbcTemplate template) {
		setJdbcTemplate(template);
	}

	/**
	 * 标志, 拥有开始时间,但是无终止时间
	 */
	private static final String GET_ALL_RUNNING_EXECUTIONS = "SELECT E.JOB_EXECUTION_ID, E.START_TIME, E.END_TIME, E.STATUS, E.EXIT_CODE, E.EXIT_MESSAGE, E.CREATE_TIME, E.LAST_UPDATED, E.VERSION, "
			+ "E.JOB_INSTANCE_ID, E.JOB_CONFIGURATION_LOCATION from %PREFIX%JOB_EXECUTION E, %PREFIX%JOB_INSTANCE I where E.JOB_INSTANCE_ID=I.JOB_INSTANCE_ID and E.START_TIME is not NULL and E.END_TIME is NULL order by E.JOB_EXECUTION_ID desc";

	private static final String FIND_PARAMS_FROM_ID = "SELECT JOB_EXECUTION_ID, KEY_NAME, TYPE_CD, "
			+ "STRING_VAL, DATE_VAL, LONG_VAL, DOUBLE_VAL, IDENTIFYING from %PREFIX%JOB_EXECUTION_PARAMS where JOB_EXECUTION_ID = ?";

	/**
	 * 查找所有正在运行的job
	 * 
	 * @param jobName
	 * @return
	 */
	public Set<JobExecution> findAllRunningJobExecutions() {
		log.info("findAllRunningJobExecutions start");
		final Set<JobExecution> result = new HashSet<>();

		RowCallbackHandler handler = new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				JobExecutionRowMapper mapper = new JobExecutionRowMapper();
				result.add(mapper.mapRow(rs, 0));
			}
		};
		getJdbcTemplate().query(getQuery(GET_ALL_RUNNING_EXECUTIONS), handler);
		return result;
	}

	private final class JobExecutionRowMapper implements RowMapper<JobExecution> {

		private JobInstance jobInstance;

		private JobParameters jobParameters;

		public JobExecutionRowMapper() {
		}

		@Override
		public JobExecution mapRow(ResultSet rs, int rowNum) throws SQLException {
			Long id = rs.getLong(1);
			String jobConfigurationLocation = rs.getString(10);
			JobExecution jobExecution;
			jobInstance = jobExplorer.getJobInstance(rs.getLong(9));
			if (jobParameters == null) {
				jobParameters = getJobParameters(id);
			}

			jobExecution = new JobExecution(jobInstance, id, jobParameters, jobConfigurationLocation);
			jobExecution.setJobInstance(jobInstance);
			jobExecution.setStartTime(rs.getTimestamp(2));
			jobExecution.setEndTime(rs.getTimestamp(3));
			jobExecution.setStatus(BatchStatus.valueOf(rs.getString(4)));
			jobExecution.setExitStatus(new ExitStatus(rs.getString(5), rs.getString(6)));
			jobExecution.setCreateTime(rs.getTimestamp(7));
			jobExecution.setLastUpdated(rs.getTimestamp(8));
			jobExecution.setVersion(rs.getInt(9));
			return jobExecution;
		}

	}
	
	//copy from JdbcJobExecutionDao
	protected JobParameters getJobParameters(Long executionId) {
		final Map<String, JobParameter> map = new HashMap<>();
		RowCallbackHandler handler = new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				ParameterType type = ParameterType.valueOf(rs.getString(3));
				JobParameter value = null;

				if (type == ParameterType.STRING) {
					value = new JobParameter(rs.getString(4), rs.getString(8).equalsIgnoreCase("Y"));
				} else if (type == ParameterType.LONG) {
					value = new JobParameter(rs.getLong(6), rs.getString(8).equalsIgnoreCase("Y"));
				} else if (type == ParameterType.DOUBLE) {
					value = new JobParameter(rs.getDouble(7), rs.getString(8).equalsIgnoreCase("Y"));
				} else if (type == ParameterType.DATE) {
					value = new JobParameter(rs.getTimestamp(5), rs.getString(8).equalsIgnoreCase("Y"));
				}
				// No need to assert that value is not null because it's an enum
				map.put(rs.getString(2), value);
			}
		};

		getJdbcTemplate().query(getQuery(FIND_PARAMS_FROM_ID), new Object[] { executionId }, handler);

		return new JobParameters(map);
	}

}
