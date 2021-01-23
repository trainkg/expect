package com.barley.robot.modal;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;

public class CondtionInterface extends Interface {

	private boolean override;

	public CondtionInterface(FullyQualifiedJavaType type) {
		super(type);
	}

	public CondtionInterface(String type) {
		super(type);
	}

	public void setOverride(boolean override) {
		this.override = override;
	}

	public boolean isOverride() {
		return override;
	}

}
