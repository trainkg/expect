package com.barley.cms;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

@Configuration
@EnableSolrRepositories
class ApplicationConfig {

	@Bean
	public SolrClient solrClient() throws Exception {
		// EmbeddedSolrServerFactory factory = new
		// EmbeddedSolrServerFactory("classpath:com/acme/solr");
		/*
		 * HttpSolrClientFactoryBean fatocy = new HttpSolrClientFactoryBean();
		 * fatocy.setUrl("http://192.168.182.132:8983/solr");
		 * fatocy.afterPropertiesSet(); return fatocy.getSolrClient();
		 */
		
		final String solrUrl = "http://192.168.182.132:8983/solr";
		return new HttpSolrClient.Builder(solrUrl).withConnectionTimeout(10000).withSocketTimeout(60000).build();
	}

	@Bean
	public SolrOperations solrTemplate() throws Exception {
		return new SolrTemplate(solrClient());
	}

}