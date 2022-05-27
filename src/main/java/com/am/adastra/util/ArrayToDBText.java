package com.am.adastra.util;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/25 20:07
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
public class ArrayToDBText {
    public static String getString(int[] arr) {
        StringBuffer sb = new StringBuffer("(");
        for (int i = 0; i < arr.length; i++) {
            String as = String.valueOf(arr[i]);
            sb.append(as);
            if (i != arr.length - 1) {
                sb.append(",");
            }
        }
        sb.append(")");
        return sb.toString();
    }
}
