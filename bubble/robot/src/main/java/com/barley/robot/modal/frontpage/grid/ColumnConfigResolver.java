package com.barley.robot.modal.frontpage.grid;

import org.mybatis.generator.api.IntrospectedColumn;

/**
 * 
 * table column 配置解析
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: ColumnConfigResolver.java, V1.0.0 2021年2月3日 下午2:00:40 $
 */
public interface ColumnConfigResolver {
	
	public ColumnConfig resolve(IntrospectedColumn column);
}
