package com.barley.robot.modal;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;

public class CondtionTopClass extends TopLevelClass {

	private boolean override;

	public CondtionTopClass(FullyQualifiedJavaType type) {
		super(type);
	}

	public CondtionTopClass(String typeName) {
		super(typeName);
	}

	public void setOverride(boolean override) {
		this.override = override;
	}

	public boolean isOverride() {
		return override;
	}
}
