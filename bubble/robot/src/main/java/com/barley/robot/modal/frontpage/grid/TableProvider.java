package com.barley.robot.modal.frontpage.grid;

import org.mybatis.generator.api.IntrospectedTable;

/**
 * 
 * 该接口负责从表定义中提取table信息
 * 
 * @author peculiar.1@163.com
 * @version $ID: TableProvider.java, V1.0.0 2021年2月9日 下午3:48:06 $
 */
public interface TableProvider {
	Table retriveTable(IntrospectedTable introspectedTable);
}
