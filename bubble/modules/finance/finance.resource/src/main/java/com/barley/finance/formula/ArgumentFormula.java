package com.barley.finance.formula;

import java.math.BigDecimal;

/**
 *
 * 支持初始化载入的公式
 * @author peculiar.1@163.com
 * @version $ID: LifecyleFormula.java, V1.0.0 2020年9月28日 下午8:26:24 $
 */
public abstract class ArgumentFormula implements Formula {
	
	@Override
	public BigDecimal computed() {
		return null;
	}
	
	
	/**
	  使用入参作为计算公式的入参条件 
	 * @param args
	 * @return
	 */
	public abstract BigDecimal _computed(Object... args);
	
}
