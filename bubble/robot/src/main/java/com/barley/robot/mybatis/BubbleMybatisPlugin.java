package com.barley.robot.mybatis;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.ShellCallback;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.PropertyRegistry;
import org.mybatis.generator.exception.ShellException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barley.robot.modal.ConditonTopClass;
import com.barley.robot.modal.CondtionInterface;
import com.barley.robot.modal.resolvers.BasicActionResolver;
import com.barley.robot.modal.resolvers.BasicServiceResolver;
import com.barley.robot.modal.resolvers.ServiceImplGenerator;

public class BubbleMybatisPlugin extends org.mybatis.generator.api.PluginAdapter {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	public boolean existFile(GeneratedJavaFile gjf, ShellCallback shellCallback) {
		File directory;
		try {
			directory = shellCallback.getDirectory(gjf.getTargetProject(), gjf.getTargetPackage());
			File targetFile = new File(directory, gjf.getFileName());
			if (targetFile.exists()) {
				return true;
			}
		} catch (ShellException e) {
			throw new RuntimeException(e);
		}
		return false;
	}

	@Override
	public boolean validate(List<String> warnings) {
		if (warnings != null) {
			for (String string : warnings) {
				logger.info("bubble: {}", string);
			}
		}
		return true;
	}

	/**
	 * 
	 * 额外的java文件在这里生成, 和模型无关的文件。
	 */
	@Override
	public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles() {
		logger.info("execute modelBaseRecordClassGenerated");
		return super.contextGenerateAdditionalJavaFiles();
	}

	@Override
	public Context getContext() {
		return super.getContext();
	}

	@Override
	public void setContext(Context context) {
		logger.info("execute setContext");
		super.setContext(context);
	}

	@Override
	public Properties getProperties() {
		return super.getProperties();
	}

	@Override
	public void setProperties(Properties properties) {
		logger.info("execute setProperties");
		super.setProperties(properties);
	}

	/**
	 * 生成和模型有关的额外文件
	 */
	@Override
	public List<GeneratedJavaFile> contextGenerateAdditionalJavaFiles(IntrospectedTable introspectedTable) {
		logger.info("execute contextGenerateAdditionalJavaFiles table name {} {} ",
				introspectedTable.getTableConfiguration().getTableName(), introspectedTable.getTableType());

		DefaultShellCallback shellback = new DefaultShellCallback(false);
		// service
		List<GeneratedJavaFile> files = new ArrayList<GeneratedJavaFile>();
		// services
		BasicServiceResolver genertor = new BasicServiceResolver("", introspectedTable);
		List<CompilationUnit> units = genertor.getCompilationUnits();

		if (units == null || units.size() == 0) {
			return files;
		}

		for (CompilationUnit compilationUnit : units) {
			GeneratedJavaFile gjf = new GeneratedJavaFile(compilationUnit,
					context.getJavaModelGeneratorConfiguration().getTargetProject(),
					context.getProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING), context.getJavaFormatter());

			if (!existFile(gjf, shellback)) {
				files.add(gjf);
			} else {

				boolean override = true;

				if (compilationUnit instanceof CondtionInterface) {
					override = ((CondtionInterface) compilationUnit).isOverride();
				}

				if (compilationUnit instanceof ConditonTopClass) {
					override = ((ConditonTopClass) compilationUnit).isOverride();
				}

				if (!override) {
					logger.warn(" file exist, no generate. {} ", gjf);
				} else {
					files.add(gjf);
				}
			}
		}

		// imples
		ServiceImplGenerator implGenereator = new ServiceImplGenerator(context.getProperty("resource.dir"),
				introspectedTable, genertor.getServiceExt());
		implGenereator.setSearchvo(genertor.getSearchvo());
		units = implGenereator.getCompilationUnits();
		for (CompilationUnit compilationUnit : units) {
			GeneratedJavaFile gjf = new GeneratedJavaFile(compilationUnit, implGenereator.getProject(),
					context.getProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING), context.getJavaFormatter());

			if (!existFile(gjf, shellback)) {
				files.add(gjf);
			} else {
				logger.warn(" file exist, no generate. {} ", gjf);
			}
		}

		// actions
		BasicActionResolver actionGenerater = new BasicActionResolver(context.getProperty("resource.dir"),
				introspectedTable, genertor.getServiceExt());

		actionGenerater.setSearchVO(genertor.getSearchvo());
		units = actionGenerater.getCompilationUnits();
		for (CompilationUnit compilationUnit : units) {
			GeneratedJavaFile gjf = new GeneratedJavaFile(compilationUnit, actionGenerater.getProject(),
					context.getProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING), context.getJavaFormatter());
			if (!existFile(gjf, shellback)) {
				files.add(gjf);
			} else {
				logger.warn(" file exist, no generate. {} ", gjf);
			}
		}
		return files;
	}


	/**
	 * @see IntrospectedTable.initialized 设定当前插件对于这个table定义的影响
	 */
	@Override
	public void initialized(IntrospectedTable introspectedTable) {
		super.initialized(introspectedTable);
		introspectedTable.setSelectByExampleStatementId("searchByCriteria");
	}


}
