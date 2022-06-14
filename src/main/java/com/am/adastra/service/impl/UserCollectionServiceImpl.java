package com.am.adastra.service.impl;

import com.am.adastra.entity.Video;
import com.am.adastra.entity.dto.VideoOperateDTO;
import com.am.adastra.entity.vo.UserCollectionSimpleVO;
import com.am.adastra.ex.RepeatException;
import com.am.adastra.ex.SystemException;
import com.am.adastra.mapper.UserCollectionMapper;
import com.am.adastra.service.UserCollectionService;
import com.am.adastra.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class UserCollectionServiceImpl implements UserCollectionService {

    @Resource
    private UserCollectionMapper mapper;


    //添加用户收藏夹到数据库中
    @Override
    public Result<Void> add(VideoOperateDTO videoOperateDTO) {
        //设置添加时间为当前时间
        // 创建当前时间对象now > LocalDateTime.now()
        LocalDateTime now = LocalDateTime.now();
        videoOperateDTO.setCreateTime(now);

        Result<Void> result = new Result<>();
        //1.先判断该用户  收藏夹中是否存在该视频（通过视频id和用户id查询）   存在就不添加
        Long uid =  videoOperateDTO.getUid();
        int aid =  videoOperateDTO.getAid();
        log.info("用户ID ： " + uid);
        log.info("视频ID ： " + aid);
        UserCollectionSimpleVO userCollectionSimpleVO = mapper.selectByUserId(uid,aid);
        if (userCollectionSimpleVO != null) {
            log.info("重复添加shoucang " + userCollectionSimpleVO);
            throw new RepeatException("重复添加");
        } else {
            //2.收藏夹中不存在此视频  调用mapper中的方法添加进入
            int raw = mapper.add(videoOperateDTO);
            if (raw == 1) {
                log.info("添加成功");
                result.setSuccess();
            } else {
                log.info("添加失败");
                throw new SystemException("添加失败");
            }
        }
        return result;
    }


    /*
        通过用户传递过来的分类信息查询他的收藏夹中属于这一分类的所有视频信息
    */
    @Override
    public Result<List<UserCollectionSimpleVO>> selectByCollection(Long userId, String category) {

        log.info(" " +userId+ " " + category);

        Result<List<UserCollectionSimpleVO>> result = new Result<>();

        //调用mapper查询数据
        List<UserCollectionSimpleVO> list = mapper.selectByCollection(userId, category);

        log.info("查询出来的信息： " + list);

        result.setSuccess("查询成功", list);

        return result;
    }


}
