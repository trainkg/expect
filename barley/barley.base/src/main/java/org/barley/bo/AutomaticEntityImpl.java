package org.barley.bo;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Setter;

/**
 * {@link AutomaticEntity} 
 * @author peculiar.1@163.com
 * @version $ID: AutomaticEntityImpl.java, V1.0.0 2020年4月15日 下午11:02:30 $
 */
public class AutomaticEntityImpl extends BasicEntity implements AutomaticEntity{
    
    @Setter
    private Long insertBy;
    @Setter
    private Long UpdateBy;
    @Setter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime insertTime;
    @Setter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime UpdateTime;
    
    @Override
    public Long getInsertBy() {
        return insertBy;
    }
    @Override
    public Long getUpdateBy() {
        return UpdateBy;
    }
    @Override
    public LocalDateTime getInsertTime() {
        return insertTime;
    }
    @Override
    public LocalDateTime getUpdateTime() {
        return UpdateTime;
    }
   
}
