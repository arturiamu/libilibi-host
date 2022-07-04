package com.am.adastra.entity.vo;

import lombok.Data;

import java.util.Date;

/**
 * 获取用户所有的反馈信息
 */
@Data
public class AdviseVO {
    private Long id;
    private Long uid;
    private String username;
    private String advise;
    private Date advise_time;
    private String state;
}
