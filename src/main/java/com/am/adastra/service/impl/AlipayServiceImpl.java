package com.am.adastra.service.impl;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.kernel.util.ResponseChecker;
import com.alipay.easysdk.payment.common.models.AlipayTradeQueryResponse;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import com.am.adastra.entity.UserVip;
import com.am.adastra.mapper.AlipayMapper;
import com.am.adastra.service.AlipayService;
import com.am.adastra.util.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * @PackagePathcom.am.adastra.service.impl
 * @Classname VipServiceImpl
 * @Description TODO
 * @Version 1.0.0
 * @Date 2022/6/26 21:31
 * @Created by Volcan
 */
@Slf4j
@Component
public class AlipayServiceImpl implements AlipayService {
    @Autowired
    AlipayMapper alipayMapper;

    @Autowired
    AlipayService alipayService;

    @Override
    public String toPay(String subject, BigDecimal money, long id) throws Exception {
        String outTradeNo = this.generateTradeNo();
        AlipayTradePagePayResponse pay = Factory.Payment.Page().pay(subject, outTradeNo,
                String.valueOf(money), "http://adastra.isamumu.cn/");
        String payForm = null;
        if (ResponseChecker.success(pay)) {
            payForm = pay.getBody();
        }

        UserVip userVip = new UserVip();
        userVip.setOutTradeNo(outTradeNo);
            log.info("存在用户id为:"+id);
            log.info("订单号:"+outTradeNo);
            log.info("购买的vip类型为:"+subject);
            userVip.setOutTradeNo(outTradeNo);
            userVip.setId(id);
            userVip.setVipType(subject);
            userVip.setIsPaySuccess("false");
            alipayService.insertVipInfo(userVip);

        return payForm;
    }


    @Override
    public void updataInfo(UserVip userVip) {
        alipayMapper.updataVipInfo(userVip);
    }


    /**
     * 通过时间生成外部订单号 out_trade_no
     * @return
     */
    private String generateTradeNo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        String tradeNo = LocalDateTime.now(ZoneOffset.of("+8")).format(formatter);
        System.out.println(tradeNo);
        return tradeNo;
    }

    /**
     * 查询交易状态
     * @param outTradeNo 生成的外部订单号 out_trade_no
     * @return
     * @throws Exception
     */
    public Object queryTradeStatus(String outTradeNo) throws Exception {
        AlipayTradeQueryResponse query = Factory.Payment.Common().query(outTradeNo);
        Map<String, Object> map = JSONUtils.jsonToMap(query.getHttpBody());

        // 返回交易结果, 是否交易成功需要根据该对象中的 trade_status 来确定
        // trade_status 的枚举值如下, 请见 https://opendocs.alipay.com/apis/api_1/alipay.trade.query
        // WAIT_BUYER_PAY（交易创建，等待买家付款）
        // TRADE_CLOSED（未付款交易超时关闭，或支付完成后全额退款）
        // TRADE_SUCCESS（交易支付成功）
        // TRADE_FINISHED（交易结束，不可退款）
        // 当 trade_status 等于 TRADE_SUCCESS 或 TRADE_FINISHED 时, 表示支付成功
        return map.get("alipay_trade_query_response");
    }

    @Override
    public boolean insertVipInfo(UserVip userVip){
        alipayMapper.insertVipInfo(userVip);
        return false;
    }
    @Override
    public void updataVipInfo(UserVip userVip) {

    }

    @Override
    public UserVip selectByOutTradeNo(String outTradeNo) {
        UserVip userVip = alipayMapper.selectByOutTradeNo(outTradeNo);
        return userVip;
    }
}
