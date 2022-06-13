package com.am.adastra.ex;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/6/10 9:45
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
public class RegisterException extends ServiceException{
    public RegisterException() {
    }

    public RegisterException(String message) {
        super(message);
    }

    public RegisterException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegisterException(Throwable cause) {
        super(cause);
    }

    public RegisterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
