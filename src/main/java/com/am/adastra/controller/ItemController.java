package com.am.adastra.controller;

import com.am.adastra.entity.Item;
import com.am.adastra.mapper.ItemMapper;
import com.am.adastra.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/6/6 11:00
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@RestController
@RequestMapping("/item")
public class ItemController {

    public static final List<Item> defaultItems = new ArrayList<Item>() {{
        add(new Item(6, "舞蹈", "dance", 129));
        add(new Item(1, "游戏", "game", 4));
        add(new Item(2, "动画", "animation", 1));
    }};

    @Autowired
    ItemMapper itemMapper;

    @GetMapping("/all")
    public Result<List<Item>> itemList() {
        Result<List<Item>> result = new Result<>();
        List<Item> items = itemMapper.getAll();
        result.setSuccess(items);
        return result;
    }

    @GetMapping("/default")
    public Result<List<Item>> itemDefault() {
        Result<List<Item>> result = new Result<>();
        result.setSuccess(defaultItems);
        return result;
    }
}