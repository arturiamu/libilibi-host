package com.am.adastra;

import com.am.adastra.util.GetIpInfo;

import java.time.Duration;
import java.time.LocalDateTime;

public class test {
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.of(2022, 6, 24, 21, 25, 20);
        LocalDateTime createTime = LocalDateTime.now();
        //获取是第几个小时
        int hour = createTime.getHour();
        System.out.println(hour);

        Duration duration = Duration.between(localDateTime,createTime);
        long hours = duration.toHours();//相差的小时数
        System.out.println("相差的小时数 "+ hours);
    }
}
