package com.barley.robot.test;

import com.barley.robot.codec.xml.Jaxb2xmlEncoder;
import com.barley.robot.modal.frontpage.form.FormConfig;
import com.barley.robot.modal.frontpage.form.FormConfig.Feild;
import com.barley.robot.modal.frontpage.form.FormConfig.Group;

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
		
		group1.setTitle("test1");
		for (int i = 0; i < 4; i++) {
			Feild feild1 = new Feild();
			feild1.setLabel("label"+i);
			group1.getFeilds().add(feild1);
		}
		
		config.getGroups().add(group1);
		
		
		Group group2 = new Group();
		group1.setTitle("test2");
		for (int i = 0; i < 4; i++) {
			Feild feild1 = new Feild();
			feild1.setLabel("label"+i);
			group2.getFeilds().add(feild1);
		}
		
		config.getGroups().add(group2);
		
		Jaxb2xmlEncoder.encodeValue(System.out, config);
	}
}
