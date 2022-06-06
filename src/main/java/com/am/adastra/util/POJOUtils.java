package com.am.adastra.util;

import com.alibaba.fastjson.JSON;
import com.am.adastra.entity.Item;
import com.am.adastra.entity.User;
import com.am.adastra.entity.UserDBO;

import java.util.List;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/31 9:25
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
public class POJOUtils {

    public static UserDBO userToDB(User user) {
        String jsStr = JSON.toJSONString(user.getItems());
        return new UserDBO(user.getId(), user.getUsername(), user.getPassword(), user.getAccount(), jsStr, user.getState());
    }

    public static User DBToUser(UserDBO userDBO) {
        List<Item> itemList = JSON.parseArray(userDBO.getJsItems(), Item.class);
        Item[] items = itemList.toArray(new Item[itemList.size()]);
        return new User(userDBO.getId(), userDBO.getUsername(), userDBO.getPassword(), userDBO.getAccount(), items, userDBO.getState());
    }
}
