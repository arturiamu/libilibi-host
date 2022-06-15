package com.am.adastra.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private Integer id;
    private Long uid;
    @NotBlank(message = "建议内容不能为空")
    private String advise;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8")
    private Date adviseTime;
}
