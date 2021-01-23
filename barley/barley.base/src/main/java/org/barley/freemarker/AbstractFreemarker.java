
package org.barley.freemarker;

import java.io.File;
import java.io.IOException;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;

public abstract class AbstractFreemarker {
	protected Configuration configuration;

	/**
	 * 构造方法
	 * 
	 * @param clazz      参照资源对象
	 * @param pathPrefix 相对于@param clazz的相对目录路径
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	public AbstractFreemarker(Class clazz, String pathPrefix) throws IOException {
		// 初始化FreeMarker配置
		// 创建一个Configuration实例
		configuration = new Configuration();
		configuration.setDefaultEncoding("UTF-8");
		configuration.setObjectWrapper(new DefaultObjectWrapper());
		// 设置FreeMarker的模版文件位置
		if (!pathPrefix.endsWith(File.separator)) {
			pathPrefix += File.separator;
		}
		configuration.setClassForTemplateLoading(clazz, pathPrefix);
	}

	public abstract void doSettings();

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

}
