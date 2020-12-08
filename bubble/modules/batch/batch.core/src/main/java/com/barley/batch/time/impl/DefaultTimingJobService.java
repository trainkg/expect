package com.barley.batch.time.impl;

import java.util.Set;

import javax.validation.constraints.NotNull;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barley.batch.core.CornJob;
import com.barley.batch.core.JobException;
import com.barley.batch.core.JobService;
import com.barley.batch.time.TimingJobService;

/**
 * 
 * @author peculiar.1@163.com
 * @version $ID: DefaultTimingJobService.java, V1.0.0 2020年12月2日 下午4:00:47 $
 */
@Service(DefaultTimingJobService.BEAN_NAME)
public class DefaultTimingJobService implements TimingJobService {
	public static final String BEAN_NAME = "defaultTimingJobService";

	/**
	 * 
	 */
	public void stopJob() {
		// scheduler.unscheduleJob();
	}

	/**
	 * 
	 * 
	 * 
	 * @param group
	 * @param jobName
	 * @return boolean if not allow disabled currently job return false.
	 */
	public boolean deleteJob(@NotNull String group, @NotNull String jobName) {
		try {
			CornJob cornjob = jobService.findJobByName(jobName);
			if(cornjob == null) {
				return false;
			}
			
			if (!cornjob.isAllowStop()) {
				return false;
			}
			jobService.disableJob(cornjob.getListId());
			Set<JobKey> jobkeys = scheduler.getJobKeys(GroupMatcher.jobGroupEquals(group));
			if (jobkeys == null || jobkeys.size() == 0) {
				throw new JobException("no exits group name with " + group);
			}
			for (JobKey jobKey : jobkeys) {
				if (jobKey.getName().contentEquals(jobName)) {
					scheduler.deleteJob(jobKey);
					break;
				}
			}
		} catch (SchedulerException e) {
			throw new JobException(e);
		}
		return true;
	}

	@Autowired
	private JobService jobService;
	@Autowired
	private Scheduler scheduler;
}
