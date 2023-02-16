package com.lunch.common.exception;

import com.lunch.common.enums.IErrorEnum;

/**
 * SysException
 *
 * @author torrisli
 * @date 2022/2/13
 * @Description: SysException
 */
public class SysException extends RuntimeException {

    public IErrorEnum errorEnum;

    public Object[] fillParameter;

    public SysException(IErrorEnum errorEnum) {
        super(errorEnum.getErrorMessage());
        this.errorEnum = errorEnum;
    }

    public SysException(IErrorEnum errorEnum, Throwable t) {
        super(errorEnum.getErrorMessage(), t);
        this.errorEnum = errorEnum;
    }

    public SysException(IErrorEnum errorEnum, Object... fillParameter) {
        super(errorEnum.getErrorMessage());
        this.errorEnum = errorEnum;
        this.fillParameter = fillParameter;
    }

    @Override
    public String getMessage() {
        // todo: multi-language message
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
