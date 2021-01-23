package com.barley.batch.core;

import lombok.Getter;
import lombok.Setter;

/**
 * Job search object.
 * 
 * @author peculiar.1@163.com
 * @version $ID: JobSearchVO.java, V1.0.0 2020年11月5日 下午8:43:14 $
 */
public class JobSearchVO {

	@Getter
	@Setter
	private Boolean dayend;

	@Getter
	@Setter
	private Boolean jobNet;

	@Getter
	@Setter
	private Boolean enabled;
}
