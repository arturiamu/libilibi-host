package com.am.adastra.ex;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/6/10 10:03
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
public class ValidException extends ServiceException{
    public ValidException() {
    }

    public ValidException(String message) {
        super(message);
    }

    public ValidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidException(Throwable cause) {
        super(cause);
    }

    public ValidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
