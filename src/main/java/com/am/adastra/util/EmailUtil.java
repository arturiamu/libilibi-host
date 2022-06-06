package com.am.adastra.util;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * @Author: lyy
 * @Version: 1.0
 * @JDK-version: 1.8
 * @Date: 2022/6/6 11:32
 * @Description:
 */
public class EmailUtil {
    private static final JavaMailSenderImpl sender = new JavaMailSenderImpl();

    static {
        //服务器
        sender.setHost("smtp.qq.com");
        //协议
        sender.setProtocol("smtps");
        //端口号
        sender.setPort(465);
        //邮箱账号
        sender.setUsername("1589691384@qq.com");
        //邮箱授权码
        sender.setPassword("idyakyhjgadyhfab");
        //编码
        sender.setDefaultEncoding("Utf-8");
        Properties p = new Properties();
        p.setProperty("mail.smtp.ssl.enable", "true");
        sender.setJavaMailProperties(p);
    }

    /**
     * 发送邮件
     *
     * @param subject
     * @param text
     * @return
     */
    public static boolean sendEmail(String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            //设置邮件标题
            message.setSubject(subject);
            //设置邮件正文
            message.setText(text);
            //设置邮件发送人
            message.setFrom("1589691384@qq.com");
            //设置邮件接收人
            message.setTo("2416588853@qq.com");
            //发送邮件
            sender.send(message);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
