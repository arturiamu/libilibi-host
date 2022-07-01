package com.am.adastra;

import com.am.adastra.service.UserService;
import com.am.adastra.util.GetIpInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class testIp {

    @Autowired
    UserService userService;

    public static void main(String[] args) {
        String ip = "112.6.117.135";
        String city = GetIpInfo.getCity(ip);

        System.out.println(city);

        Integer city1 = city.indexOf("市");
        Integer city2 = city.indexOf("省");
        String cityMunicipal = city.substring(city2+2,city1);
        System.out.println(cityMunicipal);
    }

    @Test
    void dd(){
        List<Map<String, Object>> maps = userService.ipList();
        for (int i = 0; i < maps.size(); i++) {
            System.out.println( maps.get(i));
        }
    }

}
