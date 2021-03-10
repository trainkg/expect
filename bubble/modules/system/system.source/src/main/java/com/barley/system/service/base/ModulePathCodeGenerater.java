package com.barley.system.service.base;

import com.barley.pub.CodeGenerater;
import com.barley.pub.PathCode;
import com.barley.system.modal.Module;

/**
 * 
 * module path code生成器
 * 
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: ModuleCodeGenerater.java, V1.0.0 2021年3月10日 下午4:51:59 $
 */
public class ModulePathCodeGenerater extends PathCode {

	private Module module;
	private Module parent;
	private CodeGenerater codeGen;

	public ModulePathCodeGenerater(Module parent, Module current) {
		this.module = current;
		this.parent = parent;
		createCodeGen();
	}

	public void createCodeGen() {
		ModuleCodeGenerater mcodeg = new ModuleCodeGenerater();
		mcodeg.setModule(module);
		this.codeGen = mcodeg;
	}

	@Override
	protected String genereteNodeCode() {
		return codeGen.generate();
	}

	@Override
	protected String loadParentCode() {
		return parent != null ? parent.getPath() : "";
	}

}
