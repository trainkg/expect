package com.barley.finance.formula;

/**
 *
 * 支持初始化载入的公式
 * @author peculiar.1@163.com
 * @version $ID: LifecyleFormula.java, V1.0.0 2020年9月28日 下午8:26:24 $
 */
public abstract class LifecyleFormula implements Formula {
	
	public LifecyleFormula() {
		initialize();
	}
	
	public abstract void initialize();
	
}
