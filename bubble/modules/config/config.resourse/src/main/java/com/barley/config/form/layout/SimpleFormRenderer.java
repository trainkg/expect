package com.barley.config.form.layout;

import org.slf4j.Logger;

import com.barley.config.form.FormRenderer;
import com.barley.config.form.FormService;
import com.barley.config.form.LayoutStrategy;
import com.barley.config.form.define.BaseForm;

import lombok.Getter;

/**
 * 简单的form 渲染器
 * 
 * @author peculiar.1@163.com
 * @version $ID: SimpleFormRenderer.java, V1.0.0 2020年5月22日 下午9:24:51 $
 */
public class SimpleFormRenderer implements FormRenderer<SimpleRenderConfig> {
    
    public static final LayoutStrategy DEFAULT_LAYOUT = new SimpleLayoutStrategy();
    private Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());
    
    private LayoutStrategy strategy;
    private FormService    formService;
    
    @Getter
    private String formKey;

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
    
    public SimpleFormRenderer(String formKey, FormService formService) {
    	this.formKey = formKey;
    	this.formService = formService;
	}

    @Override
    public SimpleRenderConfig renderTemplete() {
        return renderInternal();
    }

    private SimpleRenderConfig renderInternal() {
        if(!supportStrategy(getStrategy())) {
            throw new RuntimeException("Not support current strategy " + getStrategy().getClass());
        }
        SimpleRenderConfig  config = new SimpleRenderConfig();
        BaseForm form = loadingFromDefine();
        form.setFromKey("test");
        config.setForm(form);
        config.setLayoutStrategy(getStrategy());
        logger.info("use strategy {}", getStrategy().getClass().getSimpleName());
        return config;
    }
    
    private BaseForm loadingFromDefine() {
    	return new BaseForm();
    }
}
