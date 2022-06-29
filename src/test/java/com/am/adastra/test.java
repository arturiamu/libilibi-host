package com.am.adastra;

import com.am.adastra.util.GetIpInfo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class test {
    public static void main(String[] args) {
        List<Map<String , Integer>> list = new ArrayList<>();

        Map<String , Integer> map = new HashMap<>();
        map.put("name",1);
        list.add(map);
        System.out.println(list);

        for (int i = 0; i <list.size(); i++) {

        }
        Map<String , Integer> map1 = new HashMap<>();
        map1.put("lyy",1);
        list.add(map1);
        System.out.println(list);

    }
}
