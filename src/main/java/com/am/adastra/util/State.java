package com.am.adastra.util;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/27 10:24
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
public enum State {
    OK(200),
    ERR_PASSWORD(201),
    ERR_NO_USER(202),
    ERR_REG_INFO(202),
    ERR_NOT_LOGIN(202),
    ERR_USER_INFO(202),
    ERR_USERNAME_EXISTED(203);

    private final Integer valve;

    private State(Integer valve) {
        this.valve = valve;
    }

    public Integer getValve() {
        return this.valve;
    }
}
