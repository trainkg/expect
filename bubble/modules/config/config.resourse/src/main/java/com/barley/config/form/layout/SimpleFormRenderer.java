package com.barley.config.form.layout;

import org.slf4j.Logger;

import com.barley.config.form.FormRenderer;
import com.barley.config.form.LayoutStrategy;

import lombok.Setter;

/**
 * 简单的form 渲染器
 * 
 * @author peculiar.1@163.com
 * @version $ID: SimpleFormRenderer.java, V1.0.0 2020年5月22日 下午9:24:51 $
 */
public class SimpleFormRenderer implements FormRenderer {
    
    public static final LayoutStrategy DEFAULT_LAYOUT = new SimpleLayoutStrategy();
    private Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
    
    @Setter
    private LayoutStrategy strategy;

    public LayoutStrategy getStrategy() {
        if (strategy == null) {
            return DEFAULT_LAYOUT;
        }
        return strategy;
    }

    @Override
    public boolean supportStrategy(LayoutStrategy strategy) {
        if (strategy instanceof SimpleLayoutStrategy) {
            return true;
        }
        return false;
    }

    @Override
    public String renderTemplete() {
        return renderInternal();
    }

    private String renderInternal() {
        if(!supportStrategy(getStrategy())) {
            throw new RuntimeException("Not support current strategy " + getStrategy().getClass());
        }
        
        logger.info("use strategy {}", getStrategy().getClass().getSimpleName());
        return "<test>this<test>";
    }
}
