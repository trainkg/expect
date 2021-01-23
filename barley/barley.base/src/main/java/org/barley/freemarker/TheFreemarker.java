
package org.barley.freemarker;

import java.io.File;
import java.io.IOException;

import freemarker.template.Template;

public class TheFreemarker extends AbstractFreemarker {
	protected static final String TEMP = "temp";

	public TheFreemarker(@SuppressWarnings("rawtypes") Class clazz, String pathPrefix) throws IOException {
		super(clazz, pathPrefix);
	}

	@Override
	public void doSettings() {
		System.out.println("you can do something here");
	}

	public static void main(String[] args) throws Exception {
		TheFreemarker tf = new TheFreemarker(TheFreemarker.class, TEMP + File.separator);
		Template temp = null;
		try {
			temp = tf.configuration.getTemplate("test.ftl");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (temp == null) {
			throw new Exception("不存在的模板名称");
		} else {
			System.out.println(temp.getName());
			tf.doSettings();
		}
	}

}
