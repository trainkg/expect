package com.barley.batch.query;

import java.util.Set;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.explore.support.SimpleJobExplorer;
import org.springframework.batch.core.repository.dao.ExecutionContextDao;
import org.springframework.batch.core.repository.dao.JobExecutionDao;
import org.springframework.batch.core.repository.dao.JobInstanceDao;
import org.springframework.batch.core.repository.dao.StepExecutionDao;

/**
 *
 * @deprecated 没有意义, @EnableBatchProcessing 只会生成 JobExplorer接口的代理,
 * @author peculiar.1@163.com
 * @version $ID: ExtJobExplorer.java, V1.0.0 2020年11月19日 下午10:36:01 $
 */
@Deprecated
public class ExtJobExplorer extends SimpleJobExplorer implements JobExplorerExt {
	private JobBatchQueryDao daoBatchQuery;

	public ExtJobExplorer(JobInstanceDao jobInstanceDao, JobExecutionDao jobExecutionDao,
			StepExecutionDao stepExecutionDao, ExecutionContextDao ecDao, JobBatchQueryDao daoBatchQuery) {
		super(jobInstanceDao, jobExecutionDao, stepExecutionDao, ecDao);
		this.daoBatchQuery = daoBatchQuery;
	}

	@Override
	public Set<JobExecution> findAllRunningJobExecutions() {
		return daoBatchQuery.findAllRunningJobExecutions();
	}

}
