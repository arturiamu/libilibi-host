package com.am.adastra.util;

import org.springframework.util.StringUtils;

import java.lang.reflect.Field;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/27 11:40
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
public class ClassExamine {
    public static <T> void objectOverlap(T origin, T intactObject) throws Exception {
        Field[] fields = origin.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.getType() == String.class) {
                if (StringUtils.isEmpty((String) field.get(origin))) {
                    field.set(origin, field.get(intactObject));
                }
            } else {
                if (field.get(origin) == null) {
                    field.set(origin, field.get(intactObject));
                }
            }
        }
    }
}
