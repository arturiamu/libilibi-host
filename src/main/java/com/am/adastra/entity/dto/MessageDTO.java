package com.am.adastra.entity.dto;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "消息id",name = "id",example = "1")
    private Long id;
    @ApiModelProperty(value = "发送方用户名",name = "sendUserName",example = "arturiamu")
    private String sendUserName;
    @ApiModelProperty(value = "发送方id",name = "sendUserId",example = "1")
    private Long sendUserId;
    @NotNull
    @ApiModelProperty(value = "接收方id",name = "targetUserId",example = "1")
    private Long targetUserId;
    @NotBlank
    @ApiModelProperty(value = "消息内容",name = "text",example = "你好")
    private String text;
    @ApiModelProperty(value = "发送时间",name = "date",example = "2022-6-28 13:31:15")
    private Date date;
    @ApiModelProperty(value = "消息是否已读",name = "read",example = "1")
    private int read;
    @ApiModelProperty(value = "消息是否删除",name = "del",example = "1")
    private int del;
    @ApiModelProperty(value = "是否管理员发送",name = "isAdmin",example = "true")
    private Boolean isAdmin;
}
