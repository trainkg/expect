package com.barley.system.transfer;

import com.barley.pub.BasicTreeConverter;

/**
 * 
 * module 转换
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: ModuleConverter.java, V1.0.0 2021年3月9日 下午10:14:58 $
 */
public class ModuleConverter extends BasicTreeConverter<RelModule> {

	private Integer rootId;

	public ModuleConverter(Integer rootId) {
		this.rootId = rootId;
	}

	@Override
	public boolean isRoot(RelModule t) {
		if (rootId != null) {
			return t.getParentId().intValue() == rootId;
		} else {
			return t.getParentId() == null || t.getParentId().intValue() == 0;
		}
	}

	@Override
	public boolean isParent(RelModule t1, RelModule t2) {
		return t2.getParentId().intValue() == t1.getListId().intValue();
	}

	@Override
	public void addChild(RelModule t1, RelModule t2) {
		t1.addModule(t2);
	}

}
