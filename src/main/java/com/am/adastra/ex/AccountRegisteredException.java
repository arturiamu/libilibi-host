package com.am.adastra.ex;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/6/6 10:37
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
public class AccountRegisteredException extends ServiceException{
    public AccountRegisteredException() {
    }

    public AccountRegisteredException(String message) {
        super(message);
    }

    public AccountRegisteredException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccountRegisteredException(Throwable cause) {
        super(cause);
    }

    public AccountRegisteredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
