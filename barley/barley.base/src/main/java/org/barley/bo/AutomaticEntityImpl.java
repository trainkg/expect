package org.barley.bo;

import java.util.Date;

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
    private Date insertTime;
    @Setter
    private Date UpdateTime;
    
    @Override
    public Long getInsertBy() {
        return insertBy;
    }
    @Override
    public Long getUpdateBy() {
        return UpdateBy;
    }
    @Override
    public Date getInsertTime() {
        return insertTime;
    }
    @Override
    public Date getUpdateTime() {
        return UpdateTime;
    }
   
}
