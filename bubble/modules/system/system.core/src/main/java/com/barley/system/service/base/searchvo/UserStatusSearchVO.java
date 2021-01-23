package com.barley.system.service.base.searchvo;

import com.barley.system.vo.UserStatusCriteria;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author peculiar.1@163.com
 * @version $ID: com.barley.system.service.base.searchvo.UserStatusSearchVO create date 2021-01-23 14:30:29
 */
public class UserStatusSearchVO extends UserStatusCriteria implements org.barley.mybatis.CriteriaBuilder {
    @Getter
    @Setter
    private String code;

    @Getter
    @Setter
    private String descibe;

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
        
        if(StringUtils.isNotEmpty(code)) {
            criteria.andCodeEqualTo(code);
        }
        
        if(StringUtils.isNotEmpty(descibe)) {
            criteria.andDescibeEqualTo(descibe);
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
     * This method corresponds to the database table t_user_status
     *
     * @mbg.generated
     */
    public UserStatusSearchVO() {
        super();
    }
}