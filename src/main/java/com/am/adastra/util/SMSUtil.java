package com.am.adastra.util;

import com.alibaba.fastjson.JSONObject;
import com.am.adastra.controller.UserController;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/27 20:50
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Slf4j
public class SMSUtil {
    private static final int APP_ID = 1400683116;
    private static final String APP_KEY = "95f32e5cdb35bbc88dbcedb41b6934e4";
    private static final int TEMPLATE_ID = 1419186;  // 通用 code
    private static final String SMS_SIGN = "isamumu";
    private static final int CODE_SIZE = 6;

    public static boolean sendSMS(String phoneNumber, HttpServletRequest request) {
        String code = KeyUtils.keyUtils(CODE_SIZE);
        String[] params = {code};
        SmsSingleSender sender = new SmsSingleSender(APP_ID, APP_KEY);
        SmsSingleSenderResult result = null;
        try {
            result = sender.sendWithParam("86", phoneNumber, TEMPLATE_ID, params, SMS_SIGN, "", "");
        } catch (HTTPException e) {  // HTTP响应码错误
            e.printStackTrace();
        } catch (IOException e) {  // 网络IO错误
            e.printStackTrace();
        }
        if (result.result != 0) {
            return false;
        }
        log.info(code);
        request.getSession().setMaxInactiveInterval(5 * 60);
        request.getSession().setAttribute(UserController.VERIFICATION_CODE_SESSION, code);
        return true;
    }
}
