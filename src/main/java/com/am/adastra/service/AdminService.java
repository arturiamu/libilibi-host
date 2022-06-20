package com.am.adastra.service;

import com.am.adastra.entity.Admin;
import com.am.adastra.entity.User;
import com.am.adastra.entity.UserDBO;
import com.am.adastra.entity.vo.AdminVO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public interface AdminService {

    AdminVO login(Admin admin);

    Admin isLogin(HttpSession session);

    List<User> selectUser(int cur, int pageSize, String username);

    int updataUser(UserDBO userDBO);

    Integer selectTotal();

    void changeState(Long uid);
}
