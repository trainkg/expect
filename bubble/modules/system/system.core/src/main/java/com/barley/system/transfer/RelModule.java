package com.barley.system.transfer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.barley.system.modal.Module;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * 建立依赖关系的module数据模型
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: RelModule.java, V1.0.0 2021年3月9日 下午11:00:47 $
 */
public class RelModule extends Module {
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	public List<Module> modules = new ArrayList<Module>();

	/**
	 * add module
	 * 
	 * @param e
	 */
	public void addModule(Module e) {
		this.modules.add(e);
	}

	/**
	 * 
	 * @param modules
	 * @return
	 */
	public static List<RelModule> transfer(List<Module> modules) {
		List<RelModule> rs = new ArrayList<RelModule>(modules.size());

		modules.forEach(module -> {
			RelModule relModule = new RelModule();
			BeanUtils.copyProperties(module, relModule);
			rs.add(relModule);
		});

		return rs;
	}
}
