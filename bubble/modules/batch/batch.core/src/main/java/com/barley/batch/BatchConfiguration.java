package com.barley.batch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.barley.batch.core.dayend.DayEndJobExecutionListener;
import com.barley.batch.core.dayend.DayJobBuilderFactory;
import com.barley.batch.core.dayend.DayendJobContext;
import com.barley.batch.core.dayend.DefaultDayendJobContext;

@Configuration("dayendJobConfiguration")
@EnableBatchProcessing(modular = true)
//spring self task scheduler
//@EnableScheduling
public class BatchConfiguration {

	/**
	 * Use spring 默认的threadpooltaskexecuter, 控制spring batch job的调度（非 step）
	 * 
	 * @param executor
	 * @return
	 */
	@Bean
	public ExtBatchConfig extBatchConfigurer(@Qualifier("applicationTaskExecutor") TaskExecutor executor) {
		return new ExtBatchConfig(executor);
	}

	@Bean(name = "dayendJobContext")
	public DayendJobContext dayendJob(SchedulerFactoryBean schedulerFactoryBean) {
		DefaultDayendJobContext dayend = new DefaultDayendJobContext();
		dayend.setSchedulerFactoryBean(schedulerFactoryBean);
		return dayend;
	}

	@Bean
	public DayEndJobExecutionListener dayEndJobExecutionListener(DayendJobContext context) {
		return new DayEndJobExecutionListener(context);
	}

	@Bean
	public DayJobBuilderFactory dayEndJobBuilderFactory(JobRepository jobRepository,
			DayEndJobExecutionListener listener) {
		return new DayJobBuilderFactory(jobRepository, listener);
	}

	@Bean
	public BatchRuntimeManager batchRuntimeManager(DayendJobContext dayendJobContext) {
		return new BatchRuntimeManager(dayendJobContext);
	}

	/**
	 * 
	 * 管理batch相关web服务的CORS配置 , 全局配置方式
	 * 
	 * <pre>
	 * registry.addMapping("/api/**").allowedOrigins("https://domain2.com").allowedMethods("PUT", "DELETE")
	 * 		.allowedHeaders("header1", "header2", "header3").exposedHeaders("header1", "header2")
	 * 		.allowCredentials(true).maxAge(3600);
	 * </pre>
	 * 
	 * @return
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {

			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/batch/**");
			}
		};
	}
}
