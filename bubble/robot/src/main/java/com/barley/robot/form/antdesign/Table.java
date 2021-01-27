package com.barley.robot.form.antdesign;

import lombok.Getter;
import lombok.Setter;

/**
 * @see https://www.antdv.com/components/table-cn/#Column
 * @author peculiar.1@163.com
 * @version $ID: Column.java, V1.0.0 2021年1月26日 下午10:28:36 $
 */

@Getter
@Setter
public class Table {

	private String tableLayout;
	private String bordered;
	private String[] childrenColumnName;
	private Column[] columns;
	private String components;
	private Object[] dataSource;
	private Boolean defaultExpandAllRows;
	private String[] defaultExpandedRowKeys;
	private String[] expandedRowKeys;
	private String expandedRowRender;
	private String expandIcon;
	private Boolean expandRowByClick;
	private Integer expandIconColumnIndex;
	private String footer;
	private Integer indentSize;
	private Boolean loading;
	private String locale;
	private String pagination;
	private String rowClassName;
	private String rowKey;
	private RowSelection rowSelection;
	private String scroll;
	private Boolean showHeader;
	private String size;
	private String title;
	private String customHeaderRow;
	private String customRow;
	private String getPopupContainer;
	private String transformCellText;

	// event
	private String expandedRowsChange;
	private String change;
	private String expand;

	@Getter
	@Setter
	public class ColumnGroup {
		private String title;
		private String slots;
	}

	@Getter
	@Setter
	public class Pagination {

	}

	@Getter
	@Setter
	public class Scroll {
		private Integer x;
		private Integer y;
		private Boolean scrollToFirstRowOnChange;
	}

	@Getter
	@Setter
	public class RowSelection {
		private Integer columnWidth;
		private String columnTitle;
		private Boolean fixed;
		private String getCheckboxProps;
		private Boolean hideDefaultSelections;
		private String[] selectedRowKeys;
		private Boolean selections;
		private String type;
		private String onChange;
		private String onSelect;
		private String onSelectAll;
		private String onSelectInvert;
	}

	/**
	 * 
	 * D: 列描述数据对象，是 columns 中的一项，Column 使用相同的 API。
	 * 
	 * 
	 * @author peculiar.1@163.com
	 * @version $ID: Table.java, V1.0.0 2021年1月27日 上午9:48:44 $
	 */
	@Getter
	@Setter
	public class Column {

		private String align;
		private Boolean ellipsis;
		private Integer colSpan;
		private String dataIndex;
		private String[] defaultFilteredValue;
		private String filterDropdown;
		private Boolean filterDropdownVisible;
		private Boolean filtered;
		private String[] filteredValue;
		private String filterIcon;
		private Boolean filterMultiple;
		private String[] filters;
		private Boolean fixed;
		private String key;
		private String customRender;
		private Boolean sorter;
		private String sortOrder;
		private String[] sortDirections;
		private String title;
		private String width;
		private String customCell;
		private String customHeaderCell;
		private String onFilter;
		private String slots;
		private String scopedSlots;
	}

	/**
	 * 
	 * D: 自定义选择配置项
	 * 
	 * 
	 * @author peculiar.1@163.com
	 * @version $ID: Table.java, V1.0.0 2021年1月27日 上午9:47:29 $
	 */
	@Getter
	@Setter
	public class Selection {
		private String key;
		private String text;
		private String onSelect;
	}
}
