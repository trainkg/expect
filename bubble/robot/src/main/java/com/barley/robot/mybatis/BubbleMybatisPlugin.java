package com.barley.robot.mybatis;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.GeneratedKotlinFile;
import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.kotlin.KotlinFile;
import org.mybatis.generator.api.dom.kotlin.KotlinFunction;
import org.mybatis.generator.api.dom.kotlin.KotlinProperty;
import org.mybatis.generator.api.dom.kotlin.KotlinType;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.PropertyRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barley.robot.modal.resolvers.BasicServiceResolver;
import com.barley.robot.modal.resolvers.ServiceImplGenerator;

public class BubbleMybatisPlugin extends org.mybatis.generator.api.PluginAdapter {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public boolean validate(List<String> warnings) {
		if (warnings != null) {
			for (String string : warnings) {
				logger.info("bubble: {}", string);
			}
		}
		return true;
	}

	@Override
	public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
			IntrospectedTable introspectedTable, ModelClassType modelClassType) {
		logger.info("execute modelFieldGenerated");
		field.addJavaDocLine("/**bubble field comments*/");
		return true;
	}

	/**
	 *  
	 */
	@Override
	public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
		logger.info("execute modelBaseRecordClassGenerated");
		topLevelClass.addJavaDocLine("//bubble java doc");
		topLevelClass.addFileCommentLine("//bubble comment line");
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
			files.add(gjf);
		}

		// imples
		ServiceImplGenerator implGenereator = new ServiceImplGenerator(context.getProperty("resource.dir"),
				introspectedTable, genertor.getServiceExt());
		implGenereator.setSearchvo(genertor.getSearchvo());
		units = implGenereator.getCompilationUnits();
		for (CompilationUnit compilationUnit : units) {
			GeneratedJavaFile gjf = new GeneratedJavaFile(compilationUnit, implGenereator.getProject(),
					context.getProperty(PropertyRegistry.CONTEXT_JAVA_FILE_ENCODING), context.getJavaFormatter());
			files.add(gjf);
		}

		return files;
	}

	@Override
	public List<GeneratedXmlFile> contextGenerateAdditionalXmlFiles() {
		logger.info("execute contextGenerateAdditionalXmlFiles");
		return super.contextGenerateAdditionalXmlFiles();
	}

	@Override
	public List<GeneratedXmlFile> contextGenerateAdditionalXmlFiles(IntrospectedTable introspectedTable) {
		logger.info("execute contextGenerateAdditionalXmlFiles");
		return super.contextGenerateAdditionalXmlFiles(introspectedTable);
	}

	@Override
	public boolean clientCountByExampleMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {
		logger.info("execute clientCountByExampleMethodGenerated");
		return super.clientCountByExampleMethodGenerated(method, interfaze, introspectedTable);
	}

	@Override
	public List<GeneratedKotlinFile> contextGenerateAdditionalKotlinFiles() {

		return super.contextGenerateAdditionalKotlinFiles();
	}

	@Override
	public List<GeneratedKotlinFile> contextGenerateAdditionalKotlinFiles(IntrospectedTable introspectedTable) {

		return super.contextGenerateAdditionalKotlinFiles(introspectedTable);
	}

	@Override
	public boolean clientBasicCountMethodGenerated(KotlinFunction kotlinFunction, KotlinFile kotlinFile,
			IntrospectedTable introspectedTable) {

		return super.clientBasicCountMethodGenerated(kotlinFunction, kotlinFile, introspectedTable);
	}

	@Override
	public boolean clientBasicDeleteMethodGenerated(KotlinFunction kotlinFunction, KotlinFile kotlinFile,
			IntrospectedTable introspectedTable) {

		return super.clientBasicDeleteMethodGenerated(kotlinFunction, kotlinFile, introspectedTable);
	}

	@Override
	public boolean clientBasicInsertMethodGenerated(KotlinFunction kotlinFunction, KotlinFile kotlinFile,
			IntrospectedTable introspectedTable) {

		return super.clientBasicInsertMethodGenerated(kotlinFunction, kotlinFile, introspectedTable);
	}

	@Override
	public boolean clientBasicInsertMultipleMethodGenerated(KotlinFunction kotlinFunction, KotlinFile kotlinFile,
			IntrospectedTable introspectedTable) {

		return super.clientBasicInsertMultipleMethodGenerated(kotlinFunction, kotlinFile, introspectedTable);
	}

	@Override
	public boolean clientBasicInsertMultipleHelperMethodGenerated(KotlinFunction kotlinFunction, KotlinFile kotlinFile,
			IntrospectedTable introspectedTable) {

		return super.clientBasicInsertMultipleHelperMethodGenerated(kotlinFunction, kotlinFile, introspectedTable);
	}

	@Override
	public boolean clientBasicSelectManyMethodGenerated(KotlinFunction kotlinFunction, KotlinFile kotlinFile,
			IntrospectedTable introspectedTable) {

		return super.clientBasicSelectManyMethodGenerated(kotlinFunction, kotlinFile, introspectedTable);
	}

	@Override
	public boolean clientBasicSelectOneMethodGenerated(KotlinFunction kotlinFunction, KotlinFile kotlinFile,
			IntrospectedTable introspectedTable) {

		return super.clientBasicSelectOneMethodGenerated(kotlinFunction, kotlinFile, introspectedTable);
	}

	@Override
	public boolean clientBasicUpdateMethodGenerated(KotlinFunction kotlinFunction, KotlinFile kotlinFile,
			IntrospectedTable introspectedTable) {

		return super.clientBasicUpdateMethodGenerated(kotlinFunction, kotlinFile, introspectedTable);
	}

	@Override
	public boolean clientDeleteByPrimaryKeyMethodGenerated(KotlinFunction kotlinFunction, KotlinFile kotlinFile,
			IntrospectedTable introspectedTable) {

		return super.clientDeleteByPrimaryKeyMethodGenerated(kotlinFunction, kotlinFile, introspectedTable);
	}

	@Override
	public boolean clientGeneralCountMethodGenerated(KotlinFunction kotlinFunction, KotlinFile kotlinFile,
			IntrospectedTable introspectedTable) {

		return super.clientGeneralCountMethodGenerated(kotlinFunction, kotlinFile, introspectedTable);
	}

	@Override
	public boolean clientGeneralDeleteMethodGenerated(KotlinFunction kotlinFunction, KotlinFile kotlinFile,
			IntrospectedTable introspectedTable) {

		return super.clientGeneralDeleteMethodGenerated(kotlinFunction, kotlinFile, introspectedTable);
	}

	@Override
	public boolean clientGeneralSelectDistinctMethodGenerated(KotlinFunction kotlinFunction, KotlinFile kotlinFile,
			IntrospectedTable introspectedTable) {

		return super.clientGeneralSelectDistinctMethodGenerated(kotlinFunction, kotlinFile, introspectedTable);
	}

	@Override
	public boolean clientGeneralSelectMethodGenerated(KotlinFunction kotlinFunction, KotlinFile kotlinFile,
			IntrospectedTable introspectedTable) {

		return super.clientGeneralSelectMethodGenerated(kotlinFunction, kotlinFile, introspectedTable);
	}

	@Override
	public boolean clientGeneralUpdateMethodGenerated(KotlinFunction kotlinFunction, KotlinFile kotlinFile,
			IntrospectedTable introspectedTable) {

		return super.clientGeneralUpdateMethodGenerated(kotlinFunction, kotlinFile, introspectedTable);
	}

	@Override
	public boolean clientInsertMethodGenerated(KotlinFunction kotlinFunction, KotlinFile kotlinFile,
			IntrospectedTable introspectedTable) {

		return super.clientInsertMethodGenerated(kotlinFunction, kotlinFile, introspectedTable);
	}

	@Override
	public boolean clientInsertMultipleMethodGenerated(KotlinFunction kotlinFunction, KotlinFile kotlinFile,
			IntrospectedTable introspectedTable) {

		return super.clientInsertMultipleMethodGenerated(kotlinFunction, kotlinFile, introspectedTable);
	}

	@Override
	public boolean clientInsertSelectiveMethodGenerated(KotlinFunction kotlinFunction, KotlinFile kotlinFile,
			IntrospectedTable introspectedTable) {

		return super.clientInsertSelectiveMethodGenerated(kotlinFunction, kotlinFile, introspectedTable);
	}

	@Override
	public boolean clientSelectByPrimaryKeyMethodGenerated(KotlinFunction kotlinFunction, KotlinFile kotlinFile,
			IntrospectedTable introspectedTable) {

		return super.clientSelectByPrimaryKeyMethodGenerated(kotlinFunction, kotlinFile, introspectedTable);
	}

	@Override
	public boolean clientSelectOneMethodGenerated(KotlinFunction kotlinFunction, KotlinFile kotlinFile,
			IntrospectedTable introspectedTable) {

		return super.clientSelectOneMethodGenerated(kotlinFunction, kotlinFile, introspectedTable);
	}

	@Override
	public boolean clientUpdateAllColumnsMethodGenerated(KotlinFunction kotlinFunction, KotlinFile kotlinFile,
			IntrospectedTable introspectedTable) {

		return super.clientUpdateAllColumnsMethodGenerated(kotlinFunction, kotlinFile, introspectedTable);
	}

	@Override
	public boolean clientUpdateSelectiveColumnsMethodGenerated(KotlinFunction kotlinFunction, KotlinFile kotlinFile,
			IntrospectedTable introspectedTable) {

		return super.clientUpdateSelectiveColumnsMethodGenerated(kotlinFunction, kotlinFile, introspectedTable);
	}

	@Override
	public boolean clientUpdateByPrimaryKeySelectiveMethodGenerated(KotlinFunction kotlinFunction,
			KotlinFile kotlinFile, IntrospectedTable introspectedTable) {

		return super.clientUpdateByPrimaryKeySelectiveMethodGenerated(kotlinFunction, kotlinFile, introspectedTable);
	}

	@Override
	public boolean dynamicSqlSupportGenerated(KotlinFile kotlinFile, KotlinType supportClass,
			IntrospectedTable introspectedTable) {

		return super.dynamicSqlSupportGenerated(kotlinFile, supportClass, introspectedTable);
	}

	@Override
	public boolean mapperExtensionsGenerated(KotlinFile extensionsFile, IntrospectedTable introspectedTable) {

		return super.mapperExtensionsGenerated(extensionsFile, introspectedTable);
	}

	@Override
	public boolean mapperGenerated(KotlinFile mapperFile, IntrospectedTable introspectedTable) {

		return super.mapperGenerated(mapperFile, introspectedTable);
	}

	@Override
	public boolean kotlinDataClassGenerated(KotlinFile kotlinFile, KotlinType dataClass,
			IntrospectedTable introspectedTable) {

		return super.kotlinDataClassGenerated(kotlinFile, dataClass, introspectedTable);
	}

	@Override
	public boolean clientColumnListPropertyGenerated(KotlinProperty kotlinProperty, KotlinFile kotlinFile,
			IntrospectedTable introspectedTable) {

		return super.clientColumnListPropertyGenerated(kotlinProperty, kotlinFile, introspectedTable);
	}

	@Override
	public boolean clientInsertMultipleVarargMethodGenerated(KotlinFunction kotlinFunction, KotlinFile kotlinFile,
			IntrospectedTable introspectedTable) {

		return super.clientInsertMultipleVarargMethodGenerated(kotlinFunction, kotlinFile, introspectedTable);
	}

	@Override
	public boolean clientUpdateByPrimaryKeyMethodGenerated(KotlinFunction kotlinFunction, KotlinFile kotlinFile,
			IntrospectedTable introspectedTable) {

		return super.clientUpdateByPrimaryKeyMethodGenerated(kotlinFunction, kotlinFile, introspectedTable);
	}

	@Override
	public boolean clientBasicCountMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {

		return super.clientBasicCountMethodGenerated(method, interfaze, introspectedTable);
	}

	@Override
	public boolean clientBasicDeleteMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {

		return super.clientBasicDeleteMethodGenerated(method, interfaze, introspectedTable);
	}

	@Override
	public boolean clientBasicInsertMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {

		return super.clientBasicInsertMethodGenerated(method, interfaze, introspectedTable);
	}

	@Override
	public boolean clientBasicInsertMultipleMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {

		return super.clientBasicInsertMultipleMethodGenerated(method, interfaze, introspectedTable);
	}

	@Override
	public boolean clientBasicInsertMultipleHelperMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {

		return super.clientBasicInsertMultipleHelperMethodGenerated(method, interfaze, introspectedTable);
	}

	@Override
	public boolean clientBasicSelectManyMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {

		return super.clientBasicSelectManyMethodGenerated(method, interfaze, introspectedTable);
	}

	@Override
	public boolean clientBasicSelectOneMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {

		return super.clientBasicSelectOneMethodGenerated(method, interfaze, introspectedTable);
	}

	@Override
	public boolean clientBasicUpdateMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {

		return super.clientBasicUpdateMethodGenerated(method, interfaze, introspectedTable);
	}

	@Override
	public boolean clientDeleteByExampleMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {

		return super.clientDeleteByExampleMethodGenerated(method, interfaze, introspectedTable);
	}

	@Override
	public boolean clientDeleteByPrimaryKeyMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {

		return super.clientDeleteByPrimaryKeyMethodGenerated(method, interfaze, introspectedTable);
	}

	@Override
	public boolean clientGeneralCountMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {

		return super.clientGeneralCountMethodGenerated(method, interfaze, introspectedTable);
	}

	@Override
	public boolean clientGeneralDeleteMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {

		return super.clientGeneralDeleteMethodGenerated(method, interfaze, introspectedTable);
	}

	@Override
	public boolean clientGeneralSelectDistinctMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {

		return super.clientGeneralSelectDistinctMethodGenerated(method, interfaze, introspectedTable);
	}

	@Override
	public boolean clientGeneralSelectMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {

		return super.clientGeneralSelectMethodGenerated(method, interfaze, introspectedTable);
	}

	@Override
	public boolean clientGeneralUpdateMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {

		return super.clientGeneralUpdateMethodGenerated(method, interfaze, introspectedTable);
	}

	@Override
	public boolean clientInsertMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {
		logger.info("clientInsertMethodGenerated , method name {}", method.getName());
		method.addJavaDocLine("//插入方法");
		logger.info("clientInsertMethodGenerated");
		return super.clientInsertMethodGenerated(method, interfaze, introspectedTable);
	}

	@Override
	public boolean clientInsertMultipleMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {

		return super.clientInsertMultipleMethodGenerated(method, interfaze, introspectedTable);
	}

	@Override
	public boolean clientGenerated(Interface interfaze, IntrospectedTable introspectedTable) {

		return super.clientGenerated(interfaze, introspectedTable);
	}

	@Override
	public boolean clientSelectByExampleWithBLOBsMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {

		return super.clientSelectByExampleWithBLOBsMethodGenerated(method, interfaze, introspectedTable);
	}

	@Override
	public boolean clientSelectByExampleWithoutBLOBsMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {

		return super.clientSelectByExampleWithoutBLOBsMethodGenerated(method, interfaze, introspectedTable);
	}

	@Override
	public boolean clientSelectByPrimaryKeyMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {

		return super.clientSelectByPrimaryKeyMethodGenerated(method, interfaze, introspectedTable);
	}

	@Override
	public boolean clientSelectListFieldGenerated(Field field, Interface interfaze,
			IntrospectedTable introspectedTable) {

		return super.clientSelectListFieldGenerated(field, interfaze, introspectedTable);
	}

	@Override
	public boolean clientSelectOneMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {

		return super.clientSelectOneMethodGenerated(method, interfaze, introspectedTable);
	}

	@Override
	public boolean clientUpdateAllColumnsMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {

		return super.clientUpdateAllColumnsMethodGenerated(method, interfaze, introspectedTable);
	}

	@Override
	public boolean clientUpdateSelectiveColumnsMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {

		return super.clientUpdateSelectiveColumnsMethodGenerated(method, interfaze, introspectedTable);
	}

	@Override
	public boolean clientUpdateByExampleSelectiveMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {

		return super.clientUpdateByExampleSelectiveMethodGenerated(method, interfaze, introspectedTable);
	}

	@Override
	public boolean clientUpdateByExampleWithBLOBsMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {

		return super.clientUpdateByExampleWithBLOBsMethodGenerated(method, interfaze, introspectedTable);
	}

	@Override
	public boolean clientUpdateByExampleWithoutBLOBsMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {

		return super.clientUpdateByExampleWithoutBLOBsMethodGenerated(method, interfaze, introspectedTable);
	}

	@Override
	public boolean clientUpdateByPrimaryKeySelectiveMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {

		return super.clientUpdateByPrimaryKeySelectiveMethodGenerated(method, interfaze, introspectedTable);
	}

	@Override
	public boolean clientUpdateByPrimaryKeyWithBLOBsMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {

		return super.clientUpdateByPrimaryKeyWithBLOBsMethodGenerated(method, interfaze, introspectedTable);
	}

	@Override
	public boolean clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {

		return super.clientUpdateByPrimaryKeyWithoutBLOBsMethodGenerated(method, interfaze, introspectedTable);
	}

	@Override
	public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

		return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
	}

	@Override
	public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {

		return super.modelGetterMethodGenerated(method, topLevelClass, introspectedColumn, introspectedTable,
				modelClassType);
	}

	@Override
	public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

		return super.modelPrimaryKeyClassGenerated(topLevelClass, introspectedTable);
	}

	@Override
	public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {

		return super.modelRecordWithBLOBsClassGenerated(topLevelClass, introspectedTable);
	}

	@Override
	public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {

		return super.modelSetterMethodGenerated(method, topLevelClass, introspectedColumn, introspectedTable,
				modelClassType);
	}

	@Override
	public boolean sqlMapResultMapWithoutBLOBsElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {

		return super.sqlMapResultMapWithoutBLOBsElementGenerated(element, introspectedTable);
	}

	@Override
	public boolean sqlMapCountByExampleElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {

		return super.sqlMapCountByExampleElementGenerated(element, introspectedTable);
	}

	@Override
	public boolean sqlMapDeleteByExampleElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {

		return super.sqlMapDeleteByExampleElementGenerated(element, introspectedTable);
	}

	@Override
	public boolean sqlMapDeleteByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {

		return super.sqlMapDeleteByPrimaryKeyElementGenerated(element, introspectedTable);
	}

	@Override
	public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {

		return super.sqlMapDocumentGenerated(document, introspectedTable);
	}

	@Override
	public boolean sqlMapExampleWhereClauseElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {

		return super.sqlMapExampleWhereClauseElementGenerated(element, introspectedTable);
	}

	@Override
	public boolean sqlMapGenerated(GeneratedXmlFile sqlMap, IntrospectedTable introspectedTable) {

		return super.sqlMapGenerated(sqlMap, introspectedTable);
	}

	@Override
	public boolean sqlMapInsertElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {

		return super.sqlMapInsertElementGenerated(element, introspectedTable);
	}

	@Override
	public boolean sqlMapResultMapWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {

		return super.sqlMapResultMapWithBLOBsElementGenerated(element, introspectedTable);
	}

	@Override
	public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {

		return super.sqlMapSelectByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
	}

	@Override
	public boolean sqlMapSelectByExampleWithBLOBsElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {

		return super.sqlMapSelectByExampleWithBLOBsElementGenerated(element, introspectedTable);
	}

	@Override
	public boolean sqlMapSelectByPrimaryKeyElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {

		return super.sqlMapSelectByPrimaryKeyElementGenerated(element, introspectedTable);
	}

	@Override
	public boolean sqlMapUpdateByExampleSelectiveElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {

		return super.sqlMapUpdateByExampleSelectiveElementGenerated(element, introspectedTable);
	}

	@Override
	public boolean sqlMapUpdateByExampleWithBLOBsElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {

		return super.sqlMapUpdateByExampleWithBLOBsElementGenerated(element, introspectedTable);
	}

	@Override
	public boolean sqlMapUpdateByExampleWithoutBLOBsElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {

		return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
	}

	@Override
	public boolean sqlMapUpdateByPrimaryKeySelectiveElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {

		return super.sqlMapUpdateByPrimaryKeySelectiveElementGenerated(element, introspectedTable);
	}

	@Override
	public boolean sqlMapUpdateByPrimaryKeyWithBLOBsElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {

		return super.sqlMapUpdateByPrimaryKeyWithBLOBsElementGenerated(element, introspectedTable);
	}

	@Override
	public boolean sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(XmlElement element,
			IntrospectedTable introspectedTable) {

		return super.sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(element, introspectedTable);
	}

	@Override
	public boolean sqlMapInsertSelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {

		return super.sqlMapInsertSelectiveElementGenerated(element, introspectedTable);
	}

	@Override
	public boolean clientInsertSelectiveMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {

		return super.clientInsertSelectiveMethodGenerated(method, interfaze, introspectedTable);
	}

	/**
	 * @see IntrospectedTable.initialized 设定当前插件对于这个table定义的影响
	 */
	@Override
	public void initialized(IntrospectedTable introspectedTable) {
		super.initialized(introspectedTable);
		introspectedTable.setSelectByExampleStatementId("searchByCriteria");
	}

	@Override
	public boolean sqlMapBaseColumnListElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {

		return super.sqlMapBaseColumnListElementGenerated(element, introspectedTable);
	}

	@Override
	public boolean sqlMapBlobColumnListElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {

		return super.sqlMapBlobColumnListElementGenerated(element, introspectedTable);
	}

	@Override
	public boolean providerGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

		return super.providerGenerated(topLevelClass, introspectedTable);
	}

	@Override
	public boolean providerApplyWhereMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {

		return super.providerApplyWhereMethodGenerated(method, topLevelClass, introspectedTable);
	}

	@Override
	public boolean providerCountByExampleMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {

		return super.providerCountByExampleMethodGenerated(method, topLevelClass, introspectedTable);
	}

	@Override
	public boolean providerDeleteByExampleMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {

		return super.providerDeleteByExampleMethodGenerated(method, topLevelClass, introspectedTable);
	}

	@Override
	public boolean providerInsertSelectiveMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {

		return super.providerInsertSelectiveMethodGenerated(method, topLevelClass, introspectedTable);
	}

	@Override
	public boolean providerSelectByExampleWithBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {

		return super.providerSelectByExampleWithBLOBsMethodGenerated(method, topLevelClass, introspectedTable);
	}

	@Override
	public boolean providerSelectByExampleWithoutBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {

		return super.providerSelectByExampleWithoutBLOBsMethodGenerated(method, topLevelClass, introspectedTable);
	}

	@Override
	public boolean providerUpdateByExampleSelectiveMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {

		return super.providerUpdateByExampleSelectiveMethodGenerated(method, topLevelClass, introspectedTable);
	}

	@Override
	public boolean providerUpdateByExampleWithBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {

		return super.providerUpdateByExampleWithBLOBsMethodGenerated(method, topLevelClass, introspectedTable);
	}

	@Override
	public boolean providerUpdateByExampleWithoutBLOBsMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {

		return super.providerUpdateByExampleWithoutBLOBsMethodGenerated(method, topLevelClass, introspectedTable);
	}

	@Override
	public boolean providerUpdateByPrimaryKeySelectiveMethodGenerated(Method method, TopLevelClass topLevelClass,
			IntrospectedTable introspectedTable) {

		return super.providerUpdateByPrimaryKeySelectiveMethodGenerated(method, topLevelClass, introspectedTable);
	}

	@Override
	public boolean clientSelectAllMethodGenerated(Method method, Interface interfaze,
			IntrospectedTable introspectedTable) {

		return super.clientSelectAllMethodGenerated(method, interfaze, introspectedTable);
	}

	@Override
	public boolean sqlMapSelectAllElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {

		return super.sqlMapSelectAllElementGenerated(element, introspectedTable);
	}

	@Override
	public boolean dynamicSqlSupportGenerated(TopLevelClass supportClass, IntrospectedTable introspectedTable) {

		return super.dynamicSqlSupportGenerated(supportClass, introspectedTable);
	}

	@Override
	public int hashCode() {

		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {

		return super.equals(obj);
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {

		return super.clone();
	}

	@Override
	public String toString() {

		return super.toString();
	}

	@Override
	protected void finalize() throws Throwable {

		super.finalize();
	}

}
