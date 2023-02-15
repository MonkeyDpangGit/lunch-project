package com.lunch.common.exception;

import com.lunch.common.enums.ApplicationErrorEnum;
import com.lunch.common.enums.IErrorEnum;

/**
 * ApplicationException
 *
 * @author torrisli
 * @date 2022/2/13
 * @Description: ApplicationException
 */
public class ApplicationException extends RuntimeException {

    public IErrorEnum errorEnum;

    public Object[] fillParameter;

    public ApplicationException(IErrorEnum errorEnum) {
        super(errorEnum.getErrorMessage());
        this.errorEnum = errorEnum;
    }

    public ApplicationException(IErrorEnum errorEnum, Throwable t) {
        super(errorEnum.getErrorMessage(), t);
        this.errorEnum = errorEnum;
    }

    public ApplicationException(IErrorEnum errorEnum, Object... fillParameter) {
        super(errorEnum.getErrorMessage());
        this.errorEnum = errorEnum;
        this.fillParameter = fillParameter;
    }

    @Override
    public String getMessage() {
        if (errorEnum != null) {
            if (fillParameter != null && fillParameter.length > 0) {
                return String.format(errorEnum.getErrorMessage(), fillParameter);
            } else {
                return errorEnum.getErrorMessage();
            }
        } else {
            return super.getMessage();
        }
    }

    public IErrorEnum getErrorEnum() {
        return errorEnum;
    }

    public void setErrorEnum(IErrorEnum errorEnum) {
        this.errorEnum = errorEnum;
    }

    public Object[] getFillParameter() {
        return fillParameter;
    }

    public void setFillParameter(Object[] fillParameter) {
        this.fillParameter = fillParameter;
    }
}
