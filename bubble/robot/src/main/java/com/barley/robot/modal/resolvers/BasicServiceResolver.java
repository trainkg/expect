package com.barley.robot.modal.resolvers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaElement;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barley.robot.modal.CondtionInterface;
import com.barley.robot.utils.DataFormatterUtils;

import jline.internal.Log;

/**
 * 
 * service 生成器
 *
 * service 接口分类划分， 基础接口和扩展接口
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: BasicServiceResolver.java, V1.0.0 2020年12月22日 下午2:51:21 $
 */
public class BasicServiceResolver extends AbstractJavaGenerator {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	public static final String CONFIG_PREFIX = "service.";

	// global property
	protected String basicServiceDir;
	protected String resourceDir;

	// modal property
	public static final String PROPERTY_SERVICE_ENABLED = "enabled";

	private CompilationUnit searchvo;
	private CompilationUnit service;
	// private CompilationUnit serviceImpl;
	private Interface serviceExt;

	public BasicServiceResolver(String project) {
		super(project);
	}

	public BasicServiceResolver(String project, IntrospectedTable introspectedTable) {
		super(project);
		setIntrospectedTable(introspectedTable);
		setContext(introspectedTable.getContext());
		basicServiceDir = context.getProperty("service.dir");
		resourceDir = context.getProperty("resource.dir");
	}

	@Override
	public List<CompilationUnit> getCompilationUnits() {
		if (!hasServiceConfig()) {
			return null;
		}
		List<CompilationUnit> units = new ArrayList<CompilationUnit>();
		units.add(createSearchVO());
		units.add(createService());
		units.add(getInterfaceExtShell((Interface) service));
		// units.add(createServiceImpl());
		return units;
	}

	/**
	 * 
	 * this method create service implement.
	 * 
	 * @return
	 */
	/*
	 * private CompilationUnit createServiceImpl() { ServiceImplGenerator generate =
	 * new ServiceImplGenerator(resourceDir, introspectedTable, service);
	 * CompilationUnit unit = generate.getCompilationUnits().get(0); serviceImpl =
	 * unit; serviceImpl.getImportedTypes().add(searchvo.getType());
	 * serviceImpl.getImportedTypes().add(service.getType()); return unit; }
	 */

	private CompilationUnit createService() {
		Interface topLevelClass = getTopLevelClassShell();
		addCreateMethod(topLevelClass);
		addDeleteMethod(topLevelClass);
		addUpdateMethod(topLevelClass);
		addFindAllMethod(topLevelClass);
		addFindByListIdMethod(topLevelClass);
		addSearchBySearchVO(topLevelClass);
		addSearchBySearchVOPage(topLevelClass);
		service = topLevelClass;
		return topLevelClass;
	}

	private void addSearchBySearchVOPage(Interface topLevelClass) {
		Method method = generateSearchBySearchVOMethodPage(topLevelClass);
		topLevelClass.addMethod(method);
	}

	protected Method generateSearchBySearchVOMethodPage(CompilationUnit topLevelClass) {
		topLevelClass.getImportedTypes().add(searchvo.getType());
		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<>();
		FullyQualifiedJavaType parameterType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
		importedTypes.add(parameterType);
		Method method = new Method("searchByVO");
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setAbstract(true);
		FullyQualifiedJavaType returnType = new FullyQualifiedJavaType("com.github.pagehelper.PageInfo");
		returnType.addTypeArgument(parameterType);
		importedTypes.add(returnType);
		method.setReturnType(returnType);
		method.addParameter(new Parameter(searchvo.getType(), "searchVO")); //$NON-NLS-1$
		method.addParameter(new Parameter(FullyQualifiedJavaType.getIntInstance(), "page")); //$NON-NLS-1$
		method.addParameter(new Parameter(FullyQualifiedJavaType.getIntInstance(), "pageSize")); //$NON-NLS-1$
		// 注释
		// context.getCommentGenerator().addGeneralMethodComment(method,
		// introspectedTable);
		topLevelClass.addImportedTypes(importedTypes);
		return method;
	}

