package com.barley.batch.jobs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class PersonItemProcessor implements ItemProcessor<Person, String> {
	private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);

	@Override
	public String process(final Person person) throws Exception {
		final String firstName = person.getFirstName().toUpperCase();
		final String lastName = person.getLastName().toUpperCase();

		final Person transformedPerson = new Person(firstName, lastName);
		transformedPerson.setPersonId(person.getPersonId());
		log.info("person Id {"+person.getPersonId()+"} Converting (" + person + ") into (" + transformedPerson + ")");
		
		return "Converting (" + person + ") into (" + transformedPerson + ")";
	}
}
