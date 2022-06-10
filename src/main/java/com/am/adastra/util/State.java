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
    LOGIN_EX_CODE(201),
    REGISTER_EX_CODE(202),
    REPEAT_EX_CODE(203),
    SYS_EX_CODE(204),
    USER_NOT_LOGIN_EX_CODE(205),
    ILLEGAL_EX_CODE(205),
    VALID_EX_CODE(205);

    private final Integer valve;

    private State(Integer valve) {
        this.valve = valve;
    }

    public Integer getValve() {
        return this.valve;
    }
}
