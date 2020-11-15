package com.barley.batch.query;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * Job 面板信息
 * 
 * @author peculiar.1@163.com
 * @version $ID: JobDashboard.java, V1.0.0 2020年11月7日 上午10:36:59 $
 */
public class JobDashboard {

	/**
	 * day end 是否正在运行
	 */
	@Getter
	@Setter
	private boolean dayEndRuning;

	@Setter
	@Getter
	private Date processDate;

	/**
	 * 是否支持提交job
	 */
	@Getter
	@Setter
	private boolean accpetSubmit;

}
