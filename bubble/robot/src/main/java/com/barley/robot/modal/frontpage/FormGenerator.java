package com.barley.robot.modal.frontpage;

import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;

import com.barley.robot.modal.frontpage.form.FormConfig;
import com.barley.robot.modal.frontpage.form.FormConfig.Feild;
import com.barley.robot.modal.frontpage.grid.GridTitleStrategy;
import com.barley.robot.modal.frontpage.grid.SplitTitleStrategy;

/**
 * D: 表单自动生成
 * 
 * @author peculiar.1@163.com
 * @version $ID: SearchVOGenerator.java, V1.0.0 2020年12月22日 下午5:44:25 $
 */
public class FormGenerator extends AbstractFrontGenerator<FormConfig> {

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
	protected FormConfig getModel() {
		FormConfig config = (FormConfig) getConfig(FormConfig.class);
		if (config == null) {
			config = extractConfigFrom(introspectedTable);
		}
		return config;
	}

	/*
	 * 基于表信息作出的默认配置
	 */
	private FormConfig extractConfigFrom(IntrospectedTable introspectedTable) {
		FormConfig config = new FormConfig();
		List<IntrospectedColumn> introspectedColumns = introspectedTable.getAllColumns();
		for (IntrospectedColumn introspectedColumn : introspectedColumns) {
			Feild feild = new Feild();
			feild.setName(introspectedColumn.getJavaProperty());
			feild.setLabel(splitStrategy.getTitle(introspectedColumn));

			config.getFileds().add(feild);
		}
		return config;
	}

	@Override
	protected String getFeatureConfigName() {
		return PROPERTY_SERVICE_ENABLED;
	}

	@Override
	protected String getFileName() {
		return "/commons/" + introspectedTable.getFullyQualifiedTable().getDomainObjectName().toLowerCase() + "-form.vue";
	}

	@Override
	String getTagName() {
		return null;
	}

	@Override
	protected String getXmlConfigPath() {
		return "src/main/resources/robot/module/" + introspectedTable.getTableConfiguration().getTableName().toLowerCase() + "-form.xml";
	}

}
