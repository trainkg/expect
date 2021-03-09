package com.barley.pub;

import java.util.List;


/**
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: TreeConversion.java, V1.0.0 2021年3月9日 下午8:30:09 $
 */
public interface TreeConverter<T> {

	/**
	 * 树形转换
	 * 
	 * @param <T>
	 * @param target
	 * @return
	 */
	List<T> conversion(List<T> target);
	
}
