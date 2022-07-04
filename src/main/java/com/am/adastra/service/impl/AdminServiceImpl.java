package com.am.adastra.service.impl;

import cn.hutool.core.date.DateUtil;
import com.am.adastra.controller.Admin.AdminController;
import com.am.adastra.entity.Admin;
import com.am.adastra.entity.Item;
import com.am.adastra.entity.UserDBO;
import com.am.adastra.entity.dto.AdminDTO;
import com.am.adastra.entity.vo.*;
import com.am.adastra.ex.LoginException;
import com.am.adastra.ex.SystemException;
import com.am.adastra.ex.UserNotLoginException;
import com.am.adastra.mapper.AdminMapper;
import com.am.adastra.mapper.ItemMapper;
import com.am.adastra.mapper.UserHistoryMapper;
import com.am.adastra.mapper.UserMapper;
import com.am.adastra.service.AdminService;
import com.am.adastra.service.UserHistoryService;
import com.am.adastra.service.UserService;
import com.am.adastra.util.GetDatePoor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@Component
@Slf4j
public class AdminServiceImpl implements AdminService {
    @Autowired(required = false)
    private AdminMapper adminMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserHistoryMapper userHistoryMapper;

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
        try {//当数据库查出多条数据的时候，就捕获异常抛出
            adminUser = adminMapper.getByUsername(admin.getUsername());
        } catch (Exception e) {
            throw new SystemException("用户名重复，请联系管理员");
        }

