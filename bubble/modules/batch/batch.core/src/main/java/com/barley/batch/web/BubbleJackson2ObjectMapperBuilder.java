package com.barley.batch.web;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;

/**
 * 自定义JSON序列化设定
 * 
 * {@link JacksonAutoConfiguration}
 * 
 * @author peculiar.1@163.com
 * @version $ID: BubbleJackson2ObjectMapperBuilder.java, V1.0.0 2020年11月20日 下午5:03:47 $
 */
@Component
public class BubbleJackson2ObjectMapperBuilder implements Jackson2ObjectMapperBuilderCustomizer {

	@Override
	public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
		jacksonObjectMapperBuilder.simpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//忽略字段为空
		jacksonObjectMapperBuilder.failOnEmptyBeans(false);
		/*
		 * 可以注册一些对象的序列化设定和反序列化
		 */
		//jacksonObjectMapperBuilder.serializers(serializers);
		//jacksonObjectMapperBuilder.deserializers(deserializers)
	}

}
