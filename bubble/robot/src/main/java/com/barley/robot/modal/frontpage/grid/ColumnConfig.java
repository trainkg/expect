package com.barley.robot.modal.frontpage.grid;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * table column 配置
 * 
 * @author peculiar.1@163.com
 * @version $ID: ColumnConfig.java, V1.0.0 2021年2月3日 下午2:02:19 $
 */
public class ColumnConfig {

	/**
	 * 	标题
	 */
	
	@Getter
	@Setter
	private String title;
	
	@Getter
	@Setter
	private boolean showInTable = true;
	
	
}
