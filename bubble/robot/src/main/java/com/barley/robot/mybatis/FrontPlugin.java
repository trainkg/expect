package com.barley.robot.mybatis;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;

import com.barley.robot.modal.frontpage.AbstractFrontGenerator;
import com.barley.robot.modal.frontpage.FormGenerator;
import com.barley.robot.modal.frontpage.GridGenerator;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * : 负责生成基于模型的前端信息
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: FrontPlugin.java, V1.0.0 2021年1月24日 上午11:35:36 $
 */
@Slf4j
public class FrontPlugin extends org.mybatis.generator.api.PluginAdapter {

	public static List<AbstractFrontGenerator<?>> generators = new ArrayList<AbstractFrontGenerator<?>>();
 
	static {
		generators.add(new FormGenerator());
		generators.add(new GridGenerator());
	}

	/**
	 * 
	 * : 前端文件存放的根目录, 后续考虑支持环境变量设定
	 * 
	 */
	public static final String config_front_basic_dir = "front.basic.dir";

	/**
	 * 
	 * 
	 */
	@Override
	public boolean validate(List<String> warnings) {
		return true;
	}

	/**
	 * <p>
	 * 调用所有的文件生成器, 目前先不关心各个生成器之间依赖问题
	 * </p>
	 */
	public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
		
		log.info("FrontPlugin table {} start", introspectedTable.getTableConfiguration().getTableName());
		String basicDir = context.getProperty(config_front_basic_dir);
		log.info("FrontPlugin basicDir setting is [{}]", basicDir);
		
		generators.forEach(generator -> {
			generator.setBasicDir(basicDir);
			generator.setIntrospectedTable(introspectedTable);
			generator.generateFile();
		});
		
		//return empty.
		return new ArrayList<GeneratedJavaFile>();
	}

}
