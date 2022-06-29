package com.am.adastra.entity.vo;

import lombok.Data;

import java.util.Date;

/**
 * @Author: lyy
 * @Version: 1.0
 * @JDK-version: 1.8
 * @Date: 2022/6/25 13:21
 * @Description:
 */
@Data
public class MessageVO {
    private Integer id;
    private Long sendUserId;
    private Long targetUserId;
    private String text;
    private Date time;
    private Integer read;
    private Integer del;
    private String sendUserName;
    private Integer isAdmin;
}
