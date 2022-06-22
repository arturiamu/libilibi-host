package com.am.adastra.service.impl;

import cn.hutool.core.date.DateUtil;
import com.am.adastra.controller.Admin.AdminController;
import com.am.adastra.entity.Admin;
import com.am.adastra.entity.Item;
import com.am.adastra.entity.UserDBO;
import com.am.adastra.entity.Video;
import com.am.adastra.entity.vo.AdminVO;
import com.am.adastra.entity.vo.UserVO;
import com.am.adastra.ex.LoginException;
import com.am.adastra.ex.SystemException;
import com.am.adastra.ex.UserNotLoginException;
import com.am.adastra.mapper.AdminMapper;
import com.am.adastra.mapper.ItemMapper;
import com.am.adastra.mapper.UserMapper;
import com.am.adastra.service.AdminService;
import com.am.adastra.service.UserHistoryService;
import com.am.adastra.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.*;

@Component
@Slf4j
public class AdminServiceImpl implements AdminService {
    @Autowired(required = false)
    private AdminMapper adminMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    UserHistoryService userHistoryService;

    /*
     * 处理管理员登录
     * */
    @Override
    public AdminVO login(Admin admin) {
        AdminVO adminUser = null;
        try{//当数据库查出多条数据的时候，就捕获异常抛出
            adminUser = adminMapper.getByUsername(admin.getUsername());
        }catch (Exception e){
            throw new SystemException("用户名重复，请联系管理员");
        }

        //2.管理员存在就判断密码是否正确
        if (adminUser == null) {
            //说明用户名不正确
            throw new LoginException("用户名不存在");
        } else {
            if (admin.getPassword().equals(adminUser.getPassword())) {
                return adminUser;
            }else {
                throw new LoginException("密码错误");
            }
        }
    }

    /*
    * 判断用户是否登录
    * */
    @Override
    public Admin isLogin(HttpSession session) {
        Admin sessionUser = (Admin) session.getAttribute(AdminController.USER_INFO_SESSION);
        if (sessionUser == null) {
            throw new UserNotLoginException("用户未登录");
        }
        return sessionUser;
    }



    /**
     * 分页查询用户信息
     * @param cur  第几页
     * @param pageSize  每页有多少条信息
     * @param username
     * @return
     */
    @Override
    public List<UserVO> selectUser(int cur, int pageSize, String username) {
        log.info("分页查询用户信息  selectUser() ---> ");
        log.info("第几页"+cur);
        log.info("每页有多少"+pageSize);

        cur = cur <=1 ? 0 : cur-1;
        //调用userMapper层查询数据
        List<UserVO> userList = userMapper.selectPage(cur*pageSize,pageSize,username);

        log.info("分页查询到的数据 -->" + userList);
        return userList;
    }

    @Override
    public int updataUser(UserDBO userDBO) {
        String password = userDBO.getPassword();
        userDBO.setPassword(DigestUtils.md5Hex(password));
        int i = adminMapper.updateUser(userDBO);
        return i;
    }

    @Override
    public Integer selectTotal() {
        Integer total = adminMapper.selectTotal();
        return total;
    }

    /**
     * 改变用户状态
     * @param uid
     */
    @Override
    public void changeState(Long uid) {
        int ans = userMapper.changeState(uid);
        if (ans != 1){
            //说明修改失败
            throw new SystemException("修改失败,请稍后再试");
        }
    }

    @Override
    public Map<String, Object> members() {
        List<UserVO> userList = userService.list();
        int[] q = new int[12];
        //年（获取当前时间，取到当前的年份）
        Date date = new Date();
        int year = DateUtil.year(date);
        for (UserVO user : userList){
            Date createTime = user.getCreateTime();
            //如果不是当前年就跳出本次循环
            if (DateUtil.year(createTime) != year) continue;
            //得到月份
            int month = DateUtil.month(createTime);

            if (month>=0 && month<13){
                q[month] ++ ;
            }
        }
        //纵坐标
        Map<String , Object> map = new HashMap<>();
        map.put("Y", Arrays.asList(q));
        //横坐标
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            list.add((1+i)+"月");
        }
        map.put("X",list);
        //标题
        map.put("title",year+"年");

        return map;
    }

    /**
     * 获取21个视频大分类的观看次数，返回给前端
     * @return
     */
    @Override
    public Map<String, Object> videoHeat() {
        Map<String, Object> resultMap = new HashMap<>();
        List<String> itemName = new ArrayList<>();
        //1.找到所有用户
        List<UserVO> allUser = userMapper.list();
        //2.找到所有的视频大分类
        List<Item> itemMapper = this.itemMapper.getAll();
        //将视频大分类id（pid）与1，2，3...对应起来
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < itemMapper.size(); i++) {
            map.put(itemMapper.get(i).getPid(),i);
            itemName.add(itemMapper.get(i).getName());
        }
        int[] items = new int[itemMapper.size()];
        //3.查询这些用户观看的视频数据，将对应的观看数据添加到视频大分类pid上
        for (int i = 0; i < allUser.size(); i++) {
            Long id = allUser.get(i).getId();
            //得到当前用户所有的历史记录
            List<Video> userHistoryServiceAll = userHistoryService.getAll(id);
            for (int j = 0; j < userHistoryServiceAll.size(); j++) {
                //将对应的观看数据添加到视频大分类pid上
                items[map.get(userHistoryServiceAll.get(j).getPid())] ++;
            }
        }

        resultMap.put("itemName",itemName);
        resultMap.put("data",items);

        return resultMap;
    }


}
