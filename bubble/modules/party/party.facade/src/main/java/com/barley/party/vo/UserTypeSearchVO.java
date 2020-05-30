package com.barley.party.vo;

import lombok.Getter;
import lombok.Setter;

public class UserTypeSearchVO extends UserTypeCriteria {
    private @Getter @Setter Short typeStatus;

    public void pushToCriteria() {
        Criteria criteria = createCriteria();
        if (typeStatus != null) {
            criteria.andTypeStatusEqualTo(typeStatus);
        }
    }
}
