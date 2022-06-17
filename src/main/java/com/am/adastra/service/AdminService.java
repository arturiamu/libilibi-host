package com.am.adastra.service;

import com.am.adastra.entity.Admin;
import com.am.adastra.entity.User;
import com.am.adastra.entity.UserDBO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public interface AdminService {

    Admin login(Admin admin);

    Admin isLogin(HttpSession session);

    List<User> selectUser(int cur, int pageSize);

    int updataUser(UserDBO userDBO);
}
