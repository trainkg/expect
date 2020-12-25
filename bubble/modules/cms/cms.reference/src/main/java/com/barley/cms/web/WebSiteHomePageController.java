package com.barley.cms.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.barley.cms.service.SiteMainHomeService;
import com.barley.cms.service.vo.SummaryArticle;

/**
 * web site home page
 * 
 * @author peculiar.1@163.com
 * @version $ID: WebSiteHomePageController.java, V1.0.0 2020年12月12日 下午11:25:23 $
 */
@RestController
public class WebSiteHomePageController {

	public List<SummaryArticle> loadingHomePageArticle() {
		return servSite.loadingHomePageArticle();
	}

	@Autowired
	private SiteMainHomeService servSite;
}
