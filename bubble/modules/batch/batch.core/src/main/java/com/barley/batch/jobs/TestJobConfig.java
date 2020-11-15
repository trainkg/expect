package com.barley.batch.jobs;

import javax.sql.DataSource;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.barley.batch.core.dayend.DayJobBuilderFactory;

/**
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: TestJobConfig.java, V1.0.0 2020年11月2日 下午8:53:48 $
 */
@Configuration
public class TestJobConfig {


	@Bean(name = "test job")
	public org.springframework.batch.core.Job testJob(@Qualifier("dayEndJobBuilderFactory") DayJobBuilderFactory fatory, @Qualifier("testStep1") Step step1) {
		return fatory.get("test job").incrementer(new RunIdIncrementer()).flow(step1).end().build();
	}

	@Bean("testStep1")
	public Step step1(DataSource dataSource, ThreadPoolTaskExecutor executor) {

		ItemReader<Person> reader = new JdbcCursorItemReaderBuilder<Person>().name("dbPersonItemReader")
				.dataSource(dataSource).sql("select * from people").beanRowMapper(Person.class).build();

		return stepBuilderFactory.get("step1").<Person, String>chunk(10).reader(reader)
				.processor(new PersonItemProcessor()).writer(new StringItemWriter())
				.allowStartIfComplete(true)
				//.taskExecutor(executor)
				.build();
	}

	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;

}
