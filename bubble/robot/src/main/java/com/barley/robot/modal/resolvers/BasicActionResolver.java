package com.barley.robot.modal.resolvers;

import java.util.ArrayList;
import java.util.Date;
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
import org.mybatis.generator.codegen.AbstractJavaGenerator;

import com.barley.robot.utils.DataFormatterUtils;

/**
 * 
 * basic action generate.
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: BasicActionResolver.java, V1.0.0 2020年12月26日 下午9:11:46 $
 */
public class BasicActionResolver extends AbstractJavaGenerator {

	private TopLevelClass action;
	private Interface entityService;
	private CompilationUnit searchVO;
	// private ActionResolverConfiguration config;

	public BasicActionResolver(String project) {
		super(project);
	}

	public BasicActionResolver(String property, IntrospectedTable introspectedTable, Interface serviceExt) {
		super(property);
		setIntrospectedTable(introspectedTable);
		setContext(introspectedTable.getContext());
		this.entityService = serviceExt;
	}

	@Override
	public List<CompilationUnit> getCompilationUnits() {
		List<CompilationUnit> units = new ArrayList<CompilationUnit>();
		units.add(createAction());
		return units;
	}

	public CompilationUnit createAction() {
		TopLevelClass clazz = getTopLevelClassShell();
		addCreateMethod(clazz);
		addDeleteMethod(clazz);
		addAmendMethod(clazz);
		addSearchMethod(clazz);
		addSearchByKeyMethod(clazz);
		return clazz;
	}

