package com.barley.config.codetable;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import lombok.Setter;

/**
 * 基于Table&mybatis的Codetable定义 
 * 
 * @author peculiar.1@163.com
 * @version $ID: StaticCodeTable.java, V1.0.0 2020年6月27日 下午1:59:40 $
 */
public class DBCodeTable implements CodeTable{
	
	public static final String DEFAULT_TABLE_NAME = "t_code_table";
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Setter
	private String tableName;
	
	@Setter
	private SqlSessionFactory sessionFactory;
	
	@Override
	public void initalize() {
		if(logger.isDebugEnabled()) {
			logger.debug("init db code table, table name is {}", getTableName());
		}
	}

	@Override
	public List<CodeTableItem<?>> loadingCodeTable(String codeTableKey) {
		return null;
	}

	@Override
	public List<CodeTableItem<?>> loadingCodeTableWithCondtion(String codeTableKey, String condtions) {
		return null;
	}
	
	public String getTableName() {
		if(StringUtils.isEmpty(tableName)) {
			return DEFAULT_TABLE_NAME;
		}
		return this.tableName;
	}
	
}
