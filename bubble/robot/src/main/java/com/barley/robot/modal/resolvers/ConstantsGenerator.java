package com.barley.robot.modal.resolvers;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 常量内生成器
 * 
 * @author peculiar.1@163.com
 * @version $ID: SearchVOGenerator.java, V1.0.0 2020年12月22日 下午5:44:25 $
 */
public class ConstantsGenerator extends AbstractJavaGenerator {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	public static final String PROPERTY_SERVICE_ENABLED = "constants";

	public ConstantsGenerator(String project) {
		super(project);
	}

	public ConstantsGenerator(String project, IntrospectedTable introspectedTable) {
		super(project);
		setIntrospectedTable(introspectedTable);
		setContext(introspectedTable.getContext());
	}

	@Override
	public List<CompilationUnit> getCompilationUnits() {

		List<CompilationUnit> units = new ArrayList<CompilationUnit>();
		String propertyValue = introspectedTable.getTableConfigurationProperty(PROPERTY_SERVICE_ENABLED);
		if ("true".equalsIgnoreCase(propertyValue)) {
			units.add(createRootClass());
		}
		return units;
	}

	private CompilationUnit createRootClass() {
		Interface topClass = getTopLevelClassShell();

		return topClass;
	}

	private Interface getTopLevelClassShell() {
		CommentGenerator commentGenerator = context.getCommentGenerator();
		FullyQualifiedJavaType type = new FullyQualifiedJavaType(getJavaPath());
		Interface topLevelClass = new Interface(type);
		topLevelClass.setVisibility(JavaVisibility.PUBLIC);

		commentGenerator.addJavaFileComment(topLevelClass);
		return topLevelClass;
	}

	public String getJavaPath() {
		String path = context.getJavaModelGeneratorConfiguration().getTargetPackage() + "."
				+ introspectedTable.getFullyQualifiedTable().getDomainObjectName() + "Conts";
		logger.info("Conts top class is {}", path);
		return path;
	}

}
