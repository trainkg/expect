package cms.barley.cms.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author peculiar.1@163.com
 * @version $ID: Notice.java, V1.0.0 2020年12月12日 下午9:25:07 $
 */
public class Notice {

	public enum NoticeLevel {
		WARN, INFO, TIP, ERROR
	}

	@Getter
	@Setter
	private long listId;
	@Getter
	@Setter
	private String content;
	@Getter
	@Setter
	private NoticeLevel level;

}
