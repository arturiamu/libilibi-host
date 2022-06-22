package com.am.adastra.controller.Admin;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Quarter;
import com.am.adastra.entity.User;
import com.am.adastra.entity.vo.UserVO;
import com.am.adastra.service.UserService;
import com.am.adastra.util.Result;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/echarts")
public class EchartsController {
    @Autowired
    private UserService userService;

    @GetMapping("/ss")
    public Result<Map<String , Object>> get(){
        Result<Map<String , Object>> result = new Result<>();
        Map<String , Object> map = new HashMap<>();
        map.put("X", CollUtil.newArrayList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"));
        map.put("Y", CollUtil.newArrayList(150, 230, 224, 218, 135, 147, 260));

        result.setSuccess(map);
        return result;
    }

    @GetMapping("/members")
    public Result members() {
        Result<List<int[]>> result = new Result<>();
        List<UserVO> userList = userService.list();
        int[] q = new int[12];
        for (UserVO user : userList){
            Date createTime = user.getCreateTime();
            int month = DateUtil.month(createTime);
            if (month>0 && month<13){
                q[month-1] ++ ;
            }
        }

        ArrayList<int[]> ints = CollUtil.newArrayList(q);
        result.setSuccess(ints);
        return result;

    }
}
