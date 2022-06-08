package com.am.adastra.ex;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/6/5 19:51
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
public class SystemErrorException extends ServiceException {
    public SystemErrorException() {
    }

    public SystemErrorException(String message) {
        super(message);
    }

    public SystemErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemErrorException(Throwable cause) {
        super(cause);
    }

    public SystemErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
