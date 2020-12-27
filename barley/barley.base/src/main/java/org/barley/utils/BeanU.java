
package org.barley.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.barley.entity.INView;

public class BeanU {

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
	 * 利用反射机制把bean 转成map，实现BeanUtils.populate的逆过程
	 * 
	 * @param obj
	 * @param pType 筛选字段属性的类型，如果为NULL或者为空则返回全部属性
	 * @return
	 */
	public static Map<String, Object> transBeanToMap(Object obj, List<String> pType) {

		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Field[] fields = obj.getClass().getDeclaredFields();
		try {
			for (Field field : fields) {
				if (pType != null && pType.size() > 0 && !pType.contains(field.getGenericType().toString())) {
					continue;
				}
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
	 * 利用反射机制获取bean 中某些类型的属性字段名
	 * 
	 * @param obj
	 * @param pType 筛选字段属性的类型，如果为NULL或者为空则返回全部属性
	 * @return
	 */
	public static List<String> getProperties(Object obj, List<String> pType) {

		List<String> list = new ArrayList<String>();
		Field[] fields = obj.getClass().getDeclaredFields();
		try {
			for (Field field : fields) {
				// System.out.println(field.getGenericType());// 打印该类的所有属性类型
				if (pType != null && pType.size() > 0 && !pType.contains(field.getGenericType().toString())) {
					continue;
				}
				field.setAccessible(true);
				list.add(field.getName());
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 从List集合数据中取出某属性的返回该属性的List集合
	 * 
	 * @param source       数据源
	 * @param propertyName 字段名
	 * @param result       结果集
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void getOnePropertyListFrom(List<?> source, String propertyName, List result) {
		if (source != null && result != null) {
			for (Object obj : source) {
				try {
					List<Field> fields_all = new ArrayList<Field>();
					if (obj.getClass().getSuperclass() != null) {
						Collections.addAll(fields_all, obj.getClass().getSuperclass().getDeclaredFields());
					}
					Collections.addAll(fields_all, obj.getClass().getDeclaredFields());
					Field[] fields = fields_all.toArray(new Field[0]);
					for (Field field : fields) {
						field.setAccessible(true);
						if (propertyName.equals(field.getName().toString()) && !field.get(obj).equals(null)) {
							result.add(field.get(obj));
						}
					}
				} catch (Exception e) {
					continue;
				}
			}
		}
	}

	/**
	 * List过滤器
	 * 
	 * @param source      源数据
	 * @param excludeData 需要剔除的数据
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static List listFilter(List source, List excludeData) {
		if (excludeData != null && excludeData.size() > 0) {
			for (Object object : excludeData) {
				if (source.contains(object)) {
					source.remove(object);
				}
			}
		}
		return source;
	}

	/**
	 * 判断是否包含某个字段属性
	 * 
	 * @param obj
	 * @param propertyName
	 * @return
	 */
	public static boolean isExist(Object obj, String propertyName) {
		Field[] fields = obj.getClass().getDeclaredFields();
		try {
			for (Field field : fields) {
				if (propertyName.equals(field.getName().toString())) {
					return true;
				}
			}
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// 测试
		List<INView> source = new ArrayList<INView>();
		source.add(new INView("1", "jhl"));
		source.add(new INView("2", "py"));
		source.add(new INView("3", "zyy"));
		source.add(new INView("4", "ss"));
		List<Object> result = new ArrayList<Object>();
		getOnePropertyListFrom(source, "name", result);
		listFilter(result, Arrays.asList(new String[] { "jhl", "py" }));
		if (result != null) {
			for (Object s : result) {
				System.out.println("+++++++++++++--------------" + s);
			}
		} else {
			System.out.println("+++++++++++++--------------对象是NULL");
		}
		/*
		 * Map<String, Object> map = transBeanToMap( new INView(), Arrays.asList(new
		 * String[] { PropertyTypeConstant.JAVA_LANG_STRING, PropertyTypeConstant.INT
		 * })); System.out.println(map.size());
		 */
	}
}
