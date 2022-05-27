package com.am.adastra.util;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.am.adastra.controller.UserController;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
    private static final int TEMPLATE_ID = 1414315;  // 注册 code + time
    //    private static final int TEMPLATE_ID = 1419186;  // 通用 code
    private static final String SMS_SIGN = "isamumu";
    private static final int CODE_SIZE = 6;

    public static void main(String[] args) {
        sendSMS(null, "15911245016");
    }

    public static String sendSMS(HttpServletRequest request, String phoneNumber) {
        String reStr = "";
        String code = KeyUtils.keyUtils(CODE_SIZE);
        try {
            String[] params = {code, "5"};
            SmsSingleSender ssender = new SmsSingleSender(APP_ID, APP_KEY);
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumber, TEMPLATE_ID, params, SMS_SIGN, "", "");
            if (result.result != 0) {
                reStr = "error";
            }
            JSONObject json = new JSONObject();
            json.put("Code", code);
            json.put("createTime", System.currentTimeMillis());
            request.getSession().setAttribute(UserController.CODE_NAME, json);
            System.out.println(request.getSession().getAttribute(UserController.CODE_NAME));
            reStr = "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reStr;
    }

}
