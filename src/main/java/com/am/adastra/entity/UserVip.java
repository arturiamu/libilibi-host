package com.am.adastra.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @PackagePathcom.am.adastra.entity
 * @Classname UserVip
 * @Description TODO
 * @Version 1.0.0
 * @Date 2022/6/15 17:05
 * @Created by Volcan
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVip {
    private long id;
    private long startVipTime;
    private long endVipTime;
}
