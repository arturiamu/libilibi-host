package com.am.adastra.util;

import com.am.adastra.controller.UserController;
import com.am.adastra.ex.SystemException;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/27 20:50
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Slf4j
@Component
public class SMSUtil {
    @Value("${sms.app.id}")
    private int APP_ID;
    @Value("${sms.app.key}")
    private String APP_KEY;
    @Value("${sms.app.template.universal}")
    private int TEMPLATE_ID;
    @Value("${sms.app.sign}")
    private String SMS_SIGN;
    @Value("${sms.size}")
    private int CODE_SIZE;

    public boolean sendSMS(String phoneNumber, HttpServletRequest request) {
        String code = KeyUtils.keyUtils(CODE_SIZE);
        String[] params = {code};
        SmsSingleSender sender = new SmsSingleSender(APP_ID, APP_KEY);
        SmsSingleSenderResult result = null;
        try {
            result = sender.sendWithParam("86", phoneNumber, TEMPLATE_ID, params, SMS_SIGN, "", "");
        } catch (HTTPException | IOException e) {  // HTTP响应码错误
            throw new SystemException("操作频繁，请稍后重试");
        } // 网络IO错误

        if (result.result != 0) {
            return false;
        }
        log.warn(code);
        if (request != null) {
            request.getSession().setMaxInactiveInterval(5 * 60);
            request.getSession().setAttribute(UserController.VERIFICATION_CODE_SESSION, code);
        }
        return true;
    }
}
