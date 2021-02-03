package com.barley.robot.modal.frontpage.grid;

import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;

import com.barley.robot.modal.AutomaticConstants;

import lombok.Setter;

/**
 * 
 * 
 * 	主键和统计字段不进入table列表
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: SimpleColumnConfigResolver.java, V1.0.0 2021年2月3日 下午2:13:18 $
 */
public class SimpleColumnConfigResolver implements ColumnConfigResolver {
	
	
	@Setter 
	private GridTitleStrategy titleStratey = new SplitTitleStrategy();
	
	@Override
	public ColumnConfig resolve(IntrospectedColumn column) {
		ColumnConfig config = new ColumnConfig();
		config.setTitle(titleStratey.getTitle(column));
		List<IntrospectedColumn> primarykey = column.getIntrospectedTable().getPrimaryKeyColumns();
		if(primarykey != null) {
			if(primarykey.contains(column)){
				config.setShowInTable(false);
			}	
		}
		if(AutomaticConstants.contains(column.getJavaProperty())) {
			config.setShowInTable(false);
		}
		
		return config;
	}

}
