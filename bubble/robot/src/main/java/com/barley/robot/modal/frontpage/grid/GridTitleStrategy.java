package com.barley.robot.modal.frontpage.grid;

import org.mybatis.generator.api.IntrospectedColumn;

/**
 * 
 * 	获取table字段显示名称title方式
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: GridTitleStrategy.java, V1.0.0 2021年2月3日 上午11:24:13 $
 */
public interface GridTitleStrategy {
	
	public String getTitle(IntrospectedColumn column);
	
}