	protected void addFindByListIdMethod(Interface topLevelClass) {
		Method method = gereateFindByListIdMetod(topLevelClass);
		if (method != null) {
			topLevelClass.addMethod(method);
		}
	}

	/**
	 * search with primary key object.
	 * 
	 * @param topLevelClass
	 * @return
	 */
	protected Method gereateFindByListIdMetod(CompilationUnit topLevelClass) {
		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<>();
		FullyQualifiedJavaType parameterType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());

		if (introspectedTable.getRules().generatePrimaryKeyClass()) {
			FullyQualifiedJavaType primaryKeyType = new FullyQualifiedJavaType(introspectedTable.getPrimaryKeyType());
			importedTypes.add(primaryKeyType);
			importedTypes.add(parameterType);

			Method method = new Method("findByPrimaryKey");
			method.setVisibility(JavaVisibility.PUBLIC);
			method.setAbstract(true);
			method.setReturnType(parameterType);
			method.addParameter(new Parameter(primaryKeyType, "keyId")); //$NON-NLS-1$
			// 注释
			// context.getCommentGenerator().addGeneralMethodComment(method,
			// introspectedTable);

			topLevelClass.addImportedTypes(importedTypes);
			return method;
		} else if (introspectedTable.hasPrimaryKeyColumns()) {
			FullyQualifiedJavaType primarykeytype = introspectedTable.getPrimaryKeyColumns().get(0)
					.getFullyQualifiedJavaType();
			importedTypes.add(parameterType);

			Method method = new Method("findByPrimaryKey");
			method.setVisibility(JavaVisibility.PUBLIC);
			method.setAbstract(true);
			method.setReturnType(parameterType);
			method.addParameter(new Parameter(primarykeytype, "keyId")); //$NON-NLS-1$
			// 注释
			// context.getCommentGenerator().addGeneralMethodComment(method,
			// introspectedTable);
			importedTypes.add(primarykeytype);
			topLevelClass.addImportedTypes(importedTypes);
			return method;
		}

