package com.barley.batch.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.barley.batch.query.Response;
import com.barley.batch.time.TimingJobService;

/**
 * 
 * 定时任务JOB操作
 * 
 * @author peculiar.1@163.com
 * @version $ID: TimingJobsManager.java, V1.0.0 2020年12月2日 下午3:56:01 $
 */

@RestController
@RequestMapping("/batch/timing")
public class TimingJobsManager {

	/**
	 * disable timing job.
	 * 
	 * @param group
	 * @param jobName
	 * @return
	 */
	@PostMapping("/del")
	public Response<Boolean> deleteTimingJob(@RequestParam String group, @RequestParam String jobName) {
		Boolean rs = servTimingJob.deleteJob(group, jobName);
		return new Response<Boolean>().result(rs)
				.status(rs ? Response.RESPONSE_CODE_SUCCESS : Response.RESPONSE_CODE_FAILED);
	}

	@Autowired
	private TimingJobService servTimingJob;
}
