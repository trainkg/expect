package com.barley.robot.modal.resolvers;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;

/**
 * 
 * Search VO 生成器
 * 
 * @author peculiar.1@163.com
 * @version $ID: SearchVOGenerator.java, V1.0.0 2020年12月22日 下午5:44:25 $
 */
public class ServiceImplGenerator extends BasicServiceResolver {

	private Interface service;
	private TopLevelClass serviceImpl;

	public ServiceImplGenerator(String project) {
		super(project);
	}

	public ServiceImplGenerator(String project, IntrospectedTable introspectedTable, Interface service) {
		super(project, introspectedTable);
		this.service = service;
	}

	@Override
	public List<CompilationUnit> getCompilationUnits() {
		List<CompilationUnit> units = new ArrayList<CompilationUnit>();
		units.add(createServiceImpl());
		return units;
	}

	private CompilationUnit createServiceImpl() {
		TopLevelClass topLevelClass = getTopLevelClassShell();
		addCreateImplMethod(topLevelClass);
		addDeleteImplMethod(topLevelClass);
		addUpdateImplMethod(topLevelClass);
		addFindAllImplMethod(topLevelClass);
		addFindByListIdImplMethod(topLevelClass);
		addSearchBySearchVOImplMethod(topLevelClass);
		addSearchBySearchVOPageImplMethod(topLevelClass);
		addInternalSearch(topLevelClass);

		return topLevelClass;
	}

	private void addSearchBySearchVOPageImplMethod(TopLevelClass topLevelClass) {
		Method method = generateSearchBySearchVOMethodPage(topLevelClass);
		method.setAbstract(false);
		method.getBodyLines().add("return internalfindBySearchVO(searchVO, page, pageSize);");
		topLevelClass.addMethod(method);
	}

	private void addInternalSearch(TopLevelClass topLevelClass) {
		Method method = new Method("internalfindBySearchVO");
		method.setVisibility(JavaVisibility.PROTECTED);

		FullyQualifiedJavaType parameterType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
		String modalName = parameterType.getShortName();

		FullyQualifiedJavaType returnType = new FullyQualifiedJavaType("com.github.pagehelper.PageInfo");
		returnType.addTypeArgument(parameterType);
		method.setReturnType(returnType);

		method.getParameters().add(new Parameter(getSearchvo().getType(), "searchvo"));
		method.getParameters().add(new Parameter(new FullyQualifiedJavaType("java.lang.Integer"), "page"));
		method.getParameters().add(new Parameter(new FullyQualifiedJavaType("java.lang.Integer"), "pageSize"));

		method.getBodyLines().add("Page<Object> pagesvo = null;");
		method.getBodyLines().add("if (page != null) {");
		method.getBodyLines().add("pagesvo = PageHelper.startPage(page, pageSize);");
		method.getBodyLines().add("}");
		method.getBodyLines().add("if (searchvo instanceof CriteriaBuilder) {");
		method.getBodyLines().add("((CriteriaBuilder) searchvo).build();");
		method.getBodyLines().add("}");
		method.getBodyLines().add("List<" + modalName + "> list = entityMapper.searchByCriteria(searchvo);");
		method.getBodyLines()
				.add("PageInfo<" + modalName + "> pageInfo = new PageInfo<" + modalName + ">(list, pageSize);");
		method.getBodyLines().add("if (pagesvo != null) {");
		method.getBodyLines().add("pageInfo.setTotal(pagesvo.getTotal());");
		method.getBodyLines().add("}");
		method.getBodyLines().add("return pageInfo;");
		topLevelClass.addMethod(method);

		topLevelClass.getImportedTypes().add(new FullyQualifiedJavaType("org.barley.mybatis.CriteriaBuilder"));
		topLevelClass.getImportedTypes().add(new FullyQualifiedJavaType("com.github.pagehelper.Page"));
		topLevelClass.getImportedTypes().add(new FullyQualifiedJavaType("com.github.pagehelper.PageHelper"));
		topLevelClass.getImportedTypes().add(new FullyQualifiedJavaType("com.github.pagehelper.PageInfo"));
		topLevelClass.getImportedTypes().add(new FullyQualifiedJavaType("java.util.List"));

	}

	private void addFindByListIdImplMethod(TopLevelClass topLevelClass) {
		Method method = gereateFindByListIdMetod(topLevelClass);
		if (method != null) {
			method.setAbstract(false);
			String domian = introspectedTable.getTableConfiguration().getDomainObjectName();
			method.getBodyLines().add(domian + " entity = entityMapper.selectByPrimaryKey(keyId);");
			method.getBodyLines().add("return entity;");
			topLevelClass.addMethod(method);
		}
	}

