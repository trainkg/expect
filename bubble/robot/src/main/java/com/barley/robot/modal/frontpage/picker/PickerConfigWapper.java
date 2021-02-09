package com.barley.robot.modal.frontpage.picker;

import com.barley.robot.modal.frontpage.form.FormConfig;
import com.barley.robot.modal.frontpage.grid.TableWapper;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: PickerConfigWapper.java, V1.0.0 2021年2月8日 下午8:38:27 $
 */
public class PickerConfigWapper {

	/**
	 * JAVA 模型名称（用于controller路径中）
	 */
	@Setter
	@Getter
	private String beanName;

	@Setter
	@Getter
	private PickerConfig config;

	@Setter
	@Getter
	private TableWapper tableWapper;

	@Setter
	@Getter
	private FormConfig formConfig;

	public PickerConfigWapper(String beanName, PickerConfig config) {
		super();
		this.beanName = beanName;
		this.config = config;
		
		formConfig = new FormConfig();
		formConfig.setFields(config.getFields());
		formConfig.setLayout("vertical");
	}

}
