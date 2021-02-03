package com.barley.robot.codec.xml;

import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import javax.xml.bind.JAXBException;
import javax.xml.bind.MarshalException;
import javax.xml.bind.Marshaller;

import org.springframework.core.codec.CodecException;
import org.springframework.core.codec.EncodingException;
import org.springframework.util.ClassUtils;

/**
 * see spring Jaxb2XmlEncoder
 * 
 * @author peculiar.1@163.com
 * @version $ID: Jaxb2xmlEncoder.java, V1.0.0 2021年2月3日 下午4:43:39 $
 */
public class Jaxb2xmlEncoder {
	
	
	private final static JaxbContextContainer jaxbContexts = new JaxbContextContainer();
	
	public static void encodeValue(OutputStream outputStream, Object value) {
		try {
			Class<?> clazz = ClassUtils.getUserClass(value);
			Marshaller marshaller = initMarshaller(clazz);
			marshaller.marshal(value, outputStream);
		}
		catch (MarshalException ex) {
			throw new EncodingException("Could not marshal " + value.getClass() + " to XML", ex);
		}
		catch (JAXBException ex) {
			throw new CodecException("Invalid JAXB configuration", ex);
		}
	}

	private static  Marshaller initMarshaller(Class<?> clazz) throws CodecException, JAXBException {
		Marshaller marshaller = jaxbContexts.createMarshaller(clazz);
		marshaller.setProperty(Marshaller.JAXB_ENCODING, StandardCharsets.UTF_8.name());
		//marshaller = this.marshallerProcessor.apply(marshaller);
		return marshaller;
	}
}
