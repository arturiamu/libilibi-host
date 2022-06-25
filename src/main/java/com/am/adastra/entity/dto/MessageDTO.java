package com.am.adastra.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/6/24 15:58
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageDTO implements Serializable {
    private Long id;
    private String sendUserName;
    private Long sendUserId;
    @NotNull
    private Long targetUserId;
    @NotBlank
    private String text;
    private Date date;
    private int read;
    private int del;
    private Boolean isAdmin;
}
