package com.barley.config.form;

/**
 * formRender
 * 
 * @author peculiar.1@163.com
 * @version $ID: Renderer.java, V1.0.0 2020年5月22日 下午9:22:35 $
 */
public interface FormRenderer {
    
    /**
     * 判断当前渲染器是否指定指定策略
     * @param strategy
     * @return
     */
    boolean supportStrategy(LayoutStrategy strategy);
    
    /**
     * 渲染form 模板
     * @return
     */
    String  renderTemplete();
}
