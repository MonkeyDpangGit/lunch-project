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

    ILLEGAL_CUSTOMER_TYPE("InvalidParameter.CustomerTypeIllegal", "'客户类型'参数不合法", "'CustomerType' parameter is illegal."),
    NOT_EXISTS_CUSTOMER("FailedOperation.CustomerNotExists", "客户不存在", "Customer dose not exist."),
    BLANK_CUSTOMER_NAME("InvalidParameter.CustomerNameBlank", "客户名称不能为空", "'CustomerName' can not be blank.");

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
