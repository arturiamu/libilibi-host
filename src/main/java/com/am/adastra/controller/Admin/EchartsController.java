package com.am.adastra.controller.Admin;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Quarter;
import com.am.adastra.entity.User;
import com.am.adastra.entity.vo.UserVO;
import com.am.adastra.service.AdminService;
import com.am.adastra.service.UserService;
import com.am.adastra.util.Result;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@Api(tags = "数据模块")
@RestController
@RequestMapping("/echarts")
public class EchartsController {
    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;


    /**
     * 获取每个月新增的人数，返回给前端
     * 还有总人数
     * @return
     */
    @ApiOperation("获取每个月新增的人数")
    @ApiOperationSupport(order = 0)
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
    @ApiOperation("获取21个视频大分类的观看次数")
    @ApiOperationSupport(order = 5)
    @GetMapping("/videoHeat")
    public Result<Map<String,Object>> videoHeat(){
        Result<Map<String,Object>> result = new Result<>();

        Map<String,Object> map = adminService.videoHeat();

        result.setSuccess(map);

        return result;
    }
}
