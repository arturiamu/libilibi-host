package com.am.adastra.util;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;


public class GetIpInfo {

    public void getIpInfo() {
        try {
            Document document = Jsoup.connect("https://2022.ip138.com/").get();
            String ip = document.selectXpath("/html/body/p[1]/a[1]").get(0).text(); // 通过 Xpath 直接获取了网页里面的内容
            String ipInfo = document.selectXpath("/html/body/p[1]").get(0).text();
            String city = ipInfo.substring(ipInfo.lastIndexOf("来自：") + 3);
            System.out.println(ip);
            System.out.println(city);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
