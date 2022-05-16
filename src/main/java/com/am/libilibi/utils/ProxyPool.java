package com.am.libilibi.utils;

import com.am.libilibi.entity.LBProxy;
import com.am.libilibi.mapper.LBProxyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;


/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/5 15:08
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
//@Component
public class ProxyPool {
    private static List<LBProxy> generalLBProxyPools = new ArrayList<>();

    @Autowired
    public LBProxyMapper lbProxyMapper;

    private static ProxyPool proxyPool;

    @PostConstruct
    public void init() {
        System.out.println("init proxy pool...");
        proxyPool = this;
        generalLBProxyPools.addAll(this.lbProxyMapper.allProxy());
        VideosPool.run();

        Timer timer = new Timer();
        timer.schedule(new UpdateProxyPool(), 10 * 60 * 1000, 10 * 60 * 1000);
    }


    public static LBProxy getRandomProxy() {
        int i = new Random().nextInt(generalLBProxyPools.size());
        return generalLBProxyPools.get(i);
    }

    static class UpdateProxyPool extends TimerTask {
        @Override
        public void run() {
            System.out.println("update proxy...");
            generalLBProxyPools = ProxyPool.proxyPool.lbProxyMapper.allProxy();
        }
    }
}
