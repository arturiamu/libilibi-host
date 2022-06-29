package com.am.adastra.util;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

@Slf4j
public class GetIpInfo {

    public void getIpInfo() {
        try {
            Document document = Jsoup.connect("https://2022.ip138.com/").get();
            String ip = document.selectXpath("/html/body/p[1]/a[1]").get(0).text(); // 通过 Xpath 直接获取了网页里面的内容
            String ipInfo = document.selectXpath("/html/body/p[1]").get(0).text();
            String city = ipInfo.substring(ipInfo.lastIndexOf("来自：") + 3);
            log.info("ip:{}",ip);
            log.info("city:{}",city);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String getCity(String userIp) {
        if ("127.0.0.1".equals(userIp))return null;
        try {
            Document document = Jsoup.connect("https://www.ip138.com/iplookup.asp?ip="+userIp+"&action=2").get();
            String s = document.toString();
            int ASN = s.indexOf("ASN归属地");
            String substring = s.substring(ASN+9, ASN + 19);
            log.info("userIp:{}",substring);
            return substring;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
