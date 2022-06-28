package com.am.adastra.controller;

import com.am.adastra.entity.User;
import com.am.adastra.entity.dto.AdviseDTO;
import com.am.adastra.ex.ValidException;
import com.am.adastra.service.AdviseService;
import com.am.adastra.service.UserService;
import com.am.adastra.util.Result;
import com.am.adastra.util.State;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * @Description  ：
 * @Author       : ArturiaMu KUMST-Stu
 * @Params        : @param null
 * @Return       :
 * @Exception    :
 * @Date         : 2022/6/25 13:11
 */
@Slf4j
@RestController
@Api(tags = "用户建议模块")
@RequestMapping("/advise")
public class AdviseController {

    @Resource
    private AdviseService adviseService;
    @Resource
    private UserService userService;

    @ApiOperation("用户反馈建议")
    @ApiOperationSupport(order = 0)
    @PostMapping("/add")
    public Result<Void> addAdvise(@RequestBody @Valid AdviseDTO advise, BindingResult errors, HttpServletRequest request) {
        log.info("用户建议:{}", advise);
        Result<Void> result = new Result<>();

        if (errors.hasErrors()) {
            result = new Result<>();
            throw new ValidException(errors.getFieldError().getDefaultMessage());
        }

        User getUser = (User) request.getSession().getAttribute(UserController.USER_INFO_SESSION);
        if (getUser != null) {
            advise.setUid(getUser.getId());
        }
        adviseService.add(advise);
        result.setSuccess("意见反馈成功", null);
        return result;
    }

    @ApiOperation("获取用户的建议")
    @ApiOperationSupport(order = 5)
    @GetMapping("/select")
    public Result<List<AdviseDTO>> select(HttpServletRequest request) {
        //  获取当前用户的用户 id
        Result<List<AdviseDTO>> result = new Result<>();
        log.info("查询建议");
        User user = userService.isLogin(request.getSession());
        Long userId = user.getId();
        //调用业务层查询所有的用户历史信息
        List<AdviseDTO> adviseDTOList = adviseService.selectById(userId);
        result.setSuccess(adviseDTOList);
        return result;
    }

}
