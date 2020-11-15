package com.barley.config.codetable;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import com.barley.config.mappers.CodeTableMapper;

import lombok.Setter;

/**
 * 基于Table&mybatis的Codetable定义
 * 
 * @author peculiar.1@163.com
 * @version $ID: StaticCodeTable.java, V1.0.0 2020年6月27日 下午1:59:40 $
 */
public class DBCodeTable implements CodeTable {

	public static final String DEFAULT_TABLE_NAME = "t_code_table";
	public static final String CACHE_NAMESPACE = "CA_CODE_TABLE"; 
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Setter
	private String tableName;

	@Setter
	private JdbcTemplate jdbctemplate;

	@Override
	public void initalize() {
		if (logger.isDebugEnabled()) {
			logger.debug("init db code table, table name is {}", getTableName());
		}
	}
	
	@Cacheable(cacheNames = CACHE_NAMESPACE, key = "#codeTableKey")
	@Override
	public List<CodeTableItem> loadingCodeTable(String codeTableKey) {
		return loadingCodeTableWithCondtion(codeTableKey, null);
	}
	
	@Override
	public List<CodeTableItem> loadingCodeTableWithCondtion(String codeTableKey, String condtions) {
		Long listId = null;
		// decode code table key
		listId = Long.valueOf(codeTableKey);
		com.barley.config.modal.CodeTable codeTable = mapperCodeTable.selectByPrimaryKey(listId);
		if (codeTable != null) {
			//
			String value = codeTable.getCodeValue() +" cid ," + codeTable.getCodeName() +" description ";
			String table =  codeTable.getTableName();
			String seachParams = "";
			// replace original conditions.
			if(!StringUtils.isEmpty(condtions)) {
				seachParams = condtions;
			}
			String querySql = "select "+ value +" from " + table + " " + seachParams;
			List<CodeTableItem> list = jdbctemplate.query(querySql, new BeanPropertyRowMapper<CodeTableItem>(CodeTableItem.class));
			return list;
		}
		return null;
	}

	public String getTableName() {
		if (StringUtils.isEmpty(tableName)) {
			return DEFAULT_TABLE_NAME;
		}
		return this.tableName;
	}

	@Setter
	private CodeTableMapper mapperCodeTable;

}
