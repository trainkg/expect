package org.barley.web.utils;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CommonUtils {
	/**
	 * 判断是否是数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		if (str == null) {
			return false;
		} else {
			for (int i = 0; i < str.length(); i++) {
				if (!Character.isDigit(str.charAt(i))) {
					return false;
				}
			}
			return true;
		}
	}

	/**
	 * 利用反射机制把bean 转成map，实现BeanUtils.populate的逆过程
	 * 
	 * @param obj
	 * @return
	 */
	public static Map<String, Object> transBeanToMap(Object obj) {

		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Field[] fields = obj.getClass().getDeclaredFields();
		try {
			for (Field field : fields) {
				field.setAccessible(true);
				map.put(field.getName(), field.get(obj));
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 获取一定数量的未使用的登陆账号
	 * 
	 * @param nameList 已经存在的纯数字帐号
	 * @param count    数量
	 * @return
	 */
	public static List<String> getUnUsedNameList(List<String> nameList, Integer count) {
		int start = 1000001;
		if (nameList != null && nameList.size() > 0 && isNumeric(nameList.get(nameList.size() - 1))) {
			start = Integer.valueOf(nameList.get(nameList.size() - 1));
		}
		List<String> nameList_new = new ArrayList<String>(0);
		while (nameList_new.size() < count) {
			if (!nameList.contains(String.valueOf(start))) {
				nameList_new.add(String.valueOf(start));
			}
			start++;
		}
		return nameList_new;
	}

	/**
	 * 时间格式转换
	 * 
	 * @param date  原时间
	 * @param style 格式
	 * @return
	 */
	public static String formatDateAs(Date date, String style) {
		String str = null;
		SimpleDateFormat sdf = new SimpleDateFormat(style);
		if (date != null) {
			str = sdf.format(date);
		}
		return str;
	}

}
