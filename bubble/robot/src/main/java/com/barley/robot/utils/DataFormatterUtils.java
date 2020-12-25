package com.barley.robot.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataFormatterUtils {
	
	public static final String FMT_YYYY_MM_DD = "yyyy-MM-dd";
	public static final String FMT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	
	public static String format(Date date, String formatter) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatter);
		return sdf.format(date);
	}
}
