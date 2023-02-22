package com.lunch.common.enums;

/**
 * PhoneAreaCode
 *
 * @author torrisli
 * @date 2023/2/22
 * @Description: PhoneAreaCode
 */
public enum PhoneAreaCode {

    CHINA("86"),
    HONGKONG_CHINA("852"),
    MACOW_CHINA("853"),
    TAIWAI_CHINA("886"),
    AMERICA("1"),
    CANADA("1"),
    UK("44"),
    FRANCE("33"),
    GERMANY("49"),
    JAPAN("81"),
    KOREA("82");

    PhoneAreaCode(String code) {
        this.code = code;
    }

    private String code;

    public String getCode() {
        return code;
    }

    /**
     * 根据区号获取枚举类
     *
     * @param code 区号
     * @return
     */
    public static PhoneAreaCode getByCode(String code) {
        for (PhoneAreaCode value : PhoneAreaCode.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }
}
