package com.am.adastra.ex;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/6/5 19:45
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
public class UsernameDoesNotExistException extends ServiceException {
    public UsernameDoesNotExistException() {
    }

    public UsernameDoesNotExistException(String message) {
        super(message);
    }

    public UsernameDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameDoesNotExistException(Throwable cause) {
        super(cause);
    }

    public UsernameDoesNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
