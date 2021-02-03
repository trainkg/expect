package com.barley.robot.modal.frontpage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.IntrospectedTable;

import com.barley.robot.SimpleClasspathXMLResover;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * abstract front generator
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: AbstractFrontGenerator.java, V1.0.0 2021年1月24日 下午3:05:46 $
 */

@Slf4j
public abstract class AbstractFrontGenerator<T extends Object> {

	public static Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);

	@Setter
	@Getter
	protected IntrospectedTable introspectedTable;
	/**
	 * file basic directory.
	 */
	@Setter
	@Getter
	private String basicDir;

	static {
		// cfg.setTemplateLookupStrategy(TemplateLookupStrategy.DEFAULT_2_3_0);
		cfg.setDefaultEncoding("UTF-8");
		cfg.setClassForTemplateLoading(AbstractFrontGenerator.class, "/template");

		try {
			cfg.setSharedVariable("creator", "Balery auto generator");
		} catch (TemplateModelException e) {
			throw new RuntimeException(e);
		}

	}
	
	/**
	 * <p>获取XML配置文件路径,如果返回空,则当前生成器不支持XML方式配置</p>
	 * @return
	 */
	protected String getXmlConfigPath() {
		return null;
	}
	
	/**
	 * D: 获取模板位置
	 * @return
	 */
	abstract protected String getTemplatePath();

	/**
	 * 
	 * D: 获取数据模型
	 * @return
	 */
	abstract protected T getModel();

	/**
	 * D: 获取启用当前生成器配置项名称
	 * @return
	 */
	abstract protected String getFeatureConfigName();

	/**
	 * D: 是否需要生成文件
	 * 
	 * @return
	 */
	protected boolean needGenerate() {
		String propertyValue = introspectedTable.getTableConfigurationProperty(getFeatureConfigName());
		return "true".equalsIgnoreCase(propertyValue);
	};

	protected String defaultFileDirectory() {
		return "";
	}

	protected String getBizModelNamePath() {
		String bizModel = introspectedTable.getContext().getProperty("front.bizmodel");
		if (bizModel != null && !"".equals(bizModel)) {
			if (bizModel.startsWith("/")) {
				return bizModel;
			} else {
				return "/" + bizModel;
			}
		}
		return "";
	}

	/**
	 * 
	 * this method is setting of sub directory.
	 * 
	 * @return
	 */
	protected String getModelNamePath() {
		String modelNameDir = introspectedTable.getTableConfigurationProperty("front.dir");
		if (modelNameDir != null && !"".equals(modelNameDir)) {
			if (modelNameDir.startsWith("/")) {
				return modelNameDir;
			} else {
				return "/" + modelNameDir;
			}
		}
		return "";
	}

	/**
	 * 
	 * this method is setting of file name.
	 * @return
	 */
	abstract protected String getFileName();

	/**
	 * 
	 * D： 获取生成文件对应的标签名
	 * @return
	 */
	abstract String getTagName();

	/**
	 * <p>
	 * 获取生成文件的位置, 目前先定义简单规则, 路径由基础路径+业务模块模块+模型目录+文件名称构成
	 * </p>
	 * 
	 * @return
	 */
	protected String getGeneratorFilePath() {
		return basicDir + getBizModelNamePath() + getModelNamePath() + getFileName();
	}
	
	
	/**
	 * 获取配置对象
	 * @param class1
	 * @return
	 */
	protected Object getConfig(Class<?> class1) {
		String configPath = getXmlConfigPath();
		if(StringUtils.isNotBlank(configPath)) {
			return new SimpleClasspathXMLResover(configPath, class1).parseXml();
		}
		return null;
	}

	/**
	 * @describe 文件生成
	 */
	public void generateFile() {
		boolean needGenerate = needGenerate();
		if (!needGenerate) {
			return;
		}

		FileWriter fos = null;
		try {
			Template template = cfg.getTemplate(getTemplatePath());
			File file = new File(getGeneratorFilePath());
			log.info("frontpage generate file [{}]", file.getAbsolutePath());
			if (file.exists()) {
				log.warn("file [{}] is exits, if you want re-generate this file ,Please remove it first.",
						file.getAbsolutePath());
				return;
			} else {
				file.getParentFile().mkdirs();
				try {
					boolean created = file.createNewFile();
					if (!created) {
						throw new RuntimeException("create file failed.");
					}
					// file.createNewFile();
				} catch (Exception e) {
					throw new RuntimeException("create file failed.", e);
				}
			}
			fos = new FileWriter(file, false);
			template.process(getModel(), fos);

		} catch (IOException | TemplateException e) {
			throw new RuntimeException(e);
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					// ignore
				}
			}
		}
	}
}
