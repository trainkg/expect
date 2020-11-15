package com.barley.batch.core;

import lombok.Getter;
import lombok.Setter;

/**
 * Job 执行参数
 * 
 * @author peculiar.1@163.com
 * @version $ID: JobParam.java, V1.0.0 2020年11月4日 下午9:41:31 $
 */
public class JobParam {
	
	@Getter
	@Setter
	private String paramName;
	
	@Getter
	@Setter
	private String paramType;
	
}
