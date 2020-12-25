package com.barley.cms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barley.cms.dao.ArticleDao;
import com.barley.cms.service.vo.SummaryArticle;

/**
 * @description 首页信息查询
 * @author peculiar.1@163.com
 * @version $ID: SiteMainPageService.java, V1.0.0 2020年12月12日 下午11:23:13 $
 */

@Service
public class SiteMainHomeService {

	/**
	 * 加载首页显示文章
	 * 
	 * @return
	 */
	public List<SummaryArticle> loadingHomePageArticle() {
		return new ArrayList<SummaryArticle>();
	}

	@Autowired
	private ArticleDao daoArticle;

}
