package com.barley.config.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.barley.config.form.layout.SimpleFormRenderer;
import com.barley.config.form.layout.SimpleRenderConfig;

/**
 * 
 * @author peculiar.1@163.com
 * @version $ID: RenderManager.java, V1.0.0 2020年5月22日 下午9:40:51 $
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class FormEngine {

	/**
	 * 获取form的渲染方式
	 * 
	 * @param formkey
	 * @return
	 */
	public SimpleRenderConfig loadFormRender(String formkey) {
		return new SimpleFormRenderer(formkey, seForm).renderTemplete();
	}

	@Autowired
	private FormService seForm;
}
