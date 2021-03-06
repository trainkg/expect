package com.barley.system.service.base.searchvo;

import com.barley.system.vo.UserTypeCriteria;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author peculiar.1@163.com
 * @version $ID: com.barley.system.service.base.searchvo.UserTypeSearchVO
 */
public class UserTypeSearchVO extends UserTypeCriteria implements org.barley.mybatis.CriteriaBuilder {
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String code;

    @Getter
    @Setter
    private Short status;

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
        
        if(StringUtils.isNotEmpty(name)) {
            criteria.andNameEqualTo(name);
        }
        
        if(StringUtils.isNotEmpty(code)) {
            criteria.andCodeEqualTo(code);
        }
        
        if(status != null) {
            criteria.andStatusEqualTo(status);
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
     * This method corresponds to the database table t_user_type
     *
     * @mbg.generated
     */
    public UserTypeSearchVO() {
        super();
    }
}