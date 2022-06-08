package com.am.adastra.ex;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/6/2 15:21
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
public class ServiceException extends RuntimeException {
    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
