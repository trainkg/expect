package com.barley.robot.modal.resolvers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.codegen.AbstractJavaGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.barley.robot.mybatis.BubbleMybatisPlugin;

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
		createConstants(topClass);
		return topClass;
	}

	private void createConstants(Interface topClass) {
		if(introspectedTable.getPrimaryKeyColumns().size() == 0) {
			return;
		}
		Map<String, Object[]> constants = getConstants();

		constants.keySet().forEach(key -> {
			Field field = new Field("CONST_" + key.toUpperCase(), introspectedTable.getPrimaryKeyColumns().get(0).getFullyQualifiedJavaType());
			FullyQualifiedJavaType type = introspectedTable.getPrimaryKeyColumns().get(0).getFullyQualifiedJavaType();
			Object value =  constants.get(key)[0];
			Object desc =  constants.get(key)[1];
			if (Long.class.getName().equals(type.toString())) {
				field = new Field("CONST_" + key.toUpperCase(), new FullyQualifiedJavaType("long"));
				field.setInitializationString(String.valueOf(value));
			}
			
			if (String.class.getName().equals(type.toString())) {
				field = new Field("CONST_" + key.toUpperCase(), new FullyQualifiedJavaType("String"));
				field.setInitializationString("\""+String.valueOf(value)+"\"");
			}
			
			if (Integer.class.getName().equals(type.toString())) {
				field = new Field("CONST_" + key.toUpperCase(), new FullyQualifiedJavaType("int"));
				field.setInitializationString(String.valueOf(value));
			}
			
			if (Short.class.getName().equals(type.toString())) {
				field = new Field("CONST_" + key.toUpperCase(), new FullyQualifiedJavaType("short"));
				field.setInitializationString(String.valueOf(value));
			}
			
			field.getJavaDocLines().add("/**");
			field.getJavaDocLines().add("* Describe: " + desc);
			field.getJavaDocLines().add("*/");
			field.setStatic(true);
			field.setVisibility(JavaVisibility.PUBLIC);
			field.setFinal(true);
			
			topClass.addField(field);
		});
	}

	protected Map<String, Object[]> getConstants() {

		String name_key = "code";
		String value_key = "list_id";
		String desc_key = "name";
		
		String cfgName = introspectedTable.getTableConfigurationProperty("const_name");
		String cfgListId = introspectedTable.getTableConfigurationProperty("const_list_id");
		String cfgCode = introspectedTable.getTableConfigurationProperty("const_code");

		if (cfgName != null && !"".equals(cfgName)) {
			desc_key = cfgName;
		}

		if (cfgListId != null && !"".equals(cfgListId)) {
			value_key = cfgListId;
		}

		if (cfgCode != null && !"".equals(cfgCode)) {
			name_key = cfgCode;
		}
		
		
		String name_key_1 = name_key;
		String value_key_1 = value_key;
		String desc_key_1 = desc_key;
		
		Map<String, Object[]> retMap;
		Connection connection = null;
		try {
			connection = BubbleMybatisPlugin.getConnection(context);
			QueryRunner queryRunner = new QueryRunner();
			String sql = "select * from " + introspectedTable.getTableConfiguration().getTableName();

			retMap = queryRunner.query(connection, sql, new ResultSetHandler<Map<String, Object[]>>() {
				@Override
				public Map<String, Object[]> handle(ResultSet rs) throws SQLException {
					Map<String, Object[]> map = new HashMap<String, Object[]>();
					while (rs.next()) {
						String key = rs.getString(name_key_1);
						Object value = rs.getObject(value_key_1);
						String desc = "";
						try {
							desc = rs.getString(desc_key_1);
						} catch (Exception e) {
						}
						map.put(key, new Object[] { value, desc });
					}
					return map;
				}
			});
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			BubbleMybatisPlugin.closeConnection(connection);
		}
		return retMap;
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