		return null;
	}

	/**
	 * 
	 * 这个方法负责生成service中的searchByVO方法
	 * 
	 * @param topLevelClass
	 */
	protected void addSearchBySearchVO(Interface topLevelClass) {
		Method method = gereateSearchBySearchVOMethod(topLevelClass);
		topLevelClass.addMethod(method);
	}

	protected Method gereateSearchBySearchVOMethod(CompilationUnit topLevelClass) {
		topLevelClass.getImportedTypes().add(searchvo.getType());

		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<>();
		FullyQualifiedJavaType parameterType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
		importedTypes.add(parameterType);
		Method method = new Method("searchByVO");
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setAbstract(true);
//		FullyQualifiedJavaType returnType = new FullyQualifiedJavaType("com.github.pagehelper.PageInfo");
//		returnType.addTypeArgument(parameterType);
		FullyQualifiedJavaType returnType = FullyQualifiedJavaType.getNewListInstance();
		returnType.addTypeArgument(parameterType);
		importedTypes.add(returnType);
		method.setReturnType(returnType);
		method.addParameter(new Parameter(searchvo.getType(), "searchVO")); //$NON-NLS-1$
		// 注释
		// context.getCommentGenerator().addGeneralMethodComment(method,
		// introspectedTable);
		topLevelClass.addImportedTypes(importedTypes);
		return method;
	}

	/**
	 * 
	 * this method create findAll.
	 * 
	 * @param topLevelClass
	 */
	protected void addFindAllMethod(Interface topLevelClass) {
		Method method = gereateFindAllMetod(topLevelClass);
		topLevelClass.addMethod(method);
	}

	protected Method gereateFindAllMetod(CompilationUnit topLevelClass) {
		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<>();
		FullyQualifiedJavaType parameterType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
		importedTypes.add(parameterType);
		Method method = new Method("findAll");
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setAbstract(true);
		FullyQualifiedJavaType type = FullyQualifiedJavaType.getNewListInstance();
		type.addTypeArgument(parameterType);
		method.setReturnType(type);
		// 注释
		// context.getCommentGenerator().addGeneralMethodComment(method,
		// introspectedTable);
		topLevelClass.addImportedTypes(importedTypes);
		return method;
	}

	/**
	 * 
	 * this method create update.
	 * 
	 * @param topLevelClass
	 */
	protected void addUpdateMethod(Interface topLevelClass) {
		Method method = gereateUpdateMetod(topLevelClass);
		topLevelClass.addMethod(method);
	}

	protected Method gereateUpdateMetod(CompilationUnit topLevelClass) {
		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<>();
		FullyQualifiedJavaType parameterType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
		importedTypes.add(parameterType);

		Method method = new Method("update");
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setAbstract(true);
		method.setReturnType(parameterType);
		method.addParameter(new Parameter(parameterType, "record")); //$NON-NLS-1$
		// 注释
		// context.getCommentGenerator().addGeneralMethodComment(method,
		// introspectedTable);

		topLevelClass.addImportedTypes(importedTypes);
		return method;
	}

	/**
	 * this method create delete
	 * 
	 * @param topLevelClass
	 */
	protected void addDeleteMethod(Interface topLevelClass) {
		Method method = generateDeleteMetod(topLevelClass);
		if (method != null) {
			topLevelClass.addMethod(method);
		}
	}

	protected Method generateDeleteMetod(CompilationUnit unit) {
		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<>();
		FullyQualifiedJavaType parameterType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());

		if (introspectedTable.getRules().generatePrimaryKeyClass()) {
			FullyQualifiedJavaType primaryKeyType = new FullyQualifiedJavaType(introspectedTable.getPrimaryKeyType());
			importedTypes.add(primaryKeyType);
			importedTypes.add(parameterType);

			Method method = new Method("delete");
			method.setVisibility(JavaVisibility.PUBLIC);
			method.setAbstract(true);
			method.addParameter(new Parameter(primaryKeyType, "keyId")); //$NON-NLS-1$
			// 注释
			// context.getCommentGenerator().addGeneralMethodComment(method,
			// introspectedTable);

			unit.addImportedTypes(importedTypes);
			return method;
		} else if (introspectedTable.hasPrimaryKeyColumns()) {
			FullyQualifiedJavaType primarykeytype = introspectedTable.getPrimaryKeyColumns().get(0)
					.getFullyQualifiedJavaType();
			importedTypes.add(parameterType);

			Method method = new Method("delete");
			method.setVisibility(JavaVisibility.PUBLIC);
			method.setAbstract(true);
			method.addParameter(new Parameter(primarykeytype, "keyId")); //$NON-NLS-1$
			// 注释
			// context.getCommentGenerator().addGeneralMethodComment(method,
			// introspectedTable);
			importedTypes.add(primarykeytype);
			unit.addImportedTypes(importedTypes);
			return method;
		}

		return null;

	}

	protected void addCreateMethod(Interface topLevelClass) {
		Method method = geneCreateMethodDefine(topLevelClass);
		topLevelClass.addMethod(method);
		// TODO
		// 其他插件生成的对象，是否需要自动导入
		method.addBodyLine("return record;");
	}

	/**
	 * 
	 * @param topLevelClass
	 * @return
	 */
	protected Method geneCreateMethodDefine(CompilationUnit topLevelClass) {
		Set<FullyQualifiedJavaType> importedTypes = new TreeSet<>();
		FullyQualifiedJavaType parameterType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
		importedTypes.add(parameterType);

		Method method = new Method("create");
		method.setVisibility(JavaVisibility.PUBLIC);
		method.setAbstract(true);
		method.setReturnType(parameterType);
		method.addParameter(new Parameter(parameterType, "record")); //$NON-NLS-1$
		// 注释
		// context.getCommentGenerator().addGeneralMethodComment(method,
		// introspectedTable);
		topLevelClass.addImportedTypes(importedTypes);
		return method;
	}

	/**
	 * 
	 * 该方法负责创建searchvo JAVA对象
	 * 
	 * @return
	 */
	private CompilationUnit createSearchVO() {
		SearchVOGenerator generator = new SearchVOGenerator(getProject(), getIntrospectedTable());
		searchvo = generator.getCompilationUnits().get(0);
		return searchvo;
	}

	/**
	 * hasServiceConfig 该方法是用于判断是否有service生成相关配置
	 * 
	 * @return
	 */
	private boolean hasServiceConfig() {
		String propertyValue = introspectedTable
				.getTableConfigurationProperty(getpropertyKey(PROPERTY_SERVICE_ENABLED));
		logger.info("generete service property value is {}", propertyValue);
		return propertyValue != null && propertyValue.equalsIgnoreCase("true");
	}

	/**
	 * 
	 * 
	 * 
	 * @param propertyServiceName
	 * @return
	 */
	private String getpropertyKey(String propertyServiceName) {
		return CONFIG_PREFIX + propertyServiceName;
	}

	/**
	 * 
	 * 
	 * create top class instance
	 * 
	 * @return
	 */
	private Interface getTopLevelClassShell() {
		CommentGenerator commentGenerator = context.getCommentGenerator();
		FullyQualifiedJavaType type = new FullyQualifiedJavaType(getJavaPath());
		Interface topLevelClass = new Interface(type);
		topLevelClass.setVisibility(JavaVisibility.PUBLIC);
		commentGenerator.addJavaFileComment(topLevelClass);
		addJavaDoc(topLevelClass, true);
		return topLevelClass;
	}

	private Interface getInterfaceExtShell(Interface interfazz) {
		CommentGenerator commentGenerator = context.getCommentGenerator();
		FullyQualifiedJavaType type = new FullyQualifiedJavaType(getExtendInterfaceJavaPath());
		CondtionInterface topLevelClass = new CondtionInterface(type);
		topLevelClass.setOverride(false);
		topLevelClass.setVisibility(JavaVisibility.PUBLIC);
		commentGenerator.addJavaFileComment(topLevelClass);
		topLevelClass.getSuperInterfaceTypes().add(interfazz.getType());
		addJavaDoc(topLevelClass);
		this.serviceExt = topLevelClass;
		return topLevelClass;
	}

	/**
	 * 
	 * JAVA DOCS
	 * 
	 * @param topLevelClass
	 */
	protected void addJavaDoc(JavaElement topLevelClass) {
		addJavaDoc(topLevelClass, false);
	}

	protected void addJavaDoc(JavaElement topLevelClass, boolean nochangeTips) {
		topLevelClass.getJavaDocLines().add("/**");
		if (nochangeTips) {
			topLevelClass.getJavaDocLines().add(" * Auto generate , don't modify this file.");
		}
		topLevelClass.getJavaDocLines().add(" * @author peculiar.1@163.com");
		topLevelClass.getJavaDocLines().add(" * @version $ID: " + getJavaPath() + " create date " + DataFormatterUtils
				.format(new Date(System.currentTimeMillis()), DataFormatterUtils.FMT_YYYY_MM_DD_HH_MM_SS));
		topLevelClass.getJavaDocLines().add(" */");
	}

	/**
	 * 
	 * service file path
	 * 
	 * 
	 * @return
	 */
	public String getJavaPath() {
		String path = basicServiceDir + getSubPackage() + "."
				+ introspectedTable.getFullyQualifiedTable().getDomainObjectName() + "BaseService";
		Log.info("service top class is {}", path);
		return path;
	}

	public String getExtendInterfaceJavaPath() {
		String path = basicServiceDir + getSubPackage() + "."
				+ introspectedTable.getFullyQualifiedTable().getDomainObjectName() + "Service";
		return path;
	}

	/**
	 * 
	 * sub package setting.
	 * 
	 * @return
	 */
	public String getSubPackage() {
		if (StringUtils.isNotBlank(introspectedTable.getFullyQualifiedTable().getDomainObjectSubPackage())) {
			return "." + introspectedTable.getFullyQualifiedTable().getDomainObjectSubPackage();
		}
		return "";
	}

	public CompilationUnit getService() {
		return service;
	}

	public Interface getServiceExt() {
		return serviceExt;
	}

	public CompilationUnit getSearchvo() {
		return searchvo;
	}

	public void setSearchvo(CompilationUnit searchvo) {
		this.searchvo = searchvo;
	}
}
