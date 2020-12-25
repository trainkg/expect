package com.barley.cms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barley.cms.service.vo.ArticleVO;

@Service
@Transactional
public class ArticleService {
	
	@Transactional
	public void createArticle(ArticleVO articleVO) {
		indexHandler.createArticleIndex();
	}

	@Autowired
	private ArticleIndexHandler indexHandler;
}
