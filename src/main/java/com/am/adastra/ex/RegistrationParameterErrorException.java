package com.am.adastra.ex;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/6/5 19:48
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
public class RegistrationParameterErrorException extends ServiceException {
    public RegistrationParameterErrorException() {
    }

    public RegistrationParameterErrorException(String message) {
        super(message);
    }

    public RegistrationParameterErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegistrationParameterErrorException(Throwable cause) {
        super(cause);
    }

    public RegistrationParameterErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
