package com.am.adastra.ex;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/6/10 9:45
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
public class LoginException extends ServiceException {
    public LoginException() {
    }

    public LoginException(String message) {
        super(message);
    }

    public LoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginException(Throwable cause) {
        super(cause);
    }

    public LoginException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
