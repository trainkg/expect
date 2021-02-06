package com.barley.robot.test;

import com.barley.robot.codec.xml.Jaxb2xmlEncoder;
import com.barley.robot.modal.frontpage.form.FormConfig;
import com.barley.robot.modal.frontpage.form.FormConfig.Field;
import com.barley.robot.modal.frontpage.form.FormConfig.Group;
import com.barley.robot.modal.frontpage.form.FormConfigWapper;

/**
 * 
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: Jaxb2Test.java, V1.0.0 2021年2月3日 下午5:03:17 $
 */
public class Jaxb2Test {
	public static void main(String[] args) {
		FormConfig config = new FormConfig();
		Group group1 = new Group();

		group1.setHeader("test1");
		group1.setKey("1");
		for (int i = 0; i < 4; i++) {
			Field feild1 = new Field();
			feild1.setLabel("label" + i);
			feild1.setName("name" + i);
			group1.getFields().add(feild1);
		}

		config.getGroups().add(group1);

		Group group2 = new Group();
		group2.setKey("2");
		group2.setHeader("test2");
		for (int i = 0; i < 4; i++) {
			Field feild1 = new Field();
			feild1.setLabel("label" + i);
			feild1.setName("name" + i);
			group2.getFields().add(feild1);
		}

		config.getGroups().add(group2);
		System.out.println(new FormConfigWapper(config).getEditFeilds());
		Jaxb2xmlEncoder.encodeValue(System.out, config);
	}
}
