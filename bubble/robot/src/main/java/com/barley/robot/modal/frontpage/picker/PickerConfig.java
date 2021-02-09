package com.barley.robot.modal.frontpage.picker;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.barley.robot.modal.frontpage.form.FormConfig.Field;

/**
 * 
 * 选择器配置, 目前只支持设定查询条件，查询结果集为默认实现
 * 
 * @author peculiar.1@163.com
 * @version $ID: PickerConfig.java, V1.0.0 2021年2月8日 下午7:48:26 $
 */
@XmlRootElement
public class PickerConfig {

	/**
	 * 是否自动 加载域
	 * 
	 * 当设定为true的时候，会自动从数据读取对象模型，并且当和XML配置文件冲突的时候，优先使用XML配置
	 */
	private boolean autoLoadFeild = Boolean.FALSE;

	private List<Field> fields = new ArrayList<Field>();

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public void setAutoLoadFeild(boolean autoLoadFeild) {
		this.autoLoadFeild = autoLoadFeild;
	}

	@XmlAttribute
	public boolean isAutoLoadFeild() {
		return autoLoadFeild;
	}

	public boolean contain(String javaProperty) {

		boolean contain = false;
		for (Field field : getFields()) {
			if (javaProperty.equalsIgnoreCase(field.getName())) {
				contain = true;
			}
		}

		return contain;
	}

}
