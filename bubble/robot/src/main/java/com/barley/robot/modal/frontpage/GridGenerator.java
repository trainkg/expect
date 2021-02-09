package com.barley.robot.modal.frontpage;

import com.barley.robot.modal.frontpage.grid.DefaultTableProvider;
import com.barley.robot.modal.frontpage.grid.Table;
import com.barley.robot.modal.frontpage.grid.TableProvider;
import com.barley.robot.modal.frontpage.grid.TableWapper;

import lombok.extern.slf4j.Slf4j;

/**
 * D: 表格
 * 
 * <p>
 * 	表模型中那些数据是需要进入table中那些字段是进行对外展示， 在与数据层面，显示转换的逻辑是可以在前端执行，但是这样脱离了后端管理，所以我们尽量不再前端进行数据显示转换
 * 	只是在前端进行相关的UI效果/样式方便的定义
 * </p>
 * @warn 不支持联合组件生成table
 * @author peculiar.1@163.com
 * @version $ID: SearchVOGenerator.java, V1.0.0 2020年12月22日 下午5:44:25 $
 */
@Slf4j
public class GridGenerator extends AbstractFrontGenerator<TableWapper> {

	
	public static final String PROPERTY_SERVICE_ENABLED = "template.grid";
	private TableProvider provider = new DefaultTableProvider();
	
	@Override
	protected String getTemplatePath() {
		return "grids/basic-grid.ftl";
	}
	
	
	/**
	 * 
	 * loading table information
	 */
	@Override 
	protected TableWapper getModel() {
		Table table = provider.retriveTable(introspectedTable);
		StringBuffer sb = new StringBuffer(introspectedTable.getFullyQualifiedTable().getDomainObjectName());
		sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
		return new TableWapper(table,sb.toString());
	}

	@Override
	protected String getFeatureConfigName() {
		return PROPERTY_SERVICE_ENABLED;
	}

	@Override
	protected String getFileName() {
		String fileName = "/commons/" + introspectedTable.getFullyQualifiedTable().getDomainObjectName().toLowerCase()
				+ "-table.vue";
		log.info("{} generate grid file name is {}", introspectedTable.getTableConfiguration().getTableName(),
				fileName);
		return fileName;
	}

	@Override
	String getTagName() {
		return null;
	}

}
