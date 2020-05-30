package org.barley.bo;

import java.time.LocalDateTime;

/**
 * 统计信息 <p>
 * 当你的对象需要统计信息时，可以实现该接口
 * 
 * @author peculiar.1@163.com
 * @version $ID: AutomaticEntity.java, V1.0.0 2020年4月15日 下午10:58:17 $
 */
public interface AutomaticEntity {
     public Long getInsertBy();
     public Long getUpdateBy();
     public LocalDateTime getInsertTime();
     public LocalDateTime getUpdateTime();
}
