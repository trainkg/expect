package com.barley.batch.jobs;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.json.builder.JsonFileItemWriterBuilder;
import org.springframework.batch.support.DatabaseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration("batchJobsConfiguration")
public class BatchConfiguration {

	@Autowired
	// @Qualifier("jobBuilders")
	@Qualifier("dayEndJobBuilderFactory")
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	public FlatFileItemReader<Person> reader() {
		return new FlatFileItemReaderBuilder<Person>().name("personItemReader")
				.resource(new ClassPathResource("sample-data.csv")).delimited()
				.names(new String[] { "firstName", "lastName" })
				.fieldSetMapper(new BeanWrapperFieldSetMapper<Person>() {
					{
						setTargetType(Person.class);
					}
				}).build();
	}

	/**
	 * JDBC Reader
	 * 
	 * @return
	 */
	@Bean
	public JdbcCursorItemReader<Person> readerPerson(DataSource dataSource) {
		return new JdbcCursorItemReaderBuilder<Person>().name("dbPersonItemReader").dataSource(dataSource)
				.sql("select * from people").beanRowMapper(Person.class).build();
	}

	@Bean
	public PagingQueryProvider sqlProvider(DataSource dataSource) {
		SqlPagingQueryProviderFactoryBean provider = new SqlPagingQueryProviderFactoryBean();
		provider.setDataSource(dataSource);
		provider.setDatabaseType(DatabaseType.MYSQL.getProductName());
		provider.setFromClause("people");
		provider.setSelectClause("*");
		provider.setWhereClause("person_id >= :minId and person_id <= :maxId");// 结合分块上下文变量
		provider.setSortKey("person_id");
		try {
			return provider.getObject();
		} catch (Exception e) {
			log.info("queryProvider exception ");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * JDBC paging
	 * 
	 * @param dataSource
	 * @return
	 */

	@StepScope
	@Bean
	public JdbcPagingItemReader<Person> readerPersonPage(DataSource dataSource,
			@Value("#{stepExecutionContext[minValue]}") final int minValue,
			@Value("#{stepExecutionContext[maxValue]}") final int maxValue, PagingQueryProvider provider) {
		Map<String, Object> parameterValues = new HashMap<String, Object>(2);
		parameterValues.put("minId", minValue);
		parameterValues.put("maxId", maxValue);
		return new JdbcPagingItemReaderBuilder<Person>().name("pagePersonItemReader").dataSource(dataSource)
				.rowMapper(new Person()).parameterValues(parameterValues).queryProvider(provider).build();
	}

	public PersonItemProcessor processor() {
		return new PersonItemProcessor();
	}

	public PersonItemProcessor2 processor2() {
		return new PersonItemProcessor2();
	}

	@Bean
	public JdbcBatchItemWriter<Person> writer(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<Person>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Person>() {
					@Override
					public SqlParameterSource createSqlParameterSource(Person item) {
						item.setPersonId(new Random().nextInt(1000000000));
						return super.createSqlParameterSource(item);
					}
				}).sql("INSERT INTO people (person_id,first_name, last_name) VALUES (:personId,:firstName, :lastName)")
				.dataSource(dataSource).build();
	}

	/**
	 * 写入到JASON
	 * 
	 * @return
	 */

	@StepScope
	@Bean
	public JsonFileItemWriter<Person> jsonFileItemWriter(@Value("#{stepExecutionContext[minValue]}") final int minValue,
			@Value("#{stepExecutionContext[maxValue]}") final int maxValue) {
		File file = new File("D://Person" + minValue + "-" + maxValue + ".json");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		log.info("writer person info into file {}", file.getAbsolutePath());
		return new JsonFileItemWriterBuilder<Person>().jsonObjectMarshaller(new JacksonJsonObjectMarshaller<>())
				.resource(new FileSystemResource(file)).append(false).name("tradeJsonFileItemWriter").build();
		// new FileSystemResource("D://Person.json")
	}

	@Bean
	@StepScope
	public FlatFileItemWriter<Person> slaveWriter(@Value("#{stepExecutionContext[fromId]}") final String fromId,
			@Value("#{stepExecutionContext[toId]}") final String toId) {
		FlatFileItemWriter<Person> reader = new FlatFileItemWriter<>();
		reader.setResource(new FileSystemResource("csv/outputs/users.processed" + fromId + "-" + toId + ".csv"));
		// reader.setAppendAllowed(false);
		reader.setLineAggregator(new DelimitedLineAggregator<Person>() {
			{
				setDelimiter(",");
				setFieldExtractor(new BeanWrapperFieldExtractor<Person>() {
					{
						setNames(new String[] { "personId", "firstName", "lastName" });
					}
				});
			}
		});
		return reader;
	}

	/**
	 * JOB 任务
	 * 
	 * @param listener
	 * @param step1
	 * @param step2
	 * @return
	 */
	@Bean
	public Job importUserJob(JobCompletionNotificationListener listener, Step step1, Step step2, Step step1Manager) {
		return jobBuilderFactory.get("importUserJob").incrementer(new RunIdIncrementer()).listener(listener).flow(step1)
				// .next(step2)
				.next(step1Manager) // partition 分片任务
				.end().build();
	}

	@Bean
	public Step step1(JdbcBatchItemWriter<Person> writer) {
		return stepBuilderFactory.get("step1").<Person, Person>chunk(10).reader(reader())
				// .processor(processor())
				.writer(writer)
				// .allowStartIfComplete(true)
				.build();
	}

	/**
	 * 将输入表中的数据进行多线程处理
	 * 
	 * @param writer
	 * @return
	 */
	@Bean
	public Step step2(JsonFileItemWriter<Person> writer, JdbcCursorItemReader<Person> reader,
			TaskExecutor taskExecutor) {
		return stepBuilderFactory.get("step2").<Person, Person>chunk(50).reader(reader).processor(processor2())
				.writer(writer).allowStartIfComplete(true).taskExecutor(taskExecutor).throttleLimit(4).build();
	}

	@Bean
	public Step step3(JdbcPagingItemReader<Person> readerPersonPage, JsonFileItemWriter<Person> jsonFileItemWriter,
			DataSource dataSource, TaskExecutor taskExecutor) {
		return stepBuilderFactory.get("step3").<Person, Person>chunk(10).reader(readerPersonPage)
				.processor(processor2()).writer(jsonFileItemWriter).allowStartIfComplete(true).build();
	}

	/**
	 * 主step --> 分片step(step3)
	 * 
	 * @param step1
	 * @param partitionHandler
	 * @param partitioner
	 * @return
	 */
	@Bean
	public Step step1Manager(Step step3, PartitionHandler partitionHandler, Partitioner partitioner) {
		return stepBuilderFactory.get("step3.manager").partitioner("step3", partitioner)
				.partitionHandler(partitionHandler).allowStartIfComplete(true).build();
	}

	@Bean
	public Partitioner partitioner(DataSource dataSource) {
		ColumnRangePartitioner partitioner = new ColumnRangePartitioner();
		partitioner.setDataSource(dataSource);
		partitioner.setTable("people");
		partitioner.setColumn("person_id");
		return partitioner;
	}

	@Bean
	public PartitionHandler partitionHandler(Step step3, TaskExecutor executor) {
		log.info("execute partitionHandler, executor type {}", executor);
		TaskExecutorPartitionHandler retVal = new TaskExecutorPartitionHandler();
		retVal.setTaskExecutor(executor);
		retVal.setStep(step3);
		retVal.setGridSize(3);
		return retVal;
	}
}
