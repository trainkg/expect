package com.barley.batch.query;

import org.quartz.JobDetail;
import org.quartz.Trigger;

import lombok.Getter;
import lombok.Setter;

/**
 * 描述quartz job当前状态
 * 
 * @author peculiar.1@163.com
 * @version $ID: QuartzJobVO.java, V1.0.0 2020年11月26日 上午10:06:06 $
 */
public class QuartzJobDetail {
	
	@Getter
	@Setter
	private JobDetail jobDetail;
	
	@Getter
	@Setter
	private Trigger trigger;
	
	
}
