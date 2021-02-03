package com.barley.robot;

/**
 * classpath XML配置
 * 
 * @author peculiar.1@163.com
 * @version $ID: ClasspathXMLResover.java, V1.0.0 2021年2月3日 下午3:55:43 $
 */
public interface ClasspathXMLResover<T> extends XmlResolver<T> {
	
	/**
	 * 
	 * 	获取XML配置路径
	 * @return
	 */
	public String xmlPath();
	
}
