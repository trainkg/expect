package com.barley.system.service.auth.searchvo;

import com.barley.system.vo.GroupCriteria;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author peculiar.1@163.com
 * @version $ID: com.barley.system.service.auth.searchvo.GroupSearchVO create date 2021-01-03 11:09:28
 */
public class GroupSearchVO extends GroupCriteria implements org.barley.mybatis.CriteriaBuilder {
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String descibe;

    @Getter
    @Setter
    private Integer status;

    public void build() {
        Criteria criteria = createCriteria();
        
        if(StringUtils.isNotEmpty(name)) {
            criteria.andNameEqualTo(name);
        }
        
        if(StringUtils.isNotEmpty(descibe)) {
            criteria.andDescibeEqualTo(descibe);
        }
        
        if(status != null) {
            criteria.andStatusEqualTo(status);
        }
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_group
     *
     * @mbg.generated
     */
    public GroupSearchVO() {
        super();
    }
}