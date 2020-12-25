package com.barley.robot.modal.resolvers;

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
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.codegen.AbstractJavaGenerator;

import com.barley.robot.utils.DataFormatterUtils;

import jline.internal.Log;

/**
 * 
 * Search VO 生成器
 * 
 * @author peculiar.1@163.com
 * @version $ID: SearchVOGenerator.java, V1.0.0 2020年12月22日 下午5:44:25 $
 */
public class SearchVOGenerator extends AbstractJavaGenerator {

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
		commentGenerator.addJavaFileComment(topLevelClass);
		return topLevelClass;
	}

	public String getJavaPath() {
		String path = basicServiceDir + getSubPackage() + ".searchvo."
				+ introspectedTable.getFullyQualifiedTable().getDomainObjectName() + "SearchVO";
		Log.info("service top class is {}", path);
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
}
