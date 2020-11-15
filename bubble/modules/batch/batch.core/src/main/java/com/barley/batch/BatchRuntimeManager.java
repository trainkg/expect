package com.barley.batch;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.SmartLifecycle;
import org.springframework.core.Ordered;

import com.barley.batch.core.dayend.DayendJobContext;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: BatchRuntimeManager.java, V1.0.0 2020年11月4日 下午9:51:50 $
 */

@Slf4j
public class BatchRuntimeManager implements Ordered, SmartLifecycle, ApplicationContextAware, InitializingBean {

	private ApplicationContext applicationContext;
	private DayendJobContext dayEndJobContext;
	private int phase = Integer.MIN_VALUE + 1000;

	private boolean autoStartup = true;

	@Setter
	private volatile boolean accept = true;
 
	/**
	 * 保证资源的正常初始化
	 */
	//private Object lifecycleMonitor = new Object();

	private volatile boolean running = false;

	private int order = Ordered.LOWEST_PRECEDENCE;
	
	public BatchRuntimeManager(DayendJobContext dayEndJobContext) {
		this.dayEndJobContext = dayEndJobContext;
	}
	
	@Override
	public void start() {
		log.info("BatchRuntimeManager has start {}", this.applicationContext);
	}

	@Override
	public void stop() {
		log.info("BatchRuntimeManager has stop");
	}

	@Override
	public boolean isRunning() {
		return running;
	}

	@Override
	public boolean isAutoStartup() {
		return this.autoStartup;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("afterPropertiesSet");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	public int getOrder() {
		return order;
	}

	@Override
	public int getPhase() {
		return phase;
	}
	
	/**
	 是否接受提交online方式的job
	 * @return
	 */
	public boolean isAccept() {
		return accept && !dayEndJobContext.isRunning();
	}
}
