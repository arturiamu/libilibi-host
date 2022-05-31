package com.am.adastra.util;

import com.alibaba.fastjson.JSON;
import com.am.adastra.entity.Item;
import com.am.adastra.entity.User;
import com.am.adastra.entity.UserDB;

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

    public static UserDB userToDB(User user) {
        String jsStr = JSON.toJSONString(user.getItems());
        return new UserDB(user.getId(), user.getUsername(), user.getPassword(), user.getAccount(), jsStr, user.getState());
    }

    public static User DBToUser(UserDB userDB) {
        List<Item> itemList = JSON.parseArray(userDB.getJsItems(), Item.class);
        Item[] items = itemList.toArray(new Item[itemList.size()]);
        return new User(userDB.getId(), userDB.getUsername(), userDB.getPassword(), userDB.getAccount(), items, userDB.getState());
    }
}
