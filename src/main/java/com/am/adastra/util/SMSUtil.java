package com.am.adastra.util;

import com.alibaba.fastjson.JSONObject;
import com.am.adastra.controller.UserController;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/27 20:50
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
public class SMSUtil {
    private static final int APP_ID = 1400683116;
    private static final String APP_KEY = "95f32e5cdb35bbc88dbcedb41b6934e4";
    private static final int TEMPLATE_ID = 1419186;  // 通用 code
    private static final String SMS_SIGN = "isamumu";
    private static final int CODE_SIZE = 6;

    public static String sendSMS(String phoneNumber) {
        String code = KeyUtils.keyUtils(CODE_SIZE);
        try {
            String[] params = {code};
            SmsSingleSender sender = new SmsSingleSender(APP_ID, APP_KEY);
            SmsSingleSenderResult result = sender.sendWithParam("86", phoneNumber, TEMPLATE_ID, params, SMS_SIGN, "", "");
            if (result.result != 0) {
                return null;
            }
            System.out.println(code);
            return code;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
