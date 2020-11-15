package com.barley.batch.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class PersonItemProcessor2 implements ItemProcessor<Person, Person> {
	private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor2.class);

	@Override
	public Person process(final Person person) throws Exception {
		log.info("person name {} , {}",person.getPersonId(), person);
		return person;
	}
}
