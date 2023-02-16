package com.lunch.common.enums;

/**
 * SysErrorEnum
 *
 * @author torrisli
 * @date 2022/2/13
 * @Description: SysErrorEnum
 */
public enum SysErrorEnum implements IErrorEnum {

    UNKNOWN_EXCEPTION("InternalError", "未知错误, %s", "Unknown exception occurred, %s."),
    FAILED_OPERATION("FailedOperation.OperationFailure", "操作失败", "Operation failed."),
    ILLEGAL_PARAMETER("InvalidParameter.ParameterIllegal", "参数不合法, %s", "Parameter is illegal, %s.");

    private String errorCode;

    private String errorMessage;

    private String errorMessageEn;

    SysErrorEnum(String errorCode, String errorMessage, String errorMessageEn) {
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
