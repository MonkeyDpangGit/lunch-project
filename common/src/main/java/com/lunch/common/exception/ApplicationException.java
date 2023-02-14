package com.lunch.common.exception;

import com.lunch.common.enums.ErrorCodeEnum;

/**
 * ApplicationException
 *
 * @author torrisli
 * @date 2022/2/13
 * @Description: ApplicationException
 */
public class ApplicationException extends RuntimeException {

    public ErrorCodeEnum errorEnum;

    public Object[] fillParameter;

    public ApplicationException(ErrorCodeEnum errorEnum) {
        super(errorEnum.getErrorMessage());
        this.errorEnum = errorEnum;
    }

    public ApplicationException(ErrorCodeEnum errorEnum, Throwable t) {
        super(errorEnum.getErrorMessage(), t);
        this.errorEnum = errorEnum;
    }

    @Override
    public String getMessage() {
        if (errorEnum != null) {
            if (fillParameter != null && fillParameter.length > 0) {
                return errorEnum.toString(fillParameter);
            } else {
                return errorEnum.toString();
            }
        } else {
            return super.getMessage();
        }
    }

    public ErrorCodeEnum getErrorEnum() {
        return errorEnum;
    }

    public void setErrorEnum(ErrorCodeEnum errorEnum) {
        this.errorEnum = errorEnum;
    }

    public Object[] getFillParameter() {
        return fillParameter;
    }

    public void setFillParameter(Object[] fillParameter) {
        this.fillParameter = fillParameter;
    }
}
