package com.barley.robot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.barley.robot.codec.xml.Jaxb2xmlDecoder;

import lombok.extern.slf4j.Slf4j;

/**
 * JAXB
 * 
 * @author peculiar.1@163.com
 * @version $ID: AbstractClasspathXMLResover.java, V1.0.0 2021年2月3日 下午3:59:47 $
 */
@Slf4j
public class SimpleClasspathXMLResover implements ClasspathXMLResover<Object> {

	/**
	 * config xml path
	 */
	public String xmlpath;

	/**
	 * target object
	 */
	public Class<?> clazz;

	public SimpleClasspathXMLResover(String xmlpath, Class<?> clazz) {
		this.xmlpath = xmlpath;
		this.clazz = clazz;
	}

	@Override
	public Object parseXml() {
		InputStream io = null;
		try {
			io = loadingConfig();
			if (io == null) {
				log.info("xml file [{}] not exist.", xmlPath());
				return null;
			}
			return Jaxb2xmlDecoder.unmarshal(io, clazz);
		} catch (FileNotFoundException e) {
			log.info("xml file [{}] not exist. {}", xmlPath(), e.getMessage());
			return null;
		} finally {
			if (io != null) {
				try {
					io.close();
				} catch (IOException e) {
					// ignore
				}
			}
		}

	}

	/**
	 * XML 序列化对象
	 * 
	 * @return
	 */
	protected InputStream loadingConfig() throws FileNotFoundException {
		File file = new File(xmlpath);
		// return SimpleClasspathXMLResover.class.getResourceAsStream(xmlPath());
		return new FileInputStream(file);
	}

	@Override
	public String xmlPath() {
		return xmlpath;
	}

}
