package org.barley.app.batch.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = { HibernateJpaAutoConfiguration.class, FreeMarkerAutoConfiguration.class })
@ComponentScan(basePackages = "com.barley")
public class Application {
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
		//System.exit(SpringApplication.exit(SpringApplication.run(Application.class, args)));
	}
}
