package com.barley.system.controller.base;

import java.util.List;

import org.barley.web.Resonse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.barley.system.modal.Module;
import com.barley.system.service.base.ModuleService;
import com.barley.system.service.base.searchvo.ModuleSearchVO;
import com.barley.system.transfer.ModuleConverter;
import com.barley.system.transfer.RelModule;
import com.github.pagehelper.PageInfo;

/**
 * @author peculiar.1@163.com
 * @version $ID: com.barley.system.controller.base.ModuleController
 */
@RestController
@RequestMapping("/module")
public class ModuleController {

	@Autowired
	private ModuleService servEntity;

	/**
	 * 
	 * 
	 * @param record
	 * @return
	 */
	@RequestMapping("/create")
	public Resonse create(@RequestBody(required = false) Module record) {
		servEntity.create(record);
		return Resonse.newSucessResult("create success");
	}

	@GetMapping("/rmbykey/{key}")
	public Resonse deleteByKey(@PathVariable("key") Integer keyObj) {
		servEntity.delete(keyObj);
		return Resonse.newSucessResult("delete success");
	}

	@RequestMapping("/update")
	public Resonse maintenance(@RequestBody(required = false) Module record) {
		servEntity.update(record);
		return Resonse.newSucessResult("update success");
	}

	/**
	 * 按照查询条件查询菜单
	 * 
	 * TODO 依据当前人员的权限决定渲染列表
	 * 
	 * @param searchVO
	 * @return
	 */
	@RequestMapping("/query")
	public Resonse query(@RequestBody(required = false) ModuleSearchVO searchVO) {
		ModuleConverter converter = new ModuleConverter();
		List<Module> results = servEntity.searchByVO(searchVO);
		List<RelModule> modules = RelModule.transfer(results);
		List<RelModule> modulesTrs = converter.conversion(modules);
		return Resonse.newSucessResult("query success", modulesTrs);
	}

	@GetMapping("/qrybykey/{key}")
	public Module searchByKey(@PathVariable("key") Integer keyObj) {
		return servEntity.findByPrimaryKey(keyObj);
	}

	@RequestMapping("/pqry")
	public PageInfo<Module> pagingQuery(@RequestBody(required = false) ModuleSearchVO searchVO,
			@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "10") int pageSize) {

		return servEntity.searchByVO(searchVO, page, pageSize);
	}
}