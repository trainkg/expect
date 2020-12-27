package com.barley.party.service.searchvo;

import com.barley.party.vo.UserTypeCriteria;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author peculiar.1@163.com
 * @version $ID: com.barley.party.service.searchvo.UserTypeSearchVO create date 2020-12-27 14:50:38
 */
public class UserTypeSearchVO extends UserTypeCriteria implements org.barley.mybatis.CriteriaBuilder {
    @Getter
    @Setter
    private String typeName;

    @Getter
    @Setter
    private String typeCode;

    @Getter
    @Setter
    private Short typeStatus;

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
        
        if(StringUtils.isNotEmpty(typeName)) {
            criteria.andTypeNameEqualTo(typeName);
        }
        
        if(StringUtils.isNotEmpty(typeCode)) {
            criteria.andTypeCodeEqualTo(typeCode);
        }
        
        if(typeStatus != null) {
            criteria.andTypeStatusEqualTo(typeStatus);
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