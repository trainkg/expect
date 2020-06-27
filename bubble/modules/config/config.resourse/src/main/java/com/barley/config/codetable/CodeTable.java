package com.barley.config.codetable;

import java.util.List;

/**
 * CodeTable 
 * @author peculiar.1@163.com
 * @version $ID: CodeTable.java, V1.0.0 2020年6月27日 上午10:32:32 $
 */
public interface CodeTable {
	
	/**
	 * CodeTable 初始化
	 * 
	 */
	void initalize();
	
	
	/**
	 * 依据 codetable id 定义，加载codeTable显示项
	 * @param codeTableKey
	 * @return
	 */
	List<CodeTableItem> loadingCodeTable(String codeTableKey);
	
	
	/**
	 * 有条件加载
	 * @param codeTableKey
	 * @param condtions
	 * @return
	 */
	List<CodeTableItem> loadingCodeTableWithCondtion(String codeTableKey, String condtions);
}
