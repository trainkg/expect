package com.barley.system.service.base;

import com.barley.pub.CodeGenerater;
import com.barley.system.modal.Module;

/**
 * 
 * module code生成器,默认使用list id (使用上需要先产生list id)
 * 
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: ModuleCodeGenerater.java, V1.0.0 2021年3月10日 下午4:51:59 $
 */
public class ModuleCodeGenerater implements CodeGenerater {

	private Module module;

	public ModuleCodeGenerater() {
	}

	@Override
	public String generate() {
		return module.getListId().toString();
	}
	
	public void setModule(Module module) {
		this.module = module;
	}

}
