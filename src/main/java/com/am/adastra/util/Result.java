package com.am.adastra.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
public class Result<T> implements Serializable {
    private String message;
    private int state;
    private T data;

    public void setSuccess(T data) {
        this.message = "OK";
        this.state = State.OK.getValve();
        this.data = data;
    }

    public void setSuccess(String message, T data) {
        this.setSuccess(data);
        this.message = message;
    }

    public void setSuccess() {
        this.setSuccess(null);
    }

    public void setFail(String message, State state) {
        this.message = message;
        this.state = state.getValve();
    }

    public void setFail(String message) {
        this.state = -1;
        this.message = message;
        this.data = null;
    }

    public boolean isSuccess() {
        return this.state == State.OK.getValve();
    }
}
