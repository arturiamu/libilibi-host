package com.am.adastra;


import cn.hutool.core.date.DateUtil;
import com.am.adastra.util.GetDatePoor;

import java.time.LocalDateTime;
import java.util.Date;

public class testDate {
    public static void main(String[] args) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;

//        Date startDate = new Date();
        Date startDate = DateUtil.parseDate("2022-07-4 10:00:16");
//        Date endDate =  new Date();
        Date endDate =  DateUtil.parseDate("2022-07-6 11:00:16");
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - startDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        System.out.println("开始时间" + startDate);
        System.out.println("结束时间" + endDate);
        System.out.println(day + "天" + hour + "小时" + min + "分钟");
    }
}
