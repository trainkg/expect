package com.barley.robot.modal.frontpage.form;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * form 配置
 * 
 * JAXB add annotation at get method.
 * 
 * @author peculiar.1@163.com
 * @version $ID: FormConfig.java, V1.0.0 2021年2月3日 下午4:02:30 $
 */
public final class FormConfigWapper {

	@Getter
	@Setter
	private FormConfig formConfig;

	/**
	 * 
	 * 是否要分组显示
	 * 
	 * @return
	 */
	public boolean isHasGroup() {
		return formConfig.getGroups().size() > 0;
	}

	/**
	 * 
	 * 获取单个元素占位长度
	 * 
	 * @return
	 */
	public int getSingleFeildCols() {
		return 24 / formConfig.getFeildNum();
	}
}
