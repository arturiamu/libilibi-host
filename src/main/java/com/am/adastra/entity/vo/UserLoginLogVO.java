package com.am.adastra.entity.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Author: lyy
 * @Version: 1.0
 * @JDK-version: 1.8
 * @Date: 2022/6/24 21:40
 * @Description:
 */
@Data
public class UserLoginLogVO {
    private Long id;
    private Long uid;
    private Date time;
    private String ip;
}
