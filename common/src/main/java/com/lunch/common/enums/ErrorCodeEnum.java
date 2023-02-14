package com.lunch.common.enums;

/**
 * ErrorCodeEnum
 *
 * @author torrisli
 * @date 2022/2/13
 * @Description: ErrorCodeEnum
 */
public enum ErrorCodeEnum {

    UNKNOWN_EXCEPTION("InternalError", "Unknown exception occurred.", ""),
    INVALID_PARAMETER("InvalidParameter", "Parameter is illegal, %s.", ""),

    FAILED_OPERATION("FailedOperation.OperationFailure", "Operation failed.", ""),
    ILLEGAL_PARAMETER("InvalidParameter.ParameterIllegal", "Parameter is illegal, %s.", "")
    ;

    public String errorCode;

    public String errorMessage;

    public String errorMessageCN;

    ErrorCodeEnum(String errorCode, String errorMessage, String errorMessageCN) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorMessageCN = errorMessageCN;
    }

    public static ErrorCodeEnum getByCode(String code) {
        ErrorCodeEnum[] enums = values();
        for (ErrorCodeEnum errorCodeEnum : enums) {
            if (errorCodeEnum.errorCode.equals(code)) {
                return errorCodeEnum;
            }
        }
        return null;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return errorCode + ":" + errorMessage;
    }

    public String toString(Object... fillParameter) {
        return errorCode + ":" + String.format(errorMessage, fillParameter);
    }
}
