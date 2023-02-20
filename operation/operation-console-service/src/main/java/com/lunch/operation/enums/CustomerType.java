package com.lunch.operation.enums;

import com.lunch.common.enums.IMultiLangEnum;

/**
 * CustomerType
 *
 * @author torrisli
 * @date 2023/2/19
 * @Description: CustomerType
 */
public enum CustomerType implements IMultiLangEnum {

    INDIVIDUAL("个人", "individual"),
    ENTERPRISE("企业", "enterprise"),
    UNKNOWN("未知", "unknown");

    private String displayName;

    private String displayNameEn;

    CustomerType(String displayName, String displayNameEn) {
        this.displayName = displayName;
        this.displayNameEn = displayNameEn;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }
}
