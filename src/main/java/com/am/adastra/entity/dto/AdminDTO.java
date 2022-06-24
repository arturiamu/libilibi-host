package com.am.adastra.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AdminDTO {

    private String username;//用户名
    private String nickname;//昵称
    private String avatar;//头像

}
