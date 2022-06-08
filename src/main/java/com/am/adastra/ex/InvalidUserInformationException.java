package com.am.adastra.ex;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/6/6 8:57
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
public class InvalidUserInformationException extends ServiceException {
    public InvalidUserInformationException() {
    }

    public InvalidUserInformationException(String message) {
        super(message);
    }

    public InvalidUserInformationException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidUserInformationException(Throwable cause) {
        super(cause);
    }

    public InvalidUserInformationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
