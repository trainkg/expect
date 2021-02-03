package com.barley.robot.modal.frontpage.form;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * form 配置
 * 
 * JAXB add annotation at get method.
 * 
 * @author peculiar.1@163.com
 * @version $ID: FormConfig.java, V1.0.0 2021年2月3日 下午4:02:30 $
 */
@XmlRootElement(name = "form")
public final class FormConfig {

	private List<Group> groups = new ArrayList<FormConfig.Group>();
	private List<Feild> fileds = new ArrayList<FormConfig.Feild>();

	@XmlElement(name = "group")
	public List<Group> getGroups() {
		return groups;
	}

	public void setGroups(List<Group> groups) {
		this.groups = groups;
	}

	@XmlElement(name = "feild")
	public List<Feild> getFileds() {
		return fileds;
	}

	public void setFileds(List<Feild> fileds) {
		this.fileds = fileds;
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

		private String title;
		private boolean showTitle;
		private List<Feild> feilds = new ArrayList<FormConfig.Feild>();

		public void setTitle(String title) {
			this.title = title;
		}

		@XmlAttribute
		public String getTitle() {
			return title;
		}

		@XmlAttribute
		public boolean isShowTitle() {
			return showTitle;
		}

		public void setShowTitle(boolean showTitle) {
			this.showTitle = showTitle;
		}

		public void setFeilds(List<Feild> feilds) {
			this.feilds = feilds;
		}

		@XmlElement(name = "feild")
		public List<Feild> getFeilds() {
			return feilds;
		}
	}

	/**
	 * Feild
	 * 
	 * @author peculiar.1@163.com
	 * @version $ID: FormConfig.java, V1.0.0 2021年2月3日 下午4:09:23 $
	 */
	public static class Feild {

		private String label;

		private String type;

		private String name;

		private String config;

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
	}

}
