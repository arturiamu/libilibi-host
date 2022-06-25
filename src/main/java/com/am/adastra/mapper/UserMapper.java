package com.am.adastra.mapper;

import com.am.adastra.entity.User;
import com.am.adastra.entity.UserDBO;
import com.am.adastra.entity.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/27 11:20
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Mapper
public interface UserMapper {

    int addDBO(UserDBO user);

    int updateDBO(UserDBO user);

    int updatePwd(String password,Long id);

    int passwordBack(String password,Long uid);

    UserDBO getDBOById(Long id);

    UserDBO getDBOByAccount(String account);

    UserDBO getDBOByUsername(String username);

    /**
     * 返回所有用户信息
     * @return
     */
    List<UserVO> list();

    /**
     * 分页查询用户
     * @param cur
     * @param pageSize
     * @param username
     * @return
     */
    List<UserVO> selectPage(int cur, int pageSize, String username);

    /**
     * 修改用户状态
     * @param uid
     * @return
     */
    int changeState(Long uid);
}
