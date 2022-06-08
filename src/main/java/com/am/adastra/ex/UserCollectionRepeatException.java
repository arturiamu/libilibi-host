package com.am.adastra.ex;

/*
* 用户重复添加视频到收藏夹的异常
* */
public class UserCollectionRepeatException extends ServiceException{
    public UserCollectionRepeatException() {
    }

    public UserCollectionRepeatException(String message) {
        super(message);
    }

    public UserCollectionRepeatException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserCollectionRepeatException(Throwable cause) {
        super(cause);
    }

    public UserCollectionRepeatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
