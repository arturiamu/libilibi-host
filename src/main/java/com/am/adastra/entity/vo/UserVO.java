package com.am.adastra.entity.vo;

import com.am.adastra.entity.Item;
import com.am.adastra.entity.param.ValidationRules;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Data
public class UserVO implements Serializable {
    private Long id;

    //用户名
    private String username;

    //用户手机号或者邮箱
    private String account;

    private Item[] items;

    //用户注册时间
    private Date createTime;

    //用户上次修改的时间
    private Date updateTime;

    //用户状态
    private String state;

    //备注
    private String remarks;

}
