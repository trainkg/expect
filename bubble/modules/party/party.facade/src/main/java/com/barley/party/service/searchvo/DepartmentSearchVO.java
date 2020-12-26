package com.barley.party.service.searchvo;

import com.barley.party.vo.DepartmentCriteria;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author peculiar.1@163.com
 * @version $ID: com.barley.party.service.searchvo.DepartmentSearchVO create date 2020-12-26 13:02:17
 */
public class DepartmentSearchVO extends DepartmentCriteria implements org.barley.mybatis.CriteriaBuilder {
    @Getter
    @Setter
    private String depName;

    @Getter
    @Setter
    private Integer depStatus;

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
        
        if(StringUtils.isNotEmpty(depName)) {
            criteria.andDepNameEqualTo(depName);
        }
        
        if(depStatus != null) {
            criteria.andDepStatusEqualTo(depStatus);
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
     * This method corresponds to the database table t_department
     *
     * @mbg.generated
     */
    public DepartmentSearchVO() {
        super();
    }
}