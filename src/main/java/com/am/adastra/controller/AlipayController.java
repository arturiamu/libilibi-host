package com.am.adastra.controller;

import com.alipay.easysdk.factory.Factory;
import com.am.adastra.entity.User;
import com.am.adastra.entity.UserVip;
import com.am.adastra.service.UserService;
import com.am.adastra.service.AlipayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/pay")
public class AlipayController {

    @Autowired
    private AlipayService alipayService;

    @Resource
    private UserService userService;

    @GetMapping("/topay")
    @ResponseBody
    public String toPay(String subject, BigDecimal money,HttpServletRequest request) throws Exception {
        User user = userService.isLogin(request.getSession());
        long id = user.getId();
        log.info("登录的用户id为:"+id);
        String form = alipayService.toPay(subject, money,id);
        return form;
    }

    @PostMapping("/callback")
    @ResponseBody
    public String notifyCallback(HttpServletRequest request) throws Exception {
        System.out.println("进入异步");
        String success = "success";
        String failure = "failure";

        UserVip userVip = new UserVip();
        LocalDate startVipTime = LocalDate.now();
        LocalDate endVipTime = null;

        String trade_status ="";
        String out_trade_no = "";
        String subject = "";
        String tradeStatus = "TRADE_SUCCESS";

        // https://opendocs.alipay.com/open/54/00y8k9 新老版本说明中有异步通知的新版说明
        // 获取支付宝异步回调信息, 将其转为 Map<String, String>
        Map<String, String> params = new HashMap<>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
            params.put(name, valueStr);
        }

        trade_status = params.get("trade_status");
        out_trade_no = params.get("out_trade_no");
        subject = params.get("subject");
        if (trade_status.equals(tradeStatus)){
            switch (subject){
                case "mouthvip":
                    endVipTime = startVipTime.plusMonths(1); // 目前时间加1个月
                    break;
                case "halfYearVip":
                    endVipTime = startVipTime.plusMonths(6);//目前时间加6个月
                    break;
                case "yearVip":
                    endVipTime = startVipTime.plusYears(1);//目前时间加1年
                    break;
            }
            Date start_vip_time = Date.valueOf(startVipTime);
            Date end_vip_time = Date.valueOf(endVipTime);
            userVip.setOutTradeNo(out_trade_no);
            userVip.setStartVipTime(start_vip_time);
            userVip.setEndVipTime(end_vip_time);
            userVip.setIsPaySuccess(trade_status);
            System.out.println(userVip);
            alipayService.updataInfo(userVip);

        }


        System.out.println(params);
        // 新版 SDK 不用移除 sign_type
        // params.remove("sign_type");

        // 验签
        boolean signVerified = Factory.Payment.Common().verifyNotify(params);

        if(signVerified){ // 验签通过
            System.out.println("通过验签");
            return success;
        }else{ // 验签失败
            return failure;
        }
    }

    @GetMapping("/query")
    @ResponseBody
    public Object queryTradeStatus(String outTradeNo) throws Exception {
        Object result = alipayService.queryTradeStatus(outTradeNo);
        return result;
    }
}
