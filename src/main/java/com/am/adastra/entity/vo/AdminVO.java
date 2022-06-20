package com.am.adastra.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;


@Data
public class AdminVO {

    private Long id;
    private String username;
    private String password;
    private String nickname;//昵称
    private String avatar;//头像
}
