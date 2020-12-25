package com.barley.cms.service.index;

import org.apache.solr.client.solrj.beans.Field;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @description 建立所有的article对象
 * 
 * @author peculiar.1@163.com
 * @version $ID: IndexArticle.java, V1.0.0 2020年12月19日 下午3:01:31 $
 */
public class IndexArticle {
	@Getter
	@Setter
	@Field
	private long listId;
	@Getter
	@Setter
	@Field
	private String author;
	@Getter
	@Setter
	@Field
	private String title;
	@Getter
	@Setter
	@Field
	private String content;

}
