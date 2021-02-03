package com.barley.robot.modal.frontpage;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;

import com.barley.robot.modal.frontpage.grid.ColumnConfig;
import com.barley.robot.modal.frontpage.grid.ColumnConfigResolver;
import com.barley.robot.modal.frontpage.grid.SimpleColumnConfigResolver;
import com.barley.robot.modal.frontpage.grid.Table;
import com.barley.robot.modal.frontpage.grid.Table.Column;
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
	private ColumnConfigResolver resolver = new SimpleColumnConfigResolver();
	
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
		Table table = new Table();
		if(introspectedTable.getRules().generatePrimaryKeyClass()) {
			throw new RuntimeException("不支持联合逐渐对象生成table");
		}
		table.setRowKey(introspectedTable.getPrimaryKeyColumns().get(0).getJavaProperty());
		
		List<Table.Column> listCol = new ArrayList<Table.Column>();
		
		List<IntrospectedColumn> introspectedColumns =  introspectedTable.getAllColumns();
		for (IntrospectedColumn introspectedColumn : introspectedColumns) {
			Column column = table.new Column();
			ColumnConfig config = resolver.resolve(introspectedColumn);
			if(config.isShowInTable()) {
				column.setDataIndex(introspectedColumn.getJavaProperty());
				column.setTitle(config.getTitle());
				listCol.add(column);	
			}
		}

		table.setColumns(listCol.toArray(new Table.Column[] {}));
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
