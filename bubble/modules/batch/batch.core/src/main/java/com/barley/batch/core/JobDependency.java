package com.barley.batch.core;

import lombok.Getter;
import lombok.Setter;

/**
 * Job 执行依赖 <br>
 * 
   因为Job定义是层级的,只支持同层级的JOB之间（包含叶子节点和非叶子节点） 定义依赖关系, 不支持跨父节点定义JOB依赖关系
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: JobDependency.java, V1.0.0 2020年11月15日 上午8:56:27 $
 */
public class JobDependency {

	@Getter
	@Setter
	private int listId;
	@Getter
	@Setter
	private int jobId;
	@Getter
	@Setter
	private int dependId;
}
