package com.am.adastra.controller;

import com.am.adastra.entity.User;
import com.am.adastra.entity.dto.VideoOperateDTO;
import com.am.adastra.entity.vo.UserCollectionSimpleVO;
import com.am.adastra.ex.ValidException;
import com.am.adastra.service.UserCollectionService;
import com.am.adastra.service.UserService;
import com.am.adastra.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/*
 * 用户收藏夹的controller
 * */
@Slf4j
@RestController
@RequestMapping("/collection")
public class UserCollectionController {
    @Resource
    private UserService userService;
    @Resource
    private UserCollectionService userCollectionService;


    /*
            用户添加视频到收藏夹中*/
    @PostMapping("/add")
    public Result<Void> add(@RequestBody @Validated VideoOperateDTO videoOperateDTO, BindingResult errors, HttpServletRequest request) {
        //先判断传递的信息是否有误
        log.info("用户收藏============" + videoOperateDTO);
        Result<Void> result = new Result<>();

        //前端传递过来的格式出错就直接返回
        if (errors.hasErrors()) {
            throw new ValidException(errors.getFieldError().getDefaultMessage());
        }

        //  获取当前用户的用户 id
        User user = userService.isLogin(request.getSession());
        Long userId = user.getId();
        videoOperateDTO.setUid(userId);
        log.info("当前的用户id为" + userId);

        //调用逻辑层将信息添加到用户的收藏夹中
        userCollectionService.add(videoOperateDTO);
        result.setSuccess();
        return result;

    }


    /*  通过用户分类的查看用户的收藏
     */
    @GetMapping("/selectByCategory")
    public Result<List<UserCollectionSimpleVO>> selectByCategory(String category, HttpServletRequest request) {

        Result<List<UserCollectionSimpleVO>> result = new Result<>();
//        1.获取当前用户的用户 id
        User user = userService.isLogin(request.getSession());
        Long userId = user.getId();

        log.info("用户ID： " + userId);
        log.info("用户分类：  " + category);

        //2.调用逻辑层获取到此用户的视频分类信息  将用户id和分类信息传递过去
        List<UserCollectionSimpleVO> list = userCollectionService.selectByCollection(userId, category);

        result.setSuccess(list);
        return result;
    }


    /**
     * 查询该用户的所有的收藏记录
     *
     * @param request
     * @return
     */
    @GetMapping("/selectCategory")
    public Result<Map<String, List<UserCollectionSimpleVO>>> selectCategory(HttpServletRequest request) {
        Result<Map<String, List<UserCollectionSimpleVO>>> result = new Result<>();

        //1.获取当前用户的用户 id
        User user = userService.isLogin(request.getSession());
        Long userId = user.getId();
        log.info("用户id ===》 " + userId);

        Map<String, List<UserCollectionSimpleVO>> stringListMap = userCollectionService.selectCategory(userId);
        result.setSuccess(stringListMap);
        return result;


    }


}
