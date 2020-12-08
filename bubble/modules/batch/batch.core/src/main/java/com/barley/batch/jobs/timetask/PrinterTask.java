package com.barley.batch.jobs.timetask;

import javax.sql.DataSource;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import lombok.extern.slf4j.Slf4j;

/**
 * Corn 打印测试
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: PrinterTask.java, V1.0.0 2020年11月26日 下午8:47:26 $
 */
@Slf4j
public class PrinterTask extends QuartzJobBean{
	
	@Autowired
	private DataSource datasource;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		log.info("printer task start. {}", datasource);
	}
	
}
