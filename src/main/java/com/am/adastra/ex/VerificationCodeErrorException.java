package com.am.adastra.ex;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/6/5 19:47
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
public class VerificationCodeErrorException extends ServiceException {
    public VerificationCodeErrorException() {
    }

    public VerificationCodeErrorException(String message) {
        super(message);
    }

    public VerificationCodeErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public VerificationCodeErrorException(Throwable cause) {
        super(cause);
    }

    public VerificationCodeErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