        //2.管理员存在就判断密码是否正确
        if (adminUser == null) {
            //说明用户名不正确
            throw new LoginException("用户名不存在");
        } else {
            if (admin.getPassword().equals(adminUser.getPassword())) {
                return adminUser;
            } else {
                throw new LoginException("密码错误");
            }
        }
    }

    /*
     * 判断用户是否登录
     * */
    @Override
    public Admin isLogin(HttpSession session) {
        Admin sessionUser = null;
        try {
            sessionUser = (Admin) session.getAttribute(AdminController.ADMIN_INFO_SESSION);
        } catch (Exception e) {
            throw new UserNotLoginException("用户未登录");
        }
        return sessionUser;
    }


    /**
     * 分页查询用户信息
     *
     * @param cur      第几页
     * @param pageSize 每页有多少条信息
     * @param username
     * @return
     */
    @Override
    public List<UserVO> selectUser(int cur, int pageSize, String username) {
        log.info("分页查询用户信息  selectUser() ---> ");
        log.info("第几页" + cur);
        log.info("每页有多少" + pageSize);

        cur = cur <= 1 ? 0 : cur - 1;
        //调用userMapper层查询数据
        List<UserVO> userList = userMapper.selectPage(cur * pageSize, pageSize, username);

        log.info("分页查询到的数据 -->" + userList);
        return userList;
    }

    @Override
    public int updateUser(UserDBO userDBO) {
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
     *
     * @param uid
     */
    @Override
    public void changeState(Long uid) {
        int ans = userMapper.changeState(uid);
        if (ans != 1) {
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
        for (UserVO user : userList) {
            Date createTime = user.getCreateTime();
            //如果不是当前年就跳出本次循环
            if (DateUtil.year(createTime) != year) continue;
            //得到月份
            int month = DateUtil.month(createTime);

            if (month >= 0 && month < 13) {
                q[month]++;
            }
        }
        //纵坐标
        Map<String, Object> map = new HashMap<>();
        map.put("Y", Arrays.asList(q));
        //横坐标
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            list.add((1 + i) + "月");
        }
        map.put("X", list);
        //标题
        map.put("title", year + "年");

        //用户总人数
        map.put("totalUsers", userList.size());

        return map;
    }


    /**
     * 获取某个用户21个视频大分类的观看次数，返回给前端
     *
     * @return
     */
    @Override
    public Map<String, Object> videoHeatUser(Long uid) {
        Map<String, Object> resultMap = new HashMap<>();
        List<String> itemName = new ArrayList<>();

        //2.找到所有的视频大分类
        List<Item> itemMapper = this.itemMapper.getAll();
        //将视频大分类id（pid）与1，2，3...对应起来
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < itemMapper.size(); i++) {
            map.put(itemMapper.get(i).getPid(), i);
            itemName.add(itemMapper.get(i).getName());
        }
        int[] items = new int[itemMapper.size()];
        Long dailyActivity = 0L;//日活跃量  今日所有用户观看的视频数量

        //3.查询这些用户观看的视频数据，将对应的观看数据添加到视频大分类pid上

        Long id = uid;
        //得到当前用户所有的历史记录
        List<UserHistorySimpleVO> userHistoryVideo = userHistoryMapper.getLimit(id, 1000);
//            List<Video> userHistoryServiceAll = userHistoryService.getAll(id);
        for (int j = 0; j < userHistoryVideo.size(); j++) {
            dailyActivity++;
            //将对应的观看数据添加到视频大分类pid上
            items[map.get(userHistoryVideo.get(j).getVideo().getPid())]++;
        }


        //4.获取今日热点视频大分类的名字
        String hotVideo = ""; //今日热点视频
        int maxHotVideo = -1;
        for (int i = 0; i < items.length; i++) {
            if (items[i] > maxHotVideo) {
                maxHotVideo = items[i];
                hotVideo = itemName.get(i);
            }
        }


        resultMap.put("itemName", itemName);
        resultMap.put("data", items);
        resultMap.put("dailyActivity", dailyActivity);
        resultMap.put("hotVideo", hotVideo);


        return resultMap;
    }

    /**
     * 获取21个视频大分类的观看次数，返回给前端
     *
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
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < itemMapper.size(); i++) {
            map.put(itemMapper.get(i).getPid(), i);
            itemName.add(itemMapper.get(i).getName());
        }
        int[] items = new int[itemMapper.size()];
        Long dailyActivity = 0L;//日活跃量  今日所有用户观看的视频数量

        log.info("所有用户" + allUser.toString());

        //3.查询这些用户观看的视频数据，将对应的观看数据添加到视频大分类pid上
        for (int i = 0; i < allUser.size(); i++) {
            Long id = allUser.get(i).getId();
            //得到当前用户所有的历史记录
            List<UserHistorySimpleVO> userHistoryVideo = userHistoryMapper.getLimitAdmin(id, 1000);
//            List<Video> userHistoryServiceAll = userHistoryService.getAll(id);
            for (int j = 0; j < userHistoryVideo.size(); j++) {
                dailyActivity++;
                //将对应的观看数据添加到视频大分类pid上
                items[map.get(userHistoryVideo.get(j).getVideo().getPid())]++;

                log.info("当前用户ID ： " + id);
                log.info(userHistoryVideo.get(j).getVideo().getTname() + "的数量为： " + items[map.get(userHistoryVideo.get(j).getVideo().getPid())]);
            }
        }

        //4.获取今日热点视频大分类的名字
        String hotVideo = ""; //今日热点视频
        int maxHotVideo = -1;
        for (int i = 0; i < items.length; i++) {
            if (items[i] > maxHotVideo) {
                maxHotVideo = items[i];
                hotVideo = itemName.get(i);
            }
        }


        resultMap.put("itemName", itemName);
        resultMap.put("data", items);
        resultMap.put("dailyActivity", dailyActivity);
        resultMap.put("hotVideo", hotVideo);


        return resultMap;
    }


    /**
     * 修改管理员的个人信息
     *
     * @param adminDTO
     */
    @Override
    public void updateAdmin(AdminDTO adminDTO) {
        adminMapper.updateAdmin(adminDTO);
    }

    /**
     * 获取用户各个时间段的观看数据
     * 从00：00 到 23：59
     * 每一个小时作为一个分隔
     * 数据分为3组，今天  一天前  七天前
     *
     * @return
     */
    @Override
    public Map<String, Object> viewingPeriod() {
        Map<String, Object> map = new HashMap<>();
        long[] today = new long[24];//今天
        long[] dayBefore = new long[24];//一天前
        long[] sevenDaysAgo = new long[24];//七天前
        //1.获取所有用户的用户id
        List<UserVO> userVOList = userMapper.list();
        //2.通过用户id获取用户的历史记录
        for (UserVO user : userVOList) {
            List<UserHistorySimpleVO> userHistoryList = userHistoryMapper.getLimit(user.getId(), 5000);
            //3.遍历所有的历史记录，记录每个时间段的观看人数
            for (UserHistorySimpleVO videoHistory : userHistoryList) {
                LocalDateTime createTime = videoHistory.getCreateTime();
                if (createTime == null) continue;
                //获取是第几个小时
                int historyHour = createTime.getHour();
                //得到当前时间
                LocalDateTime nowTime = LocalDateTime.now();
                //获取时间差
                Duration duration = Duration.between(createTime, nowTime);
                long hours = duration.toHours();//相差的小时数
//
//                log.info("历史时间为："+createTime);
//                log.info("历史时间的小时为" + historyHour);
//                log.info("当前时间为：" + nowTime);
//                log.info("相差的小时数：" + hours);
//
//                log.info("----------------------------");
//
                if (hours < 24) {//说明是今天
                    today[historyHour]++;
                }
                if (hours > 24) {//说明是一天前
                    dayBefore[historyHour]++;
                }
                if (hours > 24 * 7) {//说明是七天前
                    sevenDaysAgo[historyHour]++;
                }
            }
        }

        map.put("today", today);
        map.put("dayBefore", dayBefore);
        map.put("sevenDaysAgo", sevenDaysAgo);
        map.put("nowTime", LocalDateTime.now());//当前时间
        String[] XTitle = new String[24];//X轴的标题
        for (int i = 0; i < XTitle.length; i++) {
            if (i < 10) {
                XTitle[i] = "0" + i + ":00";
            } else {
                XTitle[i] = i + ":00";
            }

        }
        map.put("XTitle", XTitle);

        return map;
    }


    /**
     * 获取各个时间段的访问人数
     *
     * @return
     */
    @Override
    public Map<String, Object> numberPersons() {
        Map<String, Object> map = new HashMap<>();
        long[] today = new long[24];//今天
        long[] dayBefore = new long[24];//一天前
        long[] sevenDaysAgo = new long[24];//七天前

        //1.获取所有用户的登录信息
        List<UserLoginLogVO> userLoginLogList = userMapper.loginList();
        for (UserLoginLogVO loginLog : userLoginLogList) {
            //2.遍历所有的登录日志，记录每个时间段的观看人数
            Date logTime = loginLog.getTime();
            if (logTime == null) continue;

            Date dateNow = new Date();//得到当前时间
            //计算相差多少个小时
            long datePoor = GetDatePoor.getDatePoor(dateNow, logTime);
            //得到历史时间是当天的第几个小时
            Calendar calendarLogTime = Calendar.getInstance();
            calendarLogTime.setTime(new Date());
            calendarLogTime.setTime(logTime);
            int hourLog = calendarLogTime.get(Calendar.HOUR_OF_DAY);

            if (datePoor < 24) {//说明是今天
                today[hourLog]++;
            }
            if (datePoor > 24) {//说明是一天前
                dayBefore[hourLog]++;
            }
            if (datePoor > 24 * 7) {//说明是七天前
                sevenDaysAgo[hourLog]++;
            }
        }

        map.put("today", today);
        map.put("dayBefore", dayBefore);
        map.put("sevenDaysAgo", sevenDaysAgo);
        map.put("nowTime", LocalDateTime.now());//当前时间
        String[] XTitle = new String[24];//X轴的标题
        for (int i = 0; i < XTitle.length; i++) {
            if (i < 10) {
                XTitle[i] = "0" + i + ":00";
            } else {
                XTitle[i] = i + ":00";
            }

        }
        map.put("XTitle", XTitle);

        return map;
    }

    /**
     * 获取所有的用户反馈
     * @return
     */
    @Override
    public List<AdviseVO> getAllAdvise() {
        //1.得到所有的用户反馈
        List<AdviseVO> adviseVOList = adminMapper.getAllAdvise();
        //2.遍历所有的用户反馈的用户id，得到他们的用户名
        Map<Integer,String> map = new HashMap<>();
        for (AdviseVO adviseVO :adviseVOList) {
            if (map.containsKey(adviseVO.getUid())){
                adviseVO.setUsername(map.get(adviseVO.getUid()));
            }else {
                UserDBO dboById = userMapper.getDBOById(adviseVO.getUid());
                adviseVO.setUsername(dboById.getUsername());
            }
        }
        return adviseVOList;
    }


}
