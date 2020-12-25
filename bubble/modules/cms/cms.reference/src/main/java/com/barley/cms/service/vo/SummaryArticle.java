package com.barley.cms.service.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 文章概要信息
 * 
 * @author peculiar.1@163.com
 * @version $ID: SummaryArticle.java, V1.0.0 2020年12月18日 下午6:41:33 $
 */
public class SummaryArticle {

	@Getter
	@Setter
	private long listId;

	@Getter
	@Setter
	private String title;
	/**
	 * 所属于栏目
	 */
	@Getter
	@Setter
	private String column;

}
