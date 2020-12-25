package com.barley.cms.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barley.cms.service.ArticleService;
import com.barley.cms.service.vo.ArticleVO;

/**
 * 
 * article 基本信息管理
 * 
 * @author peculiar.1@163.com
 * @version $ID: ArticleController.java, V1.0.0 2020年12月19日 上午11:16:38 $
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
	
	
	@RequestMapping("/create")
	public void createArticle() {
		ArticleVO articleVO = new ArticleVO();
		servService.createArticle(articleVO );
	}
	
	@Autowired
	private ArticleService servService;
}
