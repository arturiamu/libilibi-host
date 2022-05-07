package com.am.libilibi.utils;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;


/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/1 15:11
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
public class HttpUtils {

    public static String httpsRequest(String url, String method, Proxy proxy) throws Exception {
        URL reqURL = null; //创建URL对象
        reqURL = new URL(url);
        HttpsURLConnection httpsConn = null;

        if (proxy != null) {
            httpsConn = (HttpsURLConnection) reqURL.openConnection(proxy);
        } else {
            httpsConn = (HttpsURLConnection) reqURL.openConnection();
        }
        httpsConn.setRequestMethod(method);
        InputStreamReader isr = new InputStreamReader(httpsConn.getInputStream(), StandardCharsets.UTF_8);
        int respInt = isr.read();
        StringBuilder sb = new StringBuilder();
        while (respInt != -1) {
            sb.append((char) respInt);
            respInt = isr.read();
        }
        return sb.toString();
    }

    public static String httpRequest(String requestUrl, String requestMethod, String outputStr, Proxy proxy) throws Exception {
        StringBuilder buffer = null;
        URL url = new URL(requestUrl);
        HttpURLConnection conn = null;
        if (proxy == null) {
            conn = (HttpURLConnection) url.openConnection();
        } else {
            conn = (HttpURLConnection) url.openConnection(proxy);
        }

        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod(requestMethod);
        conn.connect();
        conn.setConnectTimeout(3000);
        //往服务器端写内容 也就是发起http请求需要带的参数
        if (null != outputStr) {
            OutputStream os = conn.getOutputStream();
            os.write(outputStr.getBytes(StandardCharsets.UTF_8));
            os.close();
        }
        //读取服务器端返回的内容
        InputStream is = conn.getInputStream();
        InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        buffer = new StringBuilder();
        String line = null;
        while ((line = br.readLine()) != null) {
            buffer.append(line);
        }
        assert buffer != null;
        return buffer.toString();
    }
}
