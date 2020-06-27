package com.barley.config.codetable;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 静态的Codetable定义 
 * 
 * @author peculiar.1@163.com
 * @version $ID: StaticCodeTable.java, V1.0.0 2020年6月27日 下午1:59:40 $
 */
public class StaticCodeTable implements CodeTable{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void initalize() {
		if(logger.isDebugEnabled()) {
			logger.debug("init static code table");
		}
	}

	@Override
	public List<CodeTableItem<?>> loadingCodeTable(String codeTableKey) {
		return null;
	}

	@Override
	public List<CodeTableItem<?>> loadingCodeTableWithCondtion(String codeTableKey, String condtions) {
		throw new CodeTableException("not support loading with condtions.");
	}
	
	
}
