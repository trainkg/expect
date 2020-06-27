package com.barley.config.codetable;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author peculiar.1@163.com
 * @version $ID: ConfigBaseController.java, V1.0.0 2019年10月20日 下午10:04:22 $
 */
@RestController
@RequestMapping(path = "/codetable")
public class CodeTableController {
	
	/**
	 * 加载code table定义
	 * @param codeKey
	 * @return
	 */
	@GetMapping("/{codeKey}")
	@ResponseBody
	public List<CodeTableItem<?>> loadingCodeTable(@PathVariable String codeKey){
		return codeManager.loadCodeTable(codeKey);
	}
	
	@Autowired
	private CodeTableManager codeManager;
}
