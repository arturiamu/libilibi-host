package com.am.adastra.service.impl;

import com.am.adastra.entity.dto.VideoOperateDTO;
import com.am.adastra.entity.vo.UserCategorySimpleVO;
import com.am.adastra.entity.vo.UserCollectionSimpleVO;
import com.am.adastra.entity.vo.UserVO;
import com.am.adastra.ex.RepeatException;
import com.am.adastra.ex.SystemException;
import com.am.adastra.mapper.UserCategoryMapper;
import com.am.adastra.mapper.UserCollectionMapper;
import com.am.adastra.mapper.UserMapper;
import com.am.adastra.repository.UserCollectionRedisRepository;
import com.am.adastra.service.UserCollectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class UserCollectionServiceImpl implements UserCollectionService {
    @Autowired
    private UserMapper userMapper;

    @Resource
    private UserCollectionMapper userCollectionMapper;

    @Resource
    private UserCategoryMapper userCategoryMapper;

    @Autowired
    UserCollectionRedisRepository userCollectionRedisRepository;//收藏夹的缓存

    //添加用户收藏夹到数据库中
    @Override
    public void add(VideoOperateDTO videoOperateDTO) {
        //设置添加时间为当前时间
        // 创建当前时间对象now > LocalDateTime.now()
        LocalDateTime now = LocalDateTime.now();
        videoOperateDTO.setCreateTime(now);

        //1.先判断该用户  收藏夹中是否存在该视频（通过视频id和用户id查询）   存在就不添加
        Long uid = videoOperateDTO.getUid();
        int aid = videoOperateDTO.getAid();
        log.info("用户ID ： " + uid);
        log.info("视频ID ： " + aid);
        UserCollectionSimpleVO userCollectionSimpleVO = userCollectionMapper.selectByUserId(uid, aid);
        if (userCollectionSimpleVO != null) {
            log.info("重复添加shoucang " + userCollectionSimpleVO);
            throw new RepeatException("重复添加");
        } else {
            //2.收藏夹中不存在此视频  调用mapper中的方法添加进入
            int raw = userCollectionMapper.add(videoOperateDTO);
            if (raw == 1) {
                log.info("添加成功");
            } else {
                log.info("添加失败");
                throw new SystemException("添加失败");
            }
        }
    }


    /*
        通过用户传递过来的分类信息查询他的收藏夹中属于这一分类的所有视频信息
    */
    @Override
    public List<UserCollectionSimpleVO> selectByCollection(Long userId, String category) {
        log.info(" " + userId + " " + category);

        //调用mapper查询数据
        List<UserCollectionSimpleVO> list = userCollectionMapper.selectByCollection(userId, category);

        log.info("查询出来的信息： " + list);

        return list;
    }

    /**
     * 获取该用户的收藏夹中所有的收藏视频
     *
     * @param userId
     * @return
     */
    @Override
    public Map<String, List<UserCollectionSimpleVO>> selectCategory(Long userId) {
        log.info("当前用户id为 " + userId);
        Map<String, List<UserCollectionSimpleVO>> map = new HashMap<>();

        //1.查询出当前用户的所有的收藏夹名称
        List<UserCategorySimpleVO> userCategorySimpleVOS = userCategoryMapper.selectById(userId);
        //2.通过当前用户的id和收藏夹名称，得到所有的收藏夹信息
        List<List<UserCollectionSimpleVO>> list = new ArrayList<>();
        for (int i = 0; i < userCategorySimpleVOS.size(); i++) {
            list.add(userCollectionMapper.selectByCollection(userId, userCategorySimpleVOS.get(i).getCategoryName()));
        }

        //3.将得到的收藏夹信息添加到哈希表中
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).size() == 0) continue;
            String CategoryName = list.get(i).get(0).getCategoryName();
            map.put(CategoryName, list.get(i));
            log.info("收藏夹信息 + " + list.get(i).toString());
        }

        return map;
    }

    /**
     * 通过用户uid得到用户所有的收藏夹记录
     *
     * @param uid
     * @return
     */
    @Override
    public List<UserCollectionSimpleVO> selectById(long uid) {
        //1.先查找缓存中是否存在该数据，存在就直接返回缓存中的数据
        Boolean exists = userCollectionRedisRepository.exists(uid);
        if (exists) {
            //缓存中存在此数据
            log.info("缓存中存在此数据");
            //从缓存中获取该数据，
            List<UserCollectionSimpleVO> collectionByUid = userCollectionRedisRepository.getCollectionByUid(uid);
            return collectionByUid;
        }
        //2.缓存中不存在此数据，，那么就从mapper中调用方法获取数据，获取到的数据先添加到缓存中，并且返回
        log.info("缓存中不存在此数据，将从数据库中查询");
        List<UserCollectionSimpleVO> collectionSimpleVOS = userCollectionMapper.selectById(uid);
        userCollectionRedisRepository.save(collectionSimpleVOS);

        return collectionSimpleVOS;
    }

    /**
     * 预热类别数据的缓存
     * 将所有用户的收藏记录预加载到缓存中
     */
    @Override
    public void preloadCache() {
        //1.删除缓存中所有的用户的收藏夹记录
        log.info("删除缓存中所有的用户的收藏夹记录");
        userCollectionRedisRepository.deleteAllCollection();
        //2.从数据库中查询出用户的收藏夹记录，添加到缓存中
        //2.1查询出所有用户
        List<UserVO> userList = userMapper.list();
        //2.2通过所有用户的uid将他们的收藏记录写入到缓存中
        log.info("将所有用户的收藏记录写入到缓存中");
        for (UserVO user : userList) {
            List<UserCollectionSimpleVO> list = userCollectionMapper.selectById(user.getId());
            userCollectionRedisRepository.save(list);
        }
        log.info("用户收藏记录写入缓存完成");
    }


}
