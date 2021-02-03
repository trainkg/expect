package com.barley.robot.modal.frontpage.grid;

import org.barley.web.utils.JsonMapper;

import lombok.Getter;
import lombok.Setter;


/**
 * 
 * table 信息
 * 
 * @author peculiar.1@163.com
 * @version $ID: TableWapper.java, V1.0.0 2021年2月3日 上午11:33:30 $
 */
public class TableWapper {
	
	/**
	 * 	JAVA 模型名称（用于controller路径中）
	 */
	@Setter
	@Getter
	private String beanName;
	
	@Setter
	@Getter
	private Table table;
	
	@Setter
	@Getter
	private String uri;

	public TableWapper(Table table, String beanName) {
		super();
		this.table = table;
		this.beanName = beanName;
	}
	
	/**
	 * 	获取column JSON串
	 *  JACKSON 无法输出单引号包裹对象
	 * @return
	 */
	public String getColumnJSON() {
		JsonMapper jsonMapper = JsonMapper.nonEmptyMapper();
		return jsonMapper.toJson(getTable().getColumns());
	}
}
