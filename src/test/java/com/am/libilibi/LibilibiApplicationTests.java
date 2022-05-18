package com.am.libilibi;

import com.am.libilibi.blapi.BLVideoZone;
import com.am.libilibi.entity.LBProxy;
import com.am.libilibi.utils.HttpUtils;
import com.am.libilibi.utils.ProxyPool;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.InetSocketAddress;
import java.net.Proxy;


@SpringBootTest
class LibilibiApplicationTests {

    @Test
    void contextLoads() {
        String url = "http://api.bilibili.com/x/web-interface/view?bvid=BV1xL4y1L7JT";
        url = "https://www.tyhttp.com/free/";
        try {
            String jsStr = HttpUtils.httpRequest(url, "GET", null, null);
            System.out.println(jsStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
