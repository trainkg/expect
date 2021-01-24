package com.barley.robot.modal.frontpage;

import java.util.HashMap;
import java.util.Map;

/**
 * D: 表单自动生成
 * 
 * @author peculiar.1@163.com
 * @version $ID: SearchVOGenerator.java, V1.0.0 2020年12月22日 下午5:44:25 $
 */
public class FormGenerator extends AbstractFrontGenerator<Map<String, Object>> {

	// private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * enable this generator
	 */
	public static final String PROPERTY_SERVICE_ENABLED = "template.form";

	@Override
	protected String getTemplatePath() {
		return "form.ftl";
	}

	@Override
	protected Map<String, Object> getModel() {
		Map<String, Object> map = new HashMap<String, Object>();
		return map;
	}

	@Override
	protected String getFeatureConfigName() {
		return PROPERTY_SERVICE_ENABLED;
	}

	@Override
	protected String getFileName() {
		return "/"+introspectedTable.getFullyQualifiedTable().getDomainObjectName().toLowerCase() + "-form.vue";
	}

}
