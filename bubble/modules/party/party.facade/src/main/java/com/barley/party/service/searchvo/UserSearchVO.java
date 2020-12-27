package com.barley.party.service.searchvo;

import com.barley.party.vo.UserCriteria;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author peculiar.1@163.com
 * @version $ID: com.barley.party.service.searchvo.UserSearchVO create date 2020-12-27 14:50:38
 */
public class UserSearchVO extends UserCriteria implements org.barley.mybatis.CriteriaBuilder {
    @Getter
    @Setter
    private java.time.LocalDateTime activationTime;

    @Getter
    @Setter
    private java.time.LocalDate applicationTime;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private java.time.LocalDateTime firstLoginTime;

    @Getter
    @Setter
    private java.time.LocalDateTime lastLoginTime;

    @Getter
    @Setter
    private String loginName;

    @Getter
    @Setter
    private Integer loginTimes;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String sourceId;

    @Getter
    @Setter
    private String spell;

    @Getter
    @Setter
    private Integer userStatus;

    @Getter
    @Setter
    private Integer userType;

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
        
        if(StringUtils.isNotEmpty(email)) {
            criteria.andEmailEqualTo(email);
        }
        
        if(StringUtils.isNotEmpty(loginName)) {
            criteria.andLoginNameEqualTo(loginName);
        }
        
        if(loginTimes != null) {
            criteria.andLoginTimesEqualTo(loginTimes);
        }
        
        if(StringUtils.isNotEmpty(password)) {
            criteria.andPasswordEqualTo(password);
        }
        
        if(StringUtils.isNotEmpty(sourceId)) {
            criteria.andSourceIdEqualTo(sourceId);
        }
        
        if(StringUtils.isNotEmpty(spell)) {
            criteria.andSpellEqualTo(spell);
        }
        
        if(userStatus != null) {
            criteria.andUserStatusEqualTo(userStatus);
        }
        
        if(userType != null) {
            criteria.andUserTypeEqualTo(userType);
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
     * This method corresponds to the database table t_user
     *
     * @mbg.generated
     */
    public UserSearchVO() {
        super();
    }
}