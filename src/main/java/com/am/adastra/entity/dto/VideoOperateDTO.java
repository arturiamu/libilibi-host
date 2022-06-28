package com.am.adastra.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

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
    @ApiModelProperty(value = "id", name = "id", example = "1")
    Integer id;
    @ApiModelProperty(value = "用户id", name = "uid", example = "1")
    Long uid;
    @NotNull(message = "aid不能为空")
    @ApiModelProperty(value = "视频id", name = "aid", example = "3581515")
    Integer aid;
    @NotNull(message = "pid不能为空")
    @ApiModelProperty(value = "视频分类id", name = "pid", example = "11")
    Integer pid;
    @ApiModelProperty(value = "收藏夹名字", name = "categoryName", example = "收藏夹1")
    String categoryName;
    @ApiModelProperty(value = "收藏时间", name = "createTime", example = "2022-6-28 13:37:17")
    LocalDateTime createTime;
}
