package com.barley.batch.jobs;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StringItemWriter implements ItemWriter<String> {

	@Override
	public void write(List<? extends String> items) throws Exception {
		System.out.println("Chunk start===============================");
		for (String string : items) {
			log.info(string);
		}
		System.out.println("Chunk end===============================");

		Thread.sleep(5000);
	}

}
