package com.barley.batch.core.dayend;

/**
 * day end job 
 * @author peculiar.1@163.com
 * @version $ID: DayendJob.java, V1.0.0 2020年11月5日 下午8:36:09 $
 */
public interface DayendJobContext {

	
	/**
	 * 执行day end
	 */
	void executeDayend();
	
	
	/**
	 * dayend batch是否正在执行
	 * 
	 * 
	 * @return
	 */
	boolean isRunning();
	
	/**
	 * 
	 * 标记day end已经开始
	 */
	void markStart();
	
	/**
	 * 标记day end 已经结束
	 */
	void markEnd();
}
