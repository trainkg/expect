package com.barley.batch.core.dayend;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import com.barley.batch.core.CornJob;
import com.barley.batch.core.JobService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author peculiar.1@163.com
 * @version $ID: DayEndJobExecutionListener.java, V1.0.0 2020年11月7日 下午8:54:22 $
 */
@Slf4j
public class DayEndJobExecutionListener implements JobExecutionListener {

	private DefaultDayendJobContext jobContext;

	public DayEndJobExecutionListener(DayendJobContext context) {
		this.jobContext = (DefaultDayendJobContext) context;
	}

	@Override
	public void beforeJob(JobExecution jobExecution) {
		log.info("job {} start", jobExecution.getJobInstance().getJobName());
		String submitType = jobExecution.getJobParameters().getString(JobService.JOB_PARAMETER_SUBMT_TYPE);
		if(JobService.JOB_SUBMIT_TYPE_ONLINE.equals(submitType)) {
			return;
		}
		CornJob lastNote = (CornJob) jobContext.getRootNote().getLastest().getTarget();
		if(jobContext.isRunning() && lastNote.getJobName().contentEquals(jobExecution.getJobInstance().getJobName())) {
			jobContext.completeSubmit();
		}
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		log.info("job {} after", jobExecution.getJobInstance().getJobName());
		String submitType = jobExecution.getJobParameters().getString(JobService.JOB_PARAMETER_SUBMT_TYPE);
		if(JobService.JOB_SUBMIT_TYPE_ONLINE.equals(submitType)) {
			return;
		}
		jobContext.completeJob(jobExecution.getJobInstance().getJobName());
	}

}
