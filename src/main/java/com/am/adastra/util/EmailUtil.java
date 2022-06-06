package com.am.adastra.util;

import com.am.adastra.controller.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;


/**
 * @Author: lyy
 * @Version: 1.0
 * @JDK-version: 1.8
 * @Date: 2022/6/6 11:32
 * @Description:
 */

@Component
public class EmailUtil {
    @Autowired
    TemplateEngine templateEngine;
    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String from;
    @Value("${sms.size}")
    private int CODE_SIZE;
    @Value("${sms.app.sign}")
    private String sign;

    public boolean sendRegisterMail(String to, HttpServletRequest request) {
        String code = KeyUtils.keyUtils(CODE_SIZE);
        Context context = new Context();
        for (int i = 0; i < CODE_SIZE; i++) {
            context.setVariable("verifyCode" + i, code.charAt(i));
        }
        String emailContent = templateEngine.process("register", context);
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(sign);
            helper.setText(emailContent, true);
            mailSender.send(message);
            if (request != null) {
                request.getSession().setMaxInactiveInterval(5 * 60);
                request.getSession().setAttribute(UserController.VERIFICATION_CODE_SESSION, code);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

