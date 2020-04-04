package com.barley.resourse.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author peculiar.1@163.com
 * @version $ID: ConfigBaseController.java, V1.0.0 2019年10月20日 下午10:04:22 $
 */
@RestController
public class ConfigBaseController {
	
	/**
	 * 自动
	 * @return
	 */
	@RequestMapping("/config/list")
	public Map<String,Object> retriveProcessInfo(){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("name", "fortest");
		return map;
	}
}
