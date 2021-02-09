package com.barley.robot.modal.frontpage.form;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 
 * form 配置
 * 
 * JAXB add annotation at get method.
 * 
 * https://www.antdv.com/components/form-model-cn/
 * 
 * @author peculiar.1@163.com
 * @version $ID: FormConfig.java, V1.0.0 2021年2月3日 下午4:02:30 $
 */
@XmlRootElement(name = "form")
public final class FormConfig {

	/**
	 * 布局方式
	 */
	private String layout = "horizontal";

	/**
	 * 
	 * 栅格系统
	 */
	private String gutter = "16,8";

	/**
	 * 单行内元素数量
	 */
	private int feildNum = 4;

	/**
	 * 内容栅格宽度, 水平布局不需要指定
	 */
	private int wrapperCol = 14;

	/**
	 * label栅格宽度,水平布局不需要指定
	 */
	private int labelCol = 4;

	/**
	 * 隐藏所有表单项的必选标记
	 */
	private boolean hideRequiredMark = false;

	/**
	 * 是否自动 加载域
	 * 
	 * 当设定为true的时候，会自动从数据读取对象模型，并且当和XML配置文件冲突的时候，优先使用XML配置
	 */
	private boolean autoLoadFeild = Boolean.TRUE;

	private List<Group> groups = new ArrayList<FormConfig.Group>();
	private List<Field> fields = new ArrayList<FormConfig.Field>();
	
	/**
	 * 
	 * 获取单个元素占位长度
	 * 
	 * @return
	 */
	public int getSingleFeildCols() {
		return 24 / getFeildNum();
	}
	
	/**
	 * 
	 * 配置中是否包含指定的列
	 * 
	 * @param javaProperty
	 * @return
	 */
	public boolean contain(String javaProperty) {

		boolean contain = false;
		for (Field field : getFields()) {
			if (javaProperty.equalsIgnoreCase(field.getName())) {
				contain = true;
			}
		}

		if (contain)
			return contain;

		for (Group group : getGroups()) {
			for (Field field : group.getFields()) {
				if (javaProperty.equalsIgnoreCase(field.getName())) {
					contain = true;
				}
			}

		}

		return contain;
	}
	
	@XmlElement(name = "group")
	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	@XmlElement(name = "field")
	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	@XmlAttribute
	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	@XmlAttribute
	public String getGutter() {
		return gutter;
	}

	public void setGutter(String gutter) {
		this.gutter = gutter;
	}

	@XmlAttribute
	public int getFeildNum() {
		return feildNum;
	}

	public void setFeildNum(int feildNum) {
		this.feildNum = feildNum;
	}

	@XmlAttribute
	public int getWrapperCol() {
		return wrapperCol;
	}

	public void setWrapperCol(int wrapperCol) {
		this.wrapperCol = wrapperCol;
	}

	@XmlAttribute
	public void setLabelCol(int labelCol) {
		this.labelCol = labelCol;
	}

	public int getLabelCol() {
		return labelCol;
	}

	@XmlAttribute
	public boolean isHideRequiredMark() {
		return hideRequiredMark;
	}

	public void setHideRequiredMark(boolean hideRequiredMark) {
		this.hideRequiredMark = hideRequiredMark;
	}
	
	
	@XmlAttribute
	public boolean isAutoLoadFeild() {
		return autoLoadFeild;
	}
	
	public void setAutoLoadFeild(boolean autoLoadFeild) {
		this.autoLoadFeild = autoLoadFeild;
	}

	/**
	 * 
	 * from group
	 * 
	 * 
	 * @author peculiar.1@163.com
	 * @version $ID: FormConfig.java, V1.0.0 2021年2月3日 下午4:07:06 $
	 */

	public static class Group {

		private String key;
		private String header;
		private boolean active;
		private boolean disabled;
		private boolean showArrow;
		private List<Field> fields = new ArrayList<FormConfig.Field>();

		public void setHeader(String header) {
			this.header = header;
		}

		@XmlAttribute
		public String getHeader() {
			return header;
		}

		@XmlAttribute
		public boolean isActive() {
			return active;
		}

		public void setActive(boolean active) {
			this.active = active;
		}

		@XmlAttribute
		public boolean isDisabled() {
			return disabled;
		}

		@XmlAttribute
		public boolean isShowArrow() {
			return showArrow;
		}

		public void setShowArrow(boolean showArrow) {
			this.showArrow = showArrow;
		}

		public void setDisabled(boolean disabled) {
			this.disabled = disabled;
		}

		public void setFields(List<Field> fields) {
			this.fields = fields;
		}

		@XmlElement(name = "field")
		public List<Field> getFields() {
			return fields;
		}

		public void setKey(String key) {
			this.key = key;
		}

		@XmlAttribute
		public String getKey() {
			return key;
		}
	}

	/**
	 * Feild
	 * 
	 * @author peculiar.1@163.com
	 * @version $ID: FormConfig.java, V1.0.0 2021年2月3日 下午4:09:23 $
	 */
	public static class Field {

		private String label;
		private String type;
		private String name;
		private String config;
		private String prop;
		private String rules;
		private String defaultValue;
		private boolean autoLink = true;
		private boolean colon = true;
		private String extra;
		private String labelCol;
		private boolean required = false;
		/**
		 * 默认只占一个元素位置
		 */
		private int feildSize = 1;

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}

		public String getType() {
			return type;
		}

		public String getConfig() {
			return config;
		}

		public void setConfig(String config) {
			this.config = config;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getExtra() {
			return extra;
		}

		public String getLabelCol() {
			return labelCol;
		}

		public String getProp() {
			return prop;
		}

		public String getRules() {
			return rules;
		}

		public boolean isAutoLink() {
			return autoLink;
		}

		public boolean isColon() {
			return colon;
		}

		public boolean isRequired() {
			return required;
		}

		public void setAutoLink(boolean autoLink) {
			this.autoLink = autoLink;
		}

		public void setColon(boolean colon) {
			this.colon = colon;
		}

		public void setExtra(String extra) {
			this.extra = extra;
		}

		public void setLabelCol(String labelCol) {
			this.labelCol = labelCol;
		}

		public void setProp(String prop) {
			this.prop = prop;
		}

		public void setRequired(boolean required) {
			this.required = required;
		}

		public void setRules(String rules) {
			this.rules = rules;
		}

		public void setType(String type) {
			this.type = type;
		}

		public int getFeildSize() {
			return feildSize;
		}

		public void setFeildSize(int feildSize) {
			this.feildSize = feildSize;
		}

		public void setDefaultValue(String defaultValue) {
			this.defaultValue = defaultValue;
		}

		public String getDefaultValue() {
			return defaultValue;
		}
	}

}
