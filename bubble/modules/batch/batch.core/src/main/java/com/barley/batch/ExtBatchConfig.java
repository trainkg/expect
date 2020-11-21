package com.barley.batch;

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.core.task.TaskExecutor;

/**
 * DefaultBatchConfigurer 负责初始化job相关服务对象
 *  
 * @EnableBatchProcessing 负责注册
 * 
 * @author peculiar.1@163.com
 * @version $ID: ExtBatchConfig.java, V1.0.0 2020年11月19日 下午10:17:34 $
 */
public class ExtBatchConfig extends DefaultBatchConfigurer {

	private TaskExecutor taskExecutor;

	public ExtBatchConfig(TaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}
	
	/**
	 设定启动JOB的时候指定的taskexecutor取代默认的同步执行器SyncTaskExecutor
	 */
	@Override
	protected JobLauncher createJobLauncher() throws Exception {
		SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
		jobLauncher.setJobRepository(getJobRepository());
		jobLauncher.setTaskExecutor(taskExecutor);
		jobLauncher.afterPropertiesSet();
		return jobLauncher;
	}
	/*
	 * protected JobExplorer createJobExplorer() throws Exception {
	 * JobExplorerFactoryBeanExt jobExplorerFactoryBean = new
	 * JobExplorerFactoryBeanExt();
	 * jobExplorerFactoryBean.setDataSource(this.dataSource);
	 * jobExplorerFactoryBean.setTablePrefix(properties.getTablePrefix());
	 * jobExplorerFactoryBean.afterPropertiesSet(); return
	 * jobExplorerFactoryBean.getObject(); }
	 */

}
