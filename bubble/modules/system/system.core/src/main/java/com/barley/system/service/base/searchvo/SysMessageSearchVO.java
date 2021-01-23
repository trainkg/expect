package com.barley.system.service.base.searchvo;

import com.barley.system.vo.SysMessageCriteria;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author peculiar.1@163.com
 * @version $ID: com.barley.system.service.base.searchvo.SysMessageSearchVO create date 2021-01-23 20:01:02
 */
public class SysMessageSearchVO extends SysMessageCriteria implements org.barley.mybatis.CriteriaBuilder {
    @Getter
    @Setter
    private String messageBody;

    @Getter
    @Setter
    private Long recipient;

    @Getter
    @Setter
    private Long sender;

    @Getter
    @Setter
    private String consumption;

    @Getter
    @Setter
    private java.time.LocalDateTime expiryDate;

    @Getter
    @Setter
    private Long insertBy;

    @Getter
    @Setter
    private Long updateBy;

    @Getter
    @Setter
    private java.time.LocalDateTime insertTime;

    @Getter
    @Setter
    private java.time.LocalDateTime updateTime;

    public void build() {
        Criteria criteria = createCriteria();
        
        if(StringUtils.isNotEmpty(messageBody)) {
            criteria.andMessageBodyEqualTo(messageBody);
        }
        
        if(recipient != null) {
            criteria.andRecipientEqualTo(recipient);
        }
        
        if(sender != null) {
            criteria.andSenderEqualTo(sender);
        }
        
        if(StringUtils.isNotEmpty(consumption)) {
            criteria.andConsumptionEqualTo(consumption);
        }
        
        if(insertBy != null) {
            criteria.andInsertByEqualTo(insertBy);
        }
        
        if(updateBy != null) {
            criteria.andUpdateByEqualTo(updateBy);
        }
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_message
     *
     * @mbg.generated
     */
    public SysMessageSearchVO() {
        super();
    }
}