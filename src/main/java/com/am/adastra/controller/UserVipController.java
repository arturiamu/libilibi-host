package com.am.adastra.controller;

import com.alipay.easysdk.factory.Factory;
import com.am.adastra.entity.User;
import com.am.adastra.entity.UserVip;
import com.am.adastra.service.GetVipService;
import com.am.adastra.service.UserService;
import com.am.adastra.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @PackagePathcom.am.adastra.controller
 * @Classname UserVipController
 * @Description TODO
 * @Version 1.0.0
 * @Date 2022/6/16 15:18
 * @Created by Volcan
 */
@Slf4j
@RestController
@RequestMapping("/UserVip")
public class UserVipController {

    @Autowired
    GetVipService getVipService;

    @Resource
    private UserService userService;


    @GetMapping("/getVip")
    public Result<UserVip> getVip(int vipLevel,HttpServletRequest request) {
        final long MOUNTH_TIME_IN_MILLIS = 2592000000L;
        final long HALF_YEAR_TIME_IN_MILLIS = 15552000000L;
        final long YEAR_TIME_IN_MILLIS = 31104000000L;
        final long ONE_DAY_TIME_IN_MILLIS = 86400000;

        Result<UserVip> result = new Result<>();
        int level = vipLevel;
        long startVipTime = 0L;
        long endVipTime= 0L;

        Date date = new Date();
        UserVip userVip1 = new UserVip();
        User user = userService.isLogin(request.getSession());
        Long id = user.getId();
        log.info("登录用户ID为"+id);
        UserVip userVip = getVipService.selectById(id);

        if (userVip!=null) {
            startVipTime = userVip.getStartVipTime();
            endVipTime = userVip.getEndVipTime();
            log.info("startVipTime:"+startVipTime);
            log.info("endVipTime:"+endVipTime);
            switch (level) {
                case 1:
                    endVipTime = endVipTime + MOUNTH_TIME_IN_MILLIS / ONE_DAY_TIME_IN_MILLIS + 1;
                    break;
                case 2:
                    endVipTime = endVipTime + HALF_YEAR_TIME_IN_MILLIS / ONE_DAY_TIME_IN_MILLIS + 1;
                    break;
                case 3:
                    endVipTime = endVipTime + YEAR_TIME_IN_MILLIS / ONE_DAY_TIME_IN_MILLIS + 1;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + level);
            }
            userVip.setEndVipTime(endVipTime);
            getVipService.updataVip(userVip);
            result.setSuccess("更新会员成功",userVip1);
            return result;
        }


        if (level >= 1 && level <= 3) {
            startVipTime = date.getTime() / ONE_DAY_TIME_IN_MILLIS;
                /*确定会员等级之后确定endVipTime,endVipTime倍计算成为以天为单位,则做会员资格认证时每天验证一次就行
                +1的意思是在每天0点进行会员资格认证时会导致实际会员体验天数偏少.索性多给一天会员时间*/
            switch (level) {
                case 1:
                    endVipTime = startVipTime + MOUNTH_TIME_IN_MILLIS / ONE_DAY_TIME_IN_MILLIS + 1;
                    break;
                case 2:
                    endVipTime = startVipTime + HALF_YEAR_TIME_IN_MILLIS / ONE_DAY_TIME_IN_MILLIS + 1;
                    break;
                case 3:
                    endVipTime = startVipTime + YEAR_TIME_IN_MILLIS / ONE_DAY_TIME_IN_MILLIS + 1;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + level);
            }
        }
        userVip1.setId(id);
        log.info("充值用户ID为:"+userVip1.getId());
        userVip1.setStartVipTime(startVipTime);
        userVip1.setEndVipTime(endVipTime);
        getVipService.addVip(userVip1);
        result.setSuccess("添加会员成功",userVip1);
        return result;
    }

    @PostMapping("notify")
    public String payNotify(HttpServletRequest request) throws Exception {
        if (request.getParameter("trade_status").equals("TRADE_SUCCESS")) {
            System.out.println("=========支付宝异步回调========");

            Map<String, String> params = new HashMap<>();
            Map<String, String[]> requestParams = request.getParameterMap();
            for (String name : requestParams.keySet()) {
                params.put(name, request.getParameter(name));
                // System.out.println(name + " = " + request.getParameter(name));
            }

            String tradeNo = params.get("out_trade_no");
            String gmtPayment = params.get("gmt_payment");
            String alipayTradeNo = params.get("trade_no");
            // 支付宝验签
            if (Factory.Payment.Common().verifyNotify(params)) {
                // 验签通过
                log.info("交易名称: " + params.get("subject"));
                log.info("交易状态: " + params.get("trade_status"));
                log.info("支付宝交易凭证号: " + params.get("trade_no"));
                log.info("商户订单号: " + params.get("out_trade_no"));
                log.info("交易金额: " + params.get("total_amount"));
                log.info("买家在支付宝唯一id: " + params.get("buyer_id"));
                log.info("买家付款时间: " + params.get("gmt_payment"));
                log.info("买家付款金额: " + params.get("buyer_pay_amount"));

                // 更新订单已支付

            }
        }
        return "success";
    }
}

