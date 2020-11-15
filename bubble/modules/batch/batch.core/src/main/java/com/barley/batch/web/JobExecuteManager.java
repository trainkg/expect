package com.barley.batch.web;

import java.util.Date;

import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.barley.batch.core.JobService;
import com.barley.batch.core.dayend.DayendJob;

/**
 * JOB 运行管理
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: JobExecuteManager.java, V1.0.0 2020年11月2日 下午8:49:01 $
 */
@RestController
@RequestMapping("/batch/opr")
public class JobExecuteManager {

	/**
	 * 提交一个job
	 */
	@ResponseBody
	@RequestMapping("/submit")
	public void submitJob(Long jobId) {
		JobParametersBuilder builder = new JobParametersBuilder().addDate(DayendJob.PARAM_KEY_DATE, new Date());
		seJob.submitJob(jobId, builder.toJobParameters(), JobService.JOB_SUBMIT_TYPE_ONLINE);
	}

	@Autowired
	private JobService seJob;

}
