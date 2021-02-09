package com.barley.robot.modal.frontpage;

import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;

import com.barley.robot.modal.AutomaticConstants;
import com.barley.robot.modal.frontpage.form.FormConfig.Field;
import com.barley.robot.modal.frontpage.grid.DefaultTableProvider;
import com.barley.robot.modal.frontpage.grid.GridTitleStrategy;
import com.barley.robot.modal.frontpage.grid.SplitTitleStrategy;
import com.barley.robot.modal.frontpage.grid.Table;
import com.barley.robot.modal.frontpage.grid.TableProvider;
import com.barley.robot.modal.frontpage.grid.TableWapper;
import com.barley.robot.modal.frontpage.picker.PickerConfig;
import com.barley.robot.modal.frontpage.picker.PickerConfigWapper;

import lombok.extern.slf4j.Slf4j;

/**
 * D: 选择器自动生成
 * 
 * @author peculiar.1@163.com
 * @version $ID: SearchVOGenerator.java, V1.0.0 2020年12月22日 下午5:44:25 $
 */
@Slf4j
public class PickerGenerator extends AbstractFrontGenerator<PickerConfigWapper> {

	public static final String PROPERTY_SERVICE_ENABLED = "template.picker";
	public GridTitleStrategy splitStrategy = new SplitTitleStrategy();
	private TableProvider provider = new DefaultTableProvider();
	@Override
	protected String getTemplatePath() {
		return "picker.ftl";
	}

	@Override
	protected PickerConfigWapper getModel() {
		PickerConfig config = (PickerConfig) getConfig(PickerConfig.class);

		// form configuration
		if (config == null) {
			config = extractConfigFrom(introspectedTable);
		} else {
			List<IntrospectedColumn> primarykey = introspectedTable.getPrimaryKeyColumns();
			if (config.isAutoLoadFeild()) {
				List<IntrospectedColumn> introspectedColumns = introspectedTable.getAllColumns();
				for (IntrospectedColumn introspectedColumn : introspectedColumns) {
					if (AutomaticConstants.contains(introspectedColumn.getJavaProperty())) {
						continue;
					}
					if (primarykey.contains(introspectedColumn)) {
						continue;
					}
					boolean contain = config.contain(introspectedColumn.getJavaProperty());
					if (!contain) {
						Field feild = new Field();
						feild.setName(introspectedColumn.getJavaProperty());
						feild.setLabel(splitStrategy.getTitle(introspectedColumn));
						feild.setDefaultValue(introspectedColumn.getDefaultValue());
						feild.setRequired(!introspectedColumn.isNullable());
						config.getFields().add(feild);
					}
				}
			}
		}
		
		StringBuffer sb = new StringBuffer(introspectedTable.getFullyQualifiedTable().getDomainObjectName());
		sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
		PickerConfigWapper wapper = new PickerConfigWapper(sb.toString(), config);

		// loading table configuration
		loadingTableConfig(wapper);
				
		return wapper;
	}

	private void loadingTableConfig(PickerConfigWapper wapper) {
		Table table = provider.retriveTable(introspectedTable);
		wapper.setTableWapper(new TableWapper(table, wapper.getBeanName()));
	}

	private PickerConfig extractConfigFrom(IntrospectedTable introspectedTable) {
		PickerConfig config = new PickerConfig();
		return config;
	}

	@Override
	protected String getFeatureConfigName() {
		return PROPERTY_SERVICE_ENABLED;
	}

	@Override
	protected String getFileName() {
		String fileName = "/commons/" + introspectedTable.getFullyQualifiedTable().getDomainObjectName().toLowerCase()
				+ "-picker.vue";
		log.info("{} generate grid file name is {}", introspectedTable.getTableConfiguration().getTableName(),
				fileName);
		return fileName;
	}

	@Override
	protected String getXmlConfigPath() {
		return "src/main/resources/robot/module/"
				+ introspectedTable.getTableConfiguration().getTableName().toLowerCase() + "-picker.xml";
	}

	@Override
	String getTagName() {
		return null;
	}

}
