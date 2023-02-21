package com.lunch.operation.enums;

import com.lunch.common.enums.IErrorEnum;

/**
 * OperationErrorEnum
 *
 * @author torrisli
 * @date 2023/2/15
 * @Description: OperationErrorEnum
 */
public enum OperationErrorEnum implements IErrorEnum {

    ILLEGAL_CUSTOMER_TYPE("InvalidParameter.GenderIllegal", "'客户类型'参数不合法", "'CustomerType' parameter is illegal.");

    private String errorCode;

    private String errorMessage;

    private String errorMessageEn;

    OperationErrorEnum(String errorCode, String errorMessage, String errorMessageEn) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorMessageEn = errorMessageEn;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String getErrorMessageEn() {
        return errorMessageEn;
    }
}
