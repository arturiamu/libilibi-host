package com.am.libilibi.utils;

import com.am.libilibi.entity.LBReqType;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/18 16:42
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
public class LBReqDispatch {
    private static final LBLoggedInReq LOGGED_IN_REQ = new LBLoggedInReq();
    private static final LBTouristsReq TOURISTS_REQ = new LBTouristsReq();

    public static void dispatchLogged() {

    }

    public static void dispatchTourists(LBReqType type, String typeName) {
        switch (type) {
            case DISPLAY_TYPE: {

            }
            case DISPLAY_RECOMMEND: {
            }
        }
    }
}
