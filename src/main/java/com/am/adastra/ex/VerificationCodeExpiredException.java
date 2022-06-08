package com.am.adastra.ex;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/6/5 19:47
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
public class VerificationCodeExpiredException extends ServiceException {
    public VerificationCodeExpiredException() {
    }

    public VerificationCodeExpiredException(String message) {
        super(message);
    }

    public VerificationCodeExpiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public VerificationCodeExpiredException(Throwable cause) {
        super(cause);
    }

    public VerificationCodeExpiredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