	private void addSearchBySearchVOImplMethod(TopLevelClass topLevelClass) {
		Method method = gereateSearchBySearchVOMethod(topLevelClass);
		method.setAbstract(false);
		// method.getBodyLines().add("//TODO");
		// method.getBodyLines().add("throw new RuntimeException(new
		// NotSupportedException(\"no support\"));");
		FullyQualifiedJavaType parameterType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
		method.getBodyLines().add("PageInfo<" + parameterType.getShortName()
				+ "> pageInfo = internalfindBySearchVO(searchVO, null, null);");
		method.getBodyLines().add("return pageInfo.getList();");

		topLevelClass.addMethod(method);

		//topLevelClass.getImportedTypes().add(new FullyQualifiedJavaType("javax.transaction.NotSupportedException"));

	}

	private void addFindAllImplMethod(TopLevelClass topLevelClass) {
		Method method = gereateFindAllMetod(topLevelClass);
		method.setAbstract(false);
		String SearchType = getSearchvo().getType().getShortName();
		method.getBodyLines().add(SearchType + " searchvo = new " + SearchType + "();");
		method.getBodyLines().add("return entityMapper.searchByCriteria(searchvo);");
		topLevelClass.addMethod(method);
	}

	private void addUpdateImplMethod(TopLevelClass topLevelClass) {
		Method method = gereateUpdateMetod(topLevelClass);
		method.setAbstract(false);
		method.getBodyLines().add("entityMapper.updateByPrimaryKey(record);");
		method.getBodyLines().add("return record;");
		topLevelClass.addMethod(method);
	}

	private void addDeleteImplMethod(TopLevelClass topLevelClass) {
		Method method = generateDeleteMetod(topLevelClass);
		if (method != null) {
			method.setAbstract(false);
			method.getBodyLines().add("entityMapper.deleteByPrimaryKey(keyId);");
			topLevelClass.addMethod(method);
		}
	}

	private void addCreateImplMethod(TopLevelClass topLevelClass) {
		Method method = geneCreateMethodDefine(topLevelClass);
		method.setAbstract(false);
		method.getBodyLines().add("entityMapper.insert(record);");
		method.getBodyLines().add("return record;");
		topLevelClass.addMethod(method);
	}

	private TopLevelClass getTopLevelClassShell() {
		CommentGenerator commentGenerator = context.getCommentGenerator();
		FullyQualifiedJavaType type = new FullyQualifiedJavaType(getExtendInterfaceJavaPath() + "Impl");
		TopLevelClass topLevelClass = new TopLevelClass(type);
		topLevelClass.setVisibility(JavaVisibility.PUBLIC);
		topLevelClass.setAbstract(false);
		addJavaDoc(topLevelClass);
		commentGenerator.addJavaFileComment(topLevelClass);
		topLevelClass.getSuperInterfaceTypes().add(service.getType());
		addSpringAnnotation(topLevelClass);
		addMybatisMapper(topLevelClass);
		this.serviceImpl = topLevelClass;
		return topLevelClass;
	}

	/**
	 * 
	 * add MYBATIS mappers
	 * 
	 * @param topLevelClass
	 */
	private void addMybatisMapper(TopLevelClass topLevelClass) {
		FullyQualifiedJavaType mapperType = new FullyQualifiedJavaType(
				getIntrospectedTable().getMyBatis3JavaMapperType());
		Field field = new Field("entityMapper", mapperType);
		field.getAnnotations().add("@Autowired");
		field.setVisibility(JavaVisibility.PROTECTED);
		topLevelClass.addField(field);
		topLevelClass.getImportedTypes().add(mapperType);
	}

	/**
	 * add spring related
	 * 
	 * @param topLevelClass
	 */
	private void addSpringAnnotation(TopLevelClass topLevelClass) {
		topLevelClass.getAnnotations().add("@Service");
		topLevelClass.getAnnotations().add("@Transactional");

		topLevelClass.getImportedTypes()
				.add(new FullyQualifiedJavaType("org.springframework.beans.factory.annotation.Autowired"));
		topLevelClass.getImportedTypes().add(new FullyQualifiedJavaType("org.springframework.stereotype.Service"));
		topLevelClass.getImportedTypes()
				.add(new FullyQualifiedJavaType("org.springframework.transaction.annotation.Transactional"));
	}

	public TopLevelClass getServiceImpl() {
		return serviceImpl;
	}
}
