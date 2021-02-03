package com.barley.robot.codec.xml;

import java.io.InputStream;

import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.core.codec.CodecException;
import org.springframework.core.codec.DecodingException;

public class Jaxb2xmlDecoder {

	private static final JaxbContextContainer jaxbContexts = new JaxbContextContainer();

	public static Object unmarshal(InputStream is, Class<?> outputClass) {
		try {
			Unmarshaller unmarshaller = initUnmarshaller(outputClass);
			if (outputClass.isAnnotationPresent(XmlRootElement.class)) {
				return unmarshaller.unmarshal(is);
			}
		} catch (UnmarshalException ex) {
			throw new DecodingException("Could not unmarshal XML to " + outputClass, ex);
		} catch (JAXBException ex) {
			throw new CodecException("Invalid JAXB configuration", ex);
		}
		return null;
	}

	private static Unmarshaller initUnmarshaller(Class<?> outputClass) throws CodecException, JAXBException {
		Unmarshaller unmarshaller = jaxbContexts.createUnmarshaller(outputClass);
		// return this.unmarshallerProcessor.apply(unmarshaller);
		return unmarshaller;
	}
}
