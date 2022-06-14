package com.am.adastra.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/6/9 17:15
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoOperateDTO {
    Integer id;
    Integer uid;
    @NotNull(message = "aid不能为空")
    Integer aid;
    @NotNull(message = "pid不能为空")
    Integer pid;
    String categoryName;
    LocalDateTime createTime;
}