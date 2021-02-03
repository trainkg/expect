package com.barley.robot.modal;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 数据库模型中的统计字段
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: AutomaticConstants.java, V1.0.0 2021年2月3日 下午8:22:46 $
 */
public class AutomaticConstants {

	public static final List<String> audit_list = new ArrayList<String>();

	static {
		audit_list.add("insertBy");
		audit_list.add("updateBy");
		audit_list.add("insertTime");
		audit_list.add("updateTime");
	}

	public static boolean contains(String fileName) {
		return audit_list.contains(fileName);
	}
}
