package com.barley.robot.modal.frontpage;

import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;

import com.barley.robot.modal.AutomaticConstants;
import com.barley.robot.modal.frontpage.form.FormConfig;
import com.barley.robot.modal.frontpage.form.FormConfigWapper;
import com.barley.robot.modal.frontpage.form.FormConfig.Field;
import com.barley.robot.modal.frontpage.grid.GridTitleStrategy;
import com.barley.robot.modal.frontpage.grid.SplitTitleStrategy;

/**
 * D: 表单自动生成
 * 
 * @author peculiar.1@163.com
 * @version $ID: SearchVOGenerator.java, V1.0.0 2020年12月22日 下午5:44:25 $
 */
public class FormGenerator extends AbstractFrontGenerator<FormConfigWapper> {

	// private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * enable this generator
	 */
	public static final String PROPERTY_SERVICE_ENABLED = "template.form";
	public GridTitleStrategy splitStrategy = new SplitTitleStrategy();

	@Override
	protected String getTemplatePath() {
		return "form.ftl";
	}

	@Override
	protected FormConfigWapper getModel() {
		FormConfig config = (FormConfig) getConfig(FormConfig.class);
		List<IntrospectedColumn> primarykey = introspectedTable.getPrimaryKeyColumns();
		FormConfigWapper wapper = new FormConfigWapper(config);
		StringBuffer sb = new StringBuffer(introspectedTable.getFullyQualifiedTable().getDomainObjectName());
		sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
		wapper.setBeanName(sb.toString());
		if (config == null) {
			config = extractConfigFrom(introspectedTable);
		} else {
			if (config.isAutoLoadFeild()) {
				List<IntrospectedColumn> introspectedColumns = introspectedTable.getAllColumns();
				for (IntrospectedColumn introspectedColumn : introspectedColumns) {
					if(AutomaticConstants.contains(introspectedColumn.getJavaProperty())) {
						continue;
					}
					if(primarykey.contains(introspectedColumn)) {
						continue;
					}
					boolean contain = config.contain(introspectedColumn.getJavaProperty());
					if(!contain) {
						Field feild = new Field();
						feild.setName(introspectedColumn.getJavaProperty());
						feild.setLabel(splitStrategy.getTitle(introspectedColumn));
						feild.setDefaultValue(introspectedColumn.getDefaultValue());
						feild.setRequired(!introspectedColumn.isNullable());
						//config.getFields().add(feild);
						wapper.addField(feild);
					}
				}
			}
		}
		return wapper;
	}
	
	
	
	/*
	 * 基于表信息作出的默认配置
	 */
	private FormConfig extractConfigFrom(IntrospectedTable introspectedTable) {
		FormConfig config = new FormConfig();
		List<IntrospectedColumn> introspectedColumns = introspectedTable.getAllColumns();
		for (IntrospectedColumn introspectedColumn : introspectedColumns) {
			Field feild = new Field();
			feild.setName(introspectedColumn.getJavaProperty());
			feild.setLabel(splitStrategy.getTitle(introspectedColumn));
			config.getFields().add(feild);
		}
		return config;
	}

	@Override
	protected String getFeatureConfigName() {
		return PROPERTY_SERVICE_ENABLED;
	}

	@Override
	protected String getFileName() {
		return "/commons/" + introspectedTable.getFullyQualifiedTable().getDomainObjectName().toLowerCase()
				+ "-form.vue";
	}

	@Override
	String getTagName() {
		return null;
	}

	@Override
	protected String getXmlConfigPath() {
		return "src/main/resources/robot/module/"
				+ introspectedTable.getTableConfiguration().getTableName().toLowerCase() + "-form.xml";
	}

}
