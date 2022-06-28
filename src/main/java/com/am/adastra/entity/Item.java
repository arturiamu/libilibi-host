package com.am.adastra.entity;

import com.tencentcloudapi.apigateway.v20180808.models.TargetServicesReq;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/30 14:18
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @ApiModelProperty(value = "id",name = "id",example = "1")
    private Integer id;
    @ApiModelProperty(value = "分类名字",name = "name",example = "舞蹈")
    private String name;
    @ApiModelProperty(value = "分类uri",name = "uri",example = "dance")
    private String uri;
    @ApiModelProperty(value = "父分类id",name = "pid",example = "11")
    private Integer pid;
}
