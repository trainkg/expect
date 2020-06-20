package com.barley.config.form.define;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author peculiar.1@163.com
 * @version $ID: BaseForm.java, V1.0.0 2020年6月20日 下午3:44:30 $
 */
public class BaseForm implements Form {

	@Getter
	@Setter
	private String fromKey;
	
	@Getter
	private String name;
	
	@Getter
	private String description;

	@Getter
	@Setter
	private java.util.List<Section> sections;
	
	public class Section {
		@Getter
		@Setter
		private String sectionKey;

		/**
		 * 插槽名称 
		 * -- 用于模板插槽设计，用户扩展
		 */
		@Getter
		@Setter
		private String soltName;

		@Getter
		@Setter
		private List<Filed> fileds;
	}

	public class Filed {
		@Getter
		@Setter
		private String filedKey;
		@Getter
		@Setter
		private String label;
		@Getter
		@Setter
		private String name;
		@Getter
		@Setter
		private String soltName;
	}

}
