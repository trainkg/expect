package com.barley.robot.modal.resolvers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barley.robot.utils.DataFormatterUtils;

/**
 * 
 * Search VO 生成器
 * 
 * @author peculiar.1@163.com
 * @version $ID: SearchVOGenerator.java, V1.0.0 2020年12月22日 下午5:44:25 $
 */
public class SearchVOGenerator extends AbstractJavaGenerator {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * searchvo
	 */
	private String basicServiceDir = "";

	public SearchVOGenerator(String project) {
		super(project);
	}

	public SearchVOGenerator(String project, IntrospectedTable introspectedTable) {
		super(project);
		setIntrospectedTable(introspectedTable);
		setContext(introspectedTable.getContext());
		basicServiceDir = context.getProperty("service.dir");
	}

	@Override
	public List<CompilationUnit> getCompilationUnits() {

		List<CompilationUnit> units = new ArrayList<CompilationUnit>();
		units.add(createSearchVO());
		return units;
	}

	private CompilationUnit createSearchVO() {
		TopLevelClass topClass = getTopLevelClassShell();
		addDefaultConstructor(topClass);
		addSearchField(topClass);
		topClass.getImportedTypes().add(new FullyQualifiedJavaType("lombok.Getter"));
		topClass.getImportedTypes().add(new FullyQualifiedJavaType("lombok.Setter"));
		return topClass;
	}

	private void addSearchField(TopLevelClass topClass) {
		List<IntrospectedColumn> columns = getColumnsInThisClass();
		for (IntrospectedColumn introspectedColumn : columns) {
			Field field = getBasicJavaBeansField(introspectedColumn);
			field.addAnnotation("@Getter");
			field.addAnnotation("@Setter");
			topClass.addField(field);
		}
	}

	private TopLevelClass getTopLevelClassShell() {
		CommentGenerator commentGenerator = context.getCommentGenerator();
		FullyQualifiedJavaType type = new FullyQualifiedJavaType(getJavaPath());
		TopLevelClass topLevelClass = new TopLevelClass(type);
		topLevelClass.setVisibility(JavaVisibility.PUBLIC);
		topLevelClass.getJavaDocLines().add("/**");
		topLevelClass.getJavaDocLines().add(" * @author peculiar.1@163.com");
		topLevelClass.getJavaDocLines().add(" * @version $ID: " + getJavaPath() + " create date " + DataFormatterUtils
				.format(new Date(System.currentTimeMillis()), DataFormatterUtils.FMT_YYYY_MM_DD_HH_MM_SS));
		topLevelClass.getJavaDocLines().add(" */");

		// extend
		if (introspectedTable.getExampleType() != null) {
			topLevelClass.setSuperClass(introspectedTable.getExampleType());
			topLevelClass.getImportedTypes().add(new FullyQualifiedJavaType(introspectedTable.getExampleType()));
		}

		createSearchBuild(topLevelClass);

		commentGenerator.addJavaFileComment(topLevelClass);
		return topLevelClass;
	}

	private void createSearchBuild(TopLevelClass topLevelClass) {
		// CriteriaBuilder
		FullyQualifiedJavaType interfazz = new FullyQualifiedJavaType("org.barley.mybatis.CriteriaBuilder");
		topLevelClass.addSuperInterface(interfazz);
		// add Implment

		Method build = new Method("build");
		build.setVisibility(JavaVisibility.PUBLIC);
		build.setAbstract(false);

		bulidBody(build, topLevelClass);

		topLevelClass.addMethod(build);
	}

