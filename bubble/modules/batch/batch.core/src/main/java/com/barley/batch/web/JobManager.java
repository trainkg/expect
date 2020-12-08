package com.barley.batch.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.barley.batch.core.CornJob;
import com.barley.batch.core.JobService;

/**
 * Job 定义管理
 * 
 * @author peculiar.1@163.com
 * @version $ID: JobManager.java, V1.0.0 2020年10月28日 下午9:35:24 $
 */
@RestController
@RequestMapping("/batch/repo")
public class JobManager {

	/**
	 * 加载系统中所有的JOB
	 * 
	 * @return
	 */
	@ResponseBody
	@GetMapping("/jobs")
	public List<CornJob> listAllJobs() {
		List<CornJob> jobs = seJob.findAllJob();
		return jobs;
	}
	
	/**
	 * @Description 加载系统中所有的激活的JOB列表
	 * @return
	 */
	@ResponseBody
	@GetMapping("/actjobs")
	public List<CornJob> listAllActiveJobs() {
		List<CornJob> jobs = seJob.findAllActiveJob();
		return jobs;
	}

	@ResponseBody
	@GetMapping("/jobs/{jobId}")
	public CornJob retrieveJob(@PathVariable Long jobId) {
		return seJob.findJob(jobId);
	}
	
	@Autowired
	private JobService seJob;

}
