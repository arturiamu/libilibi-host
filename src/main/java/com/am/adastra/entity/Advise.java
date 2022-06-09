package com.am.adastra.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: lyy
 * @Version: 1.0
 * @JDK-version: 1.8
 * @Date: 2022/6/9 10:20
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Advise {
    private Integer id;
    private Integer userId;
    private String advise;
    private Date adviseTime;
}