	private void addSearchByKeyMethod(TopLevelClass clazz) {
		Method method = new Method("searchByKey");
		FullyQualifiedJavaType returntype = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
		method.setReturnType(returntype);

		boolean isConbinkey = false;
		FullyQualifiedJavaType paramType = null;
		if (introspectedTable.getRules().generatePrimaryKeyClass()) {
			paramType = new FullyQualifiedJavaType(introspectedTable.getPrimaryKeyType());
			isConbinkey = true;
		} else if (introspectedTable.hasPrimaryKeyColumns()) {
			if (introspectedTable.getPrimaryKeyColumns() == null
					|| introspectedTable.getPrimaryKeyColumns().size() == 0) {
				return;
			}
			paramType = introspectedTable.getPrimaryKeyColumns().get(0).getFullyQualifiedJavaType();
		}
		Parameter param = new Parameter(paramType, "keyObj");
		String domain = "qrybykey";
		String requestMapping = "@RequestMapping(\"/" + domain + "\")";
		if (!isConbinkey) {
			requestMapping = "@GetMapping(\"/" + domain + "/{key}\")";
			clazz.getImportedTypes()
					.add(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.PathVariable"));
			clazz.getImportedTypes()
					.add(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.GetMapping"));

			param.addAnnotation("@PathVariable(\"key\")");
		} else {
			param.addAnnotation("@ModelAttribute");
			clazz.getImportedTypes()
					.add(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.ModelAttribute"));
		}
		method.getParameters().add(param);
		method.getBodyLines().add("return servEntity.findByPrimaryKey(keyObj);");
		method.setVisibility(JavaVisibility.PUBLIC);
		method.addAnnotation(requestMapping);
		clazz.addMethod(method);

		clazz.getImportedTypes().add(returntype);
		clazz.getImportedTypes().add(paramType);
	}

	private void addSearchMethod(TopLevelClass clazz) {
		Method method = new Method("query");
		method.setVisibility(JavaVisibility.PUBLIC);
		method.addAnnotation("@RequestMapping(\"/query\")");
		Parameter parameter = new Parameter(searchVO.getType(), "searchVO");
		parameter.addAnnotation("@ModelAttribute");
		method.getParameters().add(parameter);
		FullyQualifiedJavaType returnType = new FullyQualifiedJavaType("org.barley.web.Resonse");
		method.setReturnType(returnType);
		method.getBodyLines().add("List<" + introspectedTable.getFullyQualifiedTable().getDomainObjectName()
				+ "> results = servEntity.searchByVO(searchVO);");
		method.getBodyLines().add("return Resonse.newSucessResult(\"query success\",results);");
		clazz.addMethod(method);
		clazz.getImportedTypes().add(parameter.getType());
		clazz.getImportedTypes().add(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
		clazz.getImportedTypes().add(returnType);
		clazz.getImportedTypes().add(new FullyQualifiedJavaType("java.util.List"));
		clazz.getImportedTypes()
				.add(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.ModelAttribute"));
	}

	private void addAmendMethod(TopLevelClass clazz) {
		Method method = new Method("maintenance");
		method.setVisibility(JavaVisibility.PUBLIC);
		method.addAnnotation("@RequestMapping(\"/update\")");
		Parameter parameter = new Parameter(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()),
				"record");
		parameter.addAnnotation("@ModelAttribute");
		method.getParameters().add(parameter);
		FullyQualifiedJavaType returnType = new FullyQualifiedJavaType("org.barley.web.Resonse");
		method.setReturnType(returnType);
		method.getBodyLines().add("servEntity.update(record);");
		method.getBodyLines().add("return Resonse.newSucessResult(\"update success\");");
		clazz.addMethod(method);
		clazz.getImportedTypes().add(parameter.getType());
		clazz.getImportedTypes().add(returnType);
		clazz.getImportedTypes()
				.add(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.ModelAttribute"));
	}

	private void addCreateMethod(TopLevelClass clazz) {
		Method method = new Method("create");
		method.setVisibility(JavaVisibility.PUBLIC);
		method.addAnnotation("@RequestMapping(\"/create\")");
		Parameter parameter = new Parameter(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()),
				"record");
		parameter.addAnnotation("@ModelAttribute");
		method.getParameters().add(parameter);

		FullyQualifiedJavaType returnType = new FullyQualifiedJavaType("org.barley.web.Resonse");
		method.setReturnType(returnType);
		method.getBodyLines().add("servEntity.create(record);");
		method.getBodyLines().add("return Resonse.newSucessResult(\"create success\");");
		clazz.addMethod(method);
		clazz.getImportedTypes().add(parameter.getType());
		clazz.getImportedTypes().add(returnType);
		clazz.getImportedTypes()
				.add(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.ModelAttribute"));
	}

	private void addDeleteMethod(TopLevelClass clazz) {
		Method method = new Method("deleteByKey");
		FullyQualifiedJavaType returnType = new FullyQualifiedJavaType("org.barley.web.Resonse");
		method.setReturnType(returnType);

		boolean isConbinkey = false;
		FullyQualifiedJavaType paramType = null;
		if (introspectedTable.getRules().generatePrimaryKeyClass()) {
			paramType = new FullyQualifiedJavaType(introspectedTable.getPrimaryKeyType());
			isConbinkey = true;
		} else if (introspectedTable.hasPrimaryKeyColumns()) {
			if (introspectedTable.getPrimaryKeyColumns() == null
					|| introspectedTable.getPrimaryKeyColumns().size() == 0) {
				return;
			}
			paramType = introspectedTable.getPrimaryKeyColumns().get(0).getFullyQualifiedJavaType();
		}
		Parameter param = new Parameter(paramType, "keyObj");
		String domain = "rmbykey";
		String requestMapping = "@RequestMapping(\"/" + domain + "\")";
		if (!isConbinkey) {
			requestMapping = "@GetMapping(\"/" + domain + "/{key}\")";
			clazz.getImportedTypes()
					.add(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.PathVariable"));
			clazz.getImportedTypes()
					.add(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.GetMapping"));

			param.addAnnotation("@PathVariable(\"key\")");
		} else {
			param.addAnnotation("@ModelAttribute");
			clazz.getImportedTypes()
					.add(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.ModelAttribute"));
		}
		method.getParameters().add(param);
		method.getBodyLines().add("servEntity.delete(keyObj);");
		method.getBodyLines().add("return Resonse.newSucessResult(\"delete success\");");
		method.setVisibility(JavaVisibility.PUBLIC);
		method.addAnnotation(requestMapping);
		clazz.addMethod(method);

		clazz.getImportedTypes().add(returnType);
		clazz.getImportedTypes().add(paramType);

	}

	private TopLevelClass getTopLevelClassShell() {
		CommentGenerator commentGenerator = context.getCommentGenerator();
		FullyQualifiedJavaType type = new FullyQualifiedJavaType(getJavaPath());
		TopLevelClass topLevelClass = new TopLevelClass(type);
		topLevelClass.setVisibility(JavaVisibility.PUBLIC);
		topLevelClass.setAbstract(false);
		addGeneratedJavaDoc(topLevelClass);
		commentGenerator.addJavaFileComment(topLevelClass);
		addSpringAnnotation(topLevelClass);
		addEntityService(topLevelClass);
		return topLevelClass;
	}

	private void addGeneratedJavaDoc(TopLevelClass topLevelClass) {
		topLevelClass.getJavaDocLines().add("/**");
		topLevelClass.getJavaDocLines().add(" * @author peculiar.1@163.com");
		topLevelClass.getJavaDocLines()
				.add(" * @version $ID: " + topLevelClass.getType().getFullyQualifiedName() + " create date "
						+ DataFormatterUtils.format(new Date(System.currentTimeMillis()),
								DataFormatterUtils.FMT_YYYY_MM_DD_HH_MM_SS));
		topLevelClass.getJavaDocLines().add(" */");
	}

	private String getJavaPath() {
		String javapath = context.getProperty("controller.dir");
		String path = javapath + "." + introspectedTable.getFullyQualifiedTable().getDomainObjectName() + "Controller";
		return path;
	}

	private void addEntityService(TopLevelClass topLevelClass) {
		Field field = new Field("servEntity", entityService.getType());
		field.getAnnotations().add("@Autowired");
		field.setVisibility(JavaVisibility.PRIVATE);
		topLevelClass.getImportedTypes()
				.add(new FullyQualifiedJavaType("org.springframework.beans.factory.annotation.Autowired"));
		topLevelClass.getImportedTypes().add(entityService.getType());
		topLevelClass.addField(field);
	}

	private void addSpringAnnotation(TopLevelClass topLevelClass) {
		topLevelClass.addAnnotation("@RestController");
		StringBuffer sb = new StringBuffer(introspectedTable.getFullyQualifiedTable().getDomainObjectName());
		sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
		String domain = sb.toString();
		String requestMapping = "@RequestMapping(\"/" + domain + "\")";
		topLevelClass.addAnnotation(requestMapping);
		topLevelClass.getImportedTypes()
				.add(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.RestController"));
		topLevelClass.getImportedTypes()
				.add(new FullyQualifiedJavaType("org.springframework.web.bind.annotation.RequestMapping"));
	}

	public TopLevelClass getAction() {
		return action;
	}

	public void setSearchVO(CompilationUnit searchVO) {
		this.searchVO = searchVO;
	}

}
