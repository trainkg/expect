package com.barley.config.codetable;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;

import lombok.Setter;

/**
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: CodeTableManager.java, V1.0.0 2020年6月27日 下午1:54:18 $
 */
public class CodeTableManager implements InitializingBean {

	@Setter
	public List<CodeTable> codeTables = new ArrayList<CodeTable>();

	public CodeTableManager() {
	}

	private void init() {
		codeTables();
		register();
	}

	private void codeTables() {
		codeTables.forEach(v -> {
			v.initalize();
		});
	}

	/*
	 * register CodeTables
	 */
	private void register() {
	}

	/**
	 * 依据CodeTable定义加载,codeTable显示项
	 * 
	 * @param codeTableKey
	 * @return
	 */
	public List<CodeTableItem<?>> loadCodeTable(String codeTableKey) {
		return null;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		init();
	}
}
