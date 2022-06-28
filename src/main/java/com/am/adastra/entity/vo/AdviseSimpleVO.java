package com.am.adastra.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: lyy
 * @Version: 1.0
 * @JDK-version: 1.8
 * @Date: 2022/6/9 10:39
 * @Description:
 *
 */
@Data
public class AdviseSimpleVO implements Serializable {
    @ApiModelProperty(value = "用户建议id",name = "id",example = "1")
    private Integer id;
    @ApiModelProperty(value = "用户id",name = "userId",example = "1")
    private Integer userId;
    @ApiModelProperty(value = "用户建议内容",name = "advise",example = "颜色太突兀")
    private String advise;
    @ApiModelProperty(value = "时间",name = "adviseTime",example = "2022-6-28 13:28:20")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss",timezone = "GMT+8")
    private Date adviseTime;
}
