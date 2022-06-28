package com.am.adastra.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author: lyy
 * @Version: 1.0
 * @JDK-version: 1.8
 * @Date: 2022/6/9 10:39
 * @Description:
 */
@Data
public class AdviseDTO implements Serializable {
    @ApiModelProperty(value = "用户建议id",name = "id",example = "1")
    private Integer id;
    @ApiModelProperty(value = "用户id",name = "uid",example = "1")
    private Long uid;
    @ApiModelProperty(value = "用户建议内容",name = "advise",example = "颜色太突兀")
    @NotBlank(message = "建议内容不能为空")
    private String advise;
    @ApiModelProperty(value = "时间",name = "adviseTime",example = "2022-6-28 13:28:20")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private Date adviseTime;
}
