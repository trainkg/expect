package com.barley.robot.modal;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.TopLevelClass;

public class ConditonTopClass extends TopLevelClass {

	private boolean override;

	public ConditonTopClass(FullyQualifiedJavaType type) {
		super(type);
	}

	public ConditonTopClass(String typeName) {
		super(typeName);
	}

	public void setOverride(boolean override) {
		this.override = override;
	}

	public boolean isOverride() {
		return override;
	}
}
