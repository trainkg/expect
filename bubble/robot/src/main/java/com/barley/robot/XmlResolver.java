package com.barley.robot;

/**
 * 
 * XML 配置解析
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: XmlResovler.java, V1.0.0 2021年2月3日 下午3:52:50 $
 */
public interface XmlResolver<T> {
	
	/**
	 * 
	 * 	解析XML获取配置信息 
	 * @return
	 */
	T parseXml();
}
