package com.barley.config.form.layout;

import com.barley.config.form.LayoutStrategy;

import lombok.Getter;

/**
 * 
 * 简单的渲染，table 布局方式 <P>
 * 
 * 由于真正的渲染是前端执行的， 渲染策略只负责渲染策略的配置信息
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: SimpleLayoutStrategy.java, V1.0.0 2020年5月22日 下午9:25:45 $
 */
public class SimpleLayoutStrategy implements LayoutStrategy {
    
	/**
	 * VUE 前端栅格采用的是24格,所以尽量设置可以被24整除的数 
	 */
    public final static int DEF_COL_SIZE = 4;
    
    /**
     * 每行元素数量
     */
    @Getter
    private int colSize =  DEF_COL_SIZE;

    public SimpleLayoutStrategy() {
	}
    
	public SimpleLayoutStrategy(int colSize) {
		super();
		this.colSize = colSize;
	}
    
}
