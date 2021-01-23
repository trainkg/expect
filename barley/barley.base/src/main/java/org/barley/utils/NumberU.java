
package org.barley.utils;

import java.text.NumberFormat;

public class NumberU {

	/**
	 * 数字类型转化
	 * 
	 * @param value 被转化对象
	 * @param i     保留小数位数
	 * @return
	 */
	public static String numberFormatAs(Number value, Integer i) {
		if (i == null || i < 0) {
			i = 0;
		}
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(i);
		nf.setMinimumFractionDigits(i);
		nf.setGroupingUsed(false);
		try {
			return nf.format(value);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 数字类型转化
	 * 
	 * @param value   被转化对象
	 * @param i       保留小数位数
	 * @param minNeed 最少小数位数保留与否
	 * @return
	 */
	public static String numberFormatAs(Number value, Integer i, boolean minNeed) {
		if (i == null || i < 0) {
			i = 0;
		}
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(i);
		if (minNeed) {
			nf.setMinimumFractionDigits(i);
		}
		nf.setGroupingUsed(false);
		try {
			return nf.format(value);
		} catch (Exception e) {
			return "";
		}
	}
}
