package com.barley.config.form.layout;

import com.barley.config.form.LayoutStrategy;
import com.barley.config.form.define.BaseForm;

import lombok.Getter;
import lombok.Setter;

public class SimpleRenderConfig {
	
	public static final String DEFAULT_RANDER_NAME = "BALRY.SUMPLE.LAYOUT";
	
	@Getter
	private String renderName = DEFAULT_RANDER_NAME;
	
	@Getter
	@Setter
	private BaseForm form;

	@Getter
	@Setter
	private LayoutStrategy layoutStrategy;
}
