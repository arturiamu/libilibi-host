package com.am.adastra.service;

import com.am.adastra.entity.Admin;
import com.am.adastra.util.Result;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {

    Admin login(Admin admin);
}
