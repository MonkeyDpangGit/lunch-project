package com.lunch.common.enums;

/**
 * Gender
 *
 * @author torrisli
 * @date 2023/2/19
 * @Description: Gender
 */
public enum Gender implements IMultiLangEnum {

    MALE("男", "male"),
    FEMAIL("女", "female");

    private String displayName;

    private String displayNameEn;

    Gender(String displayName, String displayNameEn) {
        this.displayName = displayName;
        this.displayNameEn = displayNameEn;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }
}
