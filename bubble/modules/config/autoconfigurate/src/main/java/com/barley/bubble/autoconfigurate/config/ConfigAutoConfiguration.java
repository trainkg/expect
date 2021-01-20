package com.barley.bubble.autoconfigurate.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

import com.barley.config.service.CodeTableBaseService;
import com.barley.config.service.CodeTableServiceImpl;

@Configurable
public class ConfigAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean(value = CodeTableBaseService.class)
	public CodeTableBaseService configService() {
		System.out.println("get default");
		return new CodeTableServiceImpl();
	}

}
