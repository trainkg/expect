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
	
	@Setter
	@Getter
	private String name;
	
	@Getter
	@Setter
	private String description;

	@Getter
	@Setter
	private java.util.List<Section> sections;
	
	public class Section {
		
		/**
		 * 
		 * {@link FiledType}
		 */
		@Getter
		@Setter
		private String type;
		
		@Getter
		@Setter
		private String title; 
		
		@Getter
		@Setter
		private String options;
		
		@Getter
		@Setter
		private String sectionKey;
		
		/**
		 * 	插槽名称 
		 * -- 用于模板插槽设计，用户扩展
		 */
		@Getter
		@Setter
		private String soltName;

		@Getter
		@Setter
		private List<Filed> fileds;


		public Section() {
			super();
		}


		public Section(String type, String title) {
			super();
			this.type = type;
			this.title = title;
		}


		public Section(String type, String title, String options, String sectionKey, List<Filed> fileds) {
			super();
			this.type = type;
			this.title = title;
			this.options = options;
			this.sectionKey = sectionKey;
			this.fileds = fileds;
		}
		
		
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
		
		/**
		 * 
		 * {@link SectionType}
		 */
		@Getter
		@Setter
		private String type;
		@Getter
		@Setter
		private String options;
		
		
		public Filed(String filedKey, String label, String name) {
			super();
			this.filedKey = filedKey;
			this.label = label;
			this.name = name;
		}


		public Filed(String filedKey, String soltName) {
			super();
			this.filedKey = filedKey;
			this.soltName = soltName;
		}


		public Filed(String filedKey, String label, String name, String type) {
			super();
			this.filedKey = filedKey;
			this.label = label;
			this.name = name;
			this.type = type;
		}


		public Filed(String filedKey, String label, String name, String type, String options) {
			super();
			this.filedKey = filedKey;
			this.label = label;
			this.name = name;
			this.type = type;
			this.options = options;
		}
		
		
	}

}
