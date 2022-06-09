package com.am.adastra.controller;

import com.am.adastra.entity.User;
import com.am.adastra.entity.dto.AdviseDTO;
import com.am.adastra.service.AdviseService;
import com.am.adastra.service.UserService;
import com.am.adastra.util.Result;
import com.am.adastra.util.State;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @Author: lyy
 * @Version: 1.0
 * @JDK-version: 1.8
 * @Date: 2022/6/9 7:53
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/advise")
public class AdviseController {

    @Autowired
    private AdviseService adviseService;
    @Autowired
    private UserService userService;

    //添加用户的建议
    @PostMapping("/add")
    public Result<Void> advise(@RequestBody @Valid AdviseDTO advise, BindingResult errors, HttpServletRequest request) {
        log.info("用户建议:{}", advise);
        Result<Void> result = new Result<>();

        if (errors.hasErrors()) {
            result = new Result<>();
            result.setFail(errors.getFieldError().getDefaultMessage(), State.ERR_USER_INFO);
            return result;
        }

        User getUser = (User) request.getSession().getAttribute(UserController.USER_INFO_SESSION);
        if (getUser != null) {
            advise.setUserId(getUser.getId());
        }
        result.setSuccess("意见反馈成功", null);
        return result;
    }

    //通过id来查询
    @GetMapping("/select")
    public Result<List<AdviseDTO>> select(HttpServletRequest request) {
        //  获取当前用户的用户 id
        log.info("查询建议");
        User user = userService.isLogin(request.getSession());
        Integer userId = user.getId();

        //调用业务层查询所有的用户历史信息
        return adviseService.selectById(userId);
    }

}
