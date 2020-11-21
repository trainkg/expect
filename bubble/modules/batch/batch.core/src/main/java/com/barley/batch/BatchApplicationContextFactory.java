package com.barley.batch;

import org.springframework.batch.core.configuration.support.AbstractApplicationContextFactory;
import org.springframework.batch.core.configuration.support.ApplicationContextFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 
  针对 @EnableBatchProcessing(modular = true) 方式, 提供当前application context作为扫描对象, 自动注册JOB 
 * 
 * @author peculiar.1@163.com
 * @version $ID: BatchApplicationContextFactory.java, V1.0.0 2020年11月21日 下午5:18:08 $
 */
@Component
public class BatchApplicationContextFactory extends AbstractApplicationContextFactory implements ApplicationContextFactory {

	@Override
	protected ConfigurableApplicationContext createApplicationContext(ConfigurableApplicationContext parent,
			Object... resources) {
		return null;
	}

}
