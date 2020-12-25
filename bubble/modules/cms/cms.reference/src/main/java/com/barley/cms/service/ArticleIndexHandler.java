package com.barley.cms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.stereotype.Repository;

import com.barley.cms.service.index.IndexArticle;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class ArticleIndexHandler {

	private static final String COLLECTION_NAME = "homepage";

	public void createArticleIndex() {
		log.info("createArticleIndex start");
		IndexArticle article = new IndexArticle();
		article.setListId(1);
		article.setTitle("test title");
		article.setContent("test content , new films will release. version 6, 我是一个乐观开朗的人");
		article.setAuthor("yuanyu.zhu");

//		try {
//			/*
//			 * operaSolr.deleteByQuery(COLLECTION_NAME, "listId:1");
//			 * operaSolr.addBean(COLLECTION_NAME, article);
//			 * operaSolr.commit(COLLECTION_NAME);
//			 */
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (SolrServerException e) {
//			e.printStackTrace();
//		}
		
		operaService.delete(COLLECTION_NAME, new SimpleQuery("listId:1"));
		operaService.saveBean(COLLECTION_NAME, article);
		//operaService.commit(COLLECTION_NAME);
		log.info("createArticleIndex end");
	}

	@Autowired
	private SolrOperations operaService;
}
