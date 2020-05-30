package com.barley.party.vo;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * 
 * @author peculiar.1@163.com
 * @version $ID: UserSearchVO.java, V1.0.0 2020年5月25日 下午10:27:01 $
 */
public class UserSearchVO extends UserCriteria {
    
    /**
     * equal
     */
    private @Getter @Setter String userName;
    

    public void pushToCriteria() {
        Criteria criteria = createCriteria();
        if(StringUtils.isNotEmpty(userName)) {
            criteria.andLoginNameEqualTo(userName);
        }
    }
}
