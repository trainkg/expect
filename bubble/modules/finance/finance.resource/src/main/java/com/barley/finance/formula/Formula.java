package com.barley.finance.formula;

import java.math.BigDecimal;

/**
 * 公式 
 * @author peculiar.1@163.com
 * @version $ID: Formula.java, V1.0.0 2020年9月28日 下午8:25:17 $
 */
public interface Formula {
	
	/**
	 * formula computed
	 * @param context
	 * @return
	 */
	BigDecimal computed();
	
}
