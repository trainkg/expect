package com.barley.robot.modal.frontpage.grid;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;

import com.barley.robot.modal.frontpage.grid.Table.Column;

/**
 * 
 * default implement , 加载全部非主键，非统计字段列表
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: DefaultTableProvider.java, V1.0.0 2021年2月9日 下午3:53:08 $
 */
public class DefaultTableProvider implements TableProvider {

	private ColumnConfigResolver resolver = new SimpleColumnConfigResolver();

	@Override
	public Table retriveTable(IntrospectedTable introspectedTable) {
		Table table = new Table();
		if (introspectedTable.getRules().generatePrimaryKeyClass()) {
			throw new RuntimeException("不支持联合逐渐对象生成table");
		}
		table.setRowKey(introspectedTable.getPrimaryKeyColumns().get(0).getJavaProperty());

		List<Table.Column> listCol = new ArrayList<Table.Column>();

		List<IntrospectedColumn> introspectedColumns = introspectedTable.getAllColumns();
		for (IntrospectedColumn introspectedColumn : introspectedColumns) {
			Column column = table.new Column();
			ColumnConfig config = resolver.resolve(introspectedColumn);
			if (config.isShowInTable()) {
				column.setDataIndex(introspectedColumn.getJavaProperty());
				column.setTitle(config.getTitle());
				listCol.add(column);
			}
		}

		table.setColumns(listCol.toArray(new Table.Column[] {}));
		return table;
	}

}