	/**
	 * @see org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl
	 * @param build
	 */
	private void bulidBody(Method build, TopLevelClass topLevelClass) {
		build.getBodyLines().add("Criteria criteria = createCriteria();");
		List<IntrospectedColumn> columns = getColumnsInThisClass();
		for (IntrospectedColumn introspectedColumn : columns) {
			String feildName = introspectedColumn.getJavaProperty();
			introspectedColumn.getFullyQualifiedJavaType();
			// String dbColumnName = introspectedColumn.getJdbcTypeName();
//			logger.info("feildName {}", feildName);
//			logger.info("java type {}", introspectedColumn.getFullyQualifiedJavaType());

			// support list
			if (Long.class.getName().equals(introspectedColumn.getFullyQualifiedJavaType().toString())) {
				addNormalBody(feildName, introspectedColumn, build);
			}
			if (Boolean.class.getName().equals(introspectedColumn.getFullyQualifiedJavaType().toString())) {
				addNormalBody(feildName, introspectedColumn, build);
			}
			if (String.class.getName().equals(introspectedColumn.getFullyQualifiedJavaType().toString())) {
				topLevelClass.getImportedTypes()
						.add(new FullyQualifiedJavaType("org.apache.commons.lang3.StringUtils"));
				build.getBodyLines().add("");
				build.getBodyLines().add("if(StringUtils.isNotEmpty(" + feildName + ")) {");
				build.getBodyLines().add("criteria." + getMethodEqName(introspectedColumn) + "(" + feildName + ");");
				build.getBodyLines().add("}");
			}
			if (Date.class.getName().equals(introspectedColumn.getFullyQualifiedJavaType().toString())) {
				addNormalBody(feildName, introspectedColumn, build);
			}
			if (BigDecimal.class.getName().equals(introspectedColumn.getFullyQualifiedJavaType().toString())) {
				addNormalBody(feildName, introspectedColumn, build);
			}
			if (Double.class.getName().equals(introspectedColumn.getFullyQualifiedJavaType().toString())) {
				addNormalBody(feildName, introspectedColumn, build);
			}
			if (Integer.class.getName().equals(introspectedColumn.getFullyQualifiedJavaType().toString())) {
				addNormalBody(feildName, introspectedColumn, build);
			}
			if (Float.class.getName().equals(introspectedColumn.getFullyQualifiedJavaType().toString())) {
				addNormalBody(feildName, introspectedColumn, build);
			}
			if (Short.class.getName().equals(introspectedColumn.getFullyQualifiedJavaType().toString())) {
				addNormalBody(feildName, introspectedColumn, build);
			}
		}
	}

	private void addNormalBody(String feildName, IntrospectedColumn introspectedColumn, Method build) {
		build.getBodyLines().add("");
		build.getBodyLines().add("if(" + feildName + " != null) {");
		build.getBodyLines().add("criteria." + getMethodEqName(introspectedColumn) + "(" + feildName + ");");
		build.getBodyLines().add("}");
	}

	public String getJavaPath() {
		String path = basicServiceDir + getSubPackage() + ".searchvo."
				+ introspectedTable.getFullyQualifiedTable().getDomainObjectName() + "SearchVO";
		logger.info("service top class is {}", path);
		return path;
	}

	public String getSubPackage() {
		if (StringUtils.isNotBlank(introspectedTable.getFullyQualifiedTable().getDomainObjectSubPackage())) {
			return "." + introspectedTable.getFullyQualifiedTable().getDomainObjectSubPackage();
		}
		return "";
	}

	private List<IntrospectedColumn> getColumnsInThisClass() {
		List<IntrospectedColumn> introspectedColumns;
		if (includePrimaryKeyColumns()) {
			if (includeBLOBColumns()) {
				introspectedColumns = introspectedTable.getAllColumns();
			} else {
				introspectedColumns = introspectedTable.getNonBLOBColumns();
			}
		} else {
			if (includeBLOBColumns()) {
				introspectedColumns = introspectedTable.getNonPrimaryKeyColumns();
			} else {
				introspectedColumns = introspectedTable.getBaseColumns();
			}
		}
		return introspectedColumns;
	}

	private boolean includePrimaryKeyColumns() {
		// return !introspectedTable.getRules().generatePrimaryKeyClass() &&
		// introspectedTable.hasPrimaryKeyColumns();
		return false;
	}

	private boolean includeBLOBColumns() {
		return !introspectedTable.getRules().generateRecordWithBLOBsClass() && introspectedTable.hasBLOBColumns();
	}

	private static Field getBasicJavaBeansField(IntrospectedColumn introspectedColumn) {
		FullyQualifiedJavaType fqjt = introspectedColumn.getFullyQualifiedJavaType();
		String property = introspectedColumn.getJavaProperty();

		Field field = new Field(property, fqjt);
		field.setVisibility(JavaVisibility.PRIVATE);

		return field;
	}

	/**
	 * 
	 * @set Method
	 *      org.mybatis.generator.codegen.mybatis3.model.ExampleGenerator.getSetEqualMethod(IntrospectedColumn
	 *      introspectedColumn)
	 * @param introspectedColumn
	 * @return
	 */
	private String getMethodEqName(IntrospectedColumn introspectedColumn) {
		StringBuilder sb = new StringBuilder();
		sb.append(introspectedColumn.getJavaProperty());
		sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		sb.insert(0, "and"); //$NON-NLS-1$
		sb.append("EqualTo");
		return sb.toString();
	}
}
