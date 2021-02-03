package com.barley.robot.modal.frontpage.grid;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.IntrospectedColumn;

/**
 * 
 * 	将表定义注释中使用指定符号抽取出字段描述
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: SplitTitleStrategy.java, V1.0.0 2021年2月3日 上午11:26:17 $
 */
public class SplitTitleStrategy implements GridTitleStrategy{
	
	/**
	 * default 
	 */
	private char splitChar = '|';

	public SplitTitleStrategy() {
	}

	public SplitTitleStrategy(char split) {
		this.splitChar = split;
	}

	@Override
	public String getTitle(IntrospectedColumn column) {
		
		String remarks = column.getRemarks();
		
		
		
		if(StringUtils.isNotBlank(remarks)) {
			return StringUtils.split(remarks, splitChar)[0];	
		}
		
		return "";
	}
	
	
}
