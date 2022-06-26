package com.am.adastra.controller.Admin;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Quarter;
import com.am.adastra.entity.User;
import com.am.adastra.entity.vo.UserVO;
import com.am.adastra.service.AdminService;
import com.am.adastra.service.UserService;
import com.am.adastra.util.Result;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/echarts")
public class EchartsController {
    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;

    /**
     * 测试数据
     * @return
     */
    @GetMapping("/ss")
    public Result<Map<String , Object>> get(){
        Result<Map<String , Object>> result = new Result<>();
        Map<String , Object> map = new HashMap<>();
        map.put("X", CollUtil.newArrayList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"));
        map.put("Y", CollUtil.newArrayList(150, 230, 224, 218, 135, 147, 260));

        result.setSuccess(map);
        return result;
    }

    /**
     * 获取每个月新增的人数，返回给前端
     * 还有总人数
     * @return
     */
    @GetMapping("/members")
    public Result<Map<String,Object>> members() {
        Result<Map<String,Object>> result = new Result<>();

        Map<String,Object> map = adminService.members();

        result.setSuccess(map);

        return result;

    }

    /**
     * 获取21个视频大分类的观看次数，返回给前端
     */
    @GetMapping("/videoHeat")
    public Result<Map<String,Object>> videoHeat(){
        Result<Map<String,Object>> result = new Result<>();

        Map<String,Object> map = adminService.videoHeat();

        result.setSuccess(map);

        return result;
    }

    /**
     * 获取用户各个时间段的观看数据
     * 从00：00 到 23：59
     * 每一个小时作为一个分隔
     * 数据分为3组，今天  一天前  七天前
     * @return
     */
    @GetMapping("/viewingPeriod")
    public Result<Map<String,Object>> viewingPeriod(){
        Result<Map<String,Object>> result = new Result<>();

        Map<String,Object> map = adminService.viewingPeriod();

        result.setSuccess(map);

        return result;
    }

    /**
     * 获取各个时间段的访问人数
     * @return
     */
    @GetMapping("/numberPersons")
    public Result<Map<String,Object>> numberPersons(){
        Result<Map<String,Object>> result = new Result<>();

        Map<String,Object> map = adminService.numberPersons();

        result.setSuccess(map);

        return result;
    }

}
