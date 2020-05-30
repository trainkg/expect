package com.barley.config.form.layout;

import com.barley.config.form.FormRenderer;

/**
 * 
 * @author peculiar.1@163.com
 * @version $ID: RenderManager.java, V1.0.0 2020年5月22日 下午9:40:51 $
 */
public class RenderManager {
    
    private FormRenderer render;
    
    /**
     * 获取渲染后form模板
     * @return
     */
    public String render() {
        return getRender().renderTemplete();
    }
    
    public FormRenderer getRender() {
        if(render == null) {
            return new SimpleFormRenderer();
        }
        return render;
    }
}
