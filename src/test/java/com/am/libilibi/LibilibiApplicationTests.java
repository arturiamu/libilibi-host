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
//        LBProxy lbproxy = ProxyPool.getRandomProxy();
//        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(lbproxy.getHost(), Integer.parseInt(lbproxy.getPort())));

        String px = "176.56.107.99:50374";
        String[] s = px.split(":");
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(s[0], Integer.parseInt(s[1])));
        String url = "http://api.bilibili.com/x/web-interface/view?bvid=BV1xL4y1L7JT";
        try {
            String jsStr = HttpUtils.httpRequest(url, "GET", null, proxy);
            System.out.println(jsStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
