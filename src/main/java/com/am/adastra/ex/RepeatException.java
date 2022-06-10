package com.am.adastra.ex;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/6/10 9:46
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
public class RepeatException extends ServiceException {
    public RepeatException() {
    }

    public RepeatException(String message) {
        super(message);
    }

    public RepeatException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepeatException(Throwable cause) {
        super(cause);
    }

    public RepeatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
