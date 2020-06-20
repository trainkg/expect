package com.barley.flow.resourse;

import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("barly.taskresource")
@RequestMapping("/flow")
public class TaskResource {
	
	
	/**
	 * 获取所有流程
	 */
	@GetMapping("/task/list")
	@ResponseBody
	public void startProcess(@PathVariable String defined) {
		//attribute map
		Map<String,Object> attrMap = new HashMap<String, Object>();
		attrMap.put("assignee", "1");
		seruntime.startProcessInstanceById(defined,"yuanyu",attrMap);
	}
	
	
	
	@Autowired
	private RuntimeService seruntime;
	
}
