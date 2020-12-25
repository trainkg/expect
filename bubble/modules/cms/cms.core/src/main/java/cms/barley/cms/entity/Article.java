package cms.barley.cms.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author peculiar.1@163.com
 * @version $ID: Article.java, V1.0.0 2020年12月12日 下午9:07:46 $
 */

public class Article {
	
	@Getter
	@Setter
	private long listId;
	@Getter
	@Setter
	private String author;
	@Getter
	@Setter
	private String title;
	@Getter
	@Setter
	private String content;
	
}
