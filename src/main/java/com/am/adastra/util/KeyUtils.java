package com.am.adastra.util;

import java.util.Random;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/27 20:55
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
public class KeyUtils {
    public static String keyUtils(int size) {
        String str = "0123456789";
        StringBuilder st = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            char ch = str.charAt(new Random().nextInt(str.length()));
            st.append(ch);
        }
        return st.toString().toLowerCase();
    }
}
