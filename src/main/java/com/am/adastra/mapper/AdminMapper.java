package com.am.adastra.mapper;

import com.am.adastra.entity.Admin;
import com.am.adastra.entity.UserDBO;
import com.am.adastra.entity.dto.AdminDTO;
import com.am.adastra.entity.vo.AdminVO;
import com.am.adastra.entity.vo.AdviseVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/*
* 管理员的mapper
* */
@Mapper
public interface AdminMapper {

    /*
    * 通过用户名查询用户信息
    * */
    AdminVO getByUsername(String username);

    int updateUser(UserDBO userDBO);

    /**
     * 查询用户数量
     * @return
     */
    Integer selectTotal();

    void updateAdmin(AdminDTO adminDTO);

    /**
     * 查询所有的用户反馈信息
     * @return
     */
    List<AdviseVO> getAllAdvise();
}
