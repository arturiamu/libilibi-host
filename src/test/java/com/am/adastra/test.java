package com.am.adastra;

import com.am.adastra.util.GetIpInfo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class test {
    public static void main(String[] args) {
        Map<String , Integer> map = new HashMap<>();
        map.put("lyy",1);
        System.out.println(map.get("lyy"));
        map.put("lyy",map.get("lyy")+2);
        System.out.println(map.get("lyy"));
    }
}
