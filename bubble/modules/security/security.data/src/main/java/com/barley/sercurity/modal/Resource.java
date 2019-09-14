package com.barley.sercurity.modal;

import lombok.Getter;
import lombok.Setter;

/**
 * Resurce
 * 
 * @author peculiar.1@163.com
 * @version $ID: Resource.java, V1.0.0 2019年5月11日 上午11:23:40 $
 */
public class Resource {
	
	@Getter @Setter
	private String id;

	/**
	 * 资源名称
	 */
	@Getter @Setter
	private String name;

	/**
	 * 资源编号
	 */
	@Getter @Setter
	private String code;

	@Getter @Setter
	private UserDict type;

}