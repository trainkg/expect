package com.barley.batch.time;

import javax.validation.constraints.NotNull;

/**
 * @author peculiar.1@163.com
 * @version $ID: TimingJobService.java, V1.0.0 2020年12月2日 下午3:59:42 $
 */
public interface TimingJobService {
	
	
	/**
	 * delete timing job.
	 * @param group
	 * @param jobName
	 */
	public boolean deleteJob(@NotNull String group, @NotNull String jobName);
	
	
}
