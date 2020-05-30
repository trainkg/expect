package com.barley.config.form.layout;

import com.barley.config.form.LayoutStrategy;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author peculiar.1@163.com
 * @version $ID: SimpleLayoutStrategy.java, V1.0.0 2020年5月22日 下午9:25:45 $
 */
public class SimpleLayoutStrategy implements LayoutStrategy {
    
    public final static int DEF_COL_SIZE = 4;
    
    /**
     * 每行元素数量
     */
    @Setter
    @Getter
    private int colSize =  DEF_COL_SIZE;
}
