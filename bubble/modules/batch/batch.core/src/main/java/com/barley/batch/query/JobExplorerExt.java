package com.barley.batch.query;

import java.util.Set;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.explore.JobExplorer;

/**
   查询扩展 
  @deprecated 没有意义, @EnableBatchProcessing 只会生成 JobExplorer接口的代理,
 * @author peculiar.1@163.com
 * @version $ID: JobExplorerExt.java, V1.0.0 2020年11月19日 下午10:08:12 $
 */
public interface JobExplorerExt extends JobExplorer {
	
	/**
	 * 
	  查找所有在运行的job任务
	 * @return
	 */
	public Set<JobExecution> findAllRunningJobExecutions();
}
