package com.am.libilibi.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/1 17:48
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> {
    private String message;
    private int status_code;
    private T data;

    public void setResultSuccess(T data) {
        this.status_code = 0;
        this.message = "SUCCESS";
        this.data = data;
    }

    public void setResultSuccess(String message,T data) {
        this.status_code = 0;
        this.message = message;
        this.data = data;
    }

    public void setResultFailed(String message) {
        this.status_code = -1;
        this.message = message;
        this.data = null;
    }

    public void setResultFailed() {
        this.status_code = -1;
        this.message = "FAILED";
        this.data = null;
    }

    public boolean isSuccess(){
        return status_code == 0;
    }
}
