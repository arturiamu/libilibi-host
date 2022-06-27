package com.am.adastra.service;

import com.am.adastra.entity.UserVip;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @PackagePathcom.am.adastra.service
 * @Classname VipService
 * @Description TODO
 * @Version 1.0.0
 * @Date 2022/6/26 21:31
 * @Created by Volcan
 */
@Service
public interface AlipayService {


    String toPay(String subject, BigDecimal money, long id) throws Exception;

    Object queryTradeStatus(String outTradeNo) throws Exception;

    void updataInfo(UserVip userVip);

    boolean insertVipInfo(UserVip userVip);

    void updataVipInfo(UserVip userVip);

    UserVip selectByOutTradeNo(String outTradeNo);
}
