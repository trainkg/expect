package com.barley.pub;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: TreeConversion.java, V1.0.0 2021年3月9日 下午8:30:09 $
 * @param <T>
 */
public abstract class BasicTreeConverter<T> implements TreeConverter<T> {

	/**
	 * 树形转换
	 */
	@Override
	public List<T> conversion(List<T> target) {
		if (target == null || target.isEmpty()) {
			throw new IllegalArgumentException("target must have data.");
		}
		
		List<T> rs = new ArrayList<T>();
		
		for (T t : target) {
			if(isRoot(t)) {
				rs.add(t);
				fillChild(t,target);
			}
		}
		return rs;
	}
	
	/**
	 * 
	 * 子节点信息填充
	 * @param t
	 * @param target
	 */
	void fillChild(T t, List<T> target) {
		extractChild(t,target);
	}
	
	
	private void extractChild(T t, List<T> target) {
		for (T t2 : target) {
			if(isParent(t, t2)) {
				extractChild(t2, target);
				addChild(t, t2);
			}
		}
	}

	/**
	 * 
	 * 当前数据是否是根节点
	 * @param t
	 * @return
	 */
	public abstract boolean isRoot(T t);
	
	/**
	 * 
	 * @param t
	 * @return 如果T1是T2的parent节点, 返回true, 否则返回false.
	 */
	public abstract boolean isParent(T t1, T t2);
	
	
	/**
	 * 添加子元素, 将T2加入到T1的子元素列表中
	 * @param t1
	 * @param t2
	 */
	public abstract void addChild(T t1, T t2);
}
