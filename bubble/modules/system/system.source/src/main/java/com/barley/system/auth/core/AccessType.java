package com.barley.system.auth.core;

/**
 * 访问方式
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: AccessType.java, V1.0.0 2021年1月9日 上午10:33:05 $
 */
public enum AccessType {

	/**
	 * <p>
	 * 常规的页面流访问 ,在这种模式下面, 存在客户响应交互问题， 主要存在传统页面的流转控制
	 * </p>
	 */
	PAGEFLOW,

	/**
	 * <p>
	 * 基于RESTFUL风格的API访问模式
	 * </p>
	 */
	RESTFUL
}
