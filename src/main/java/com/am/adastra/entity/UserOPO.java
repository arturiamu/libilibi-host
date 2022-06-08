package com.am.adastra.entity;

import com.am.adastra.entity.param.ValidationRules;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/6/6 20:43
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOPO {
    @NotNull(groups = ValidationRules.userOP.class, message = "用户id不能为空")
    private Integer id;
    @NotNull(groups = ValidationRules.userOP.class, message = "视频id不能为空")
    private Long aid;

    private String categoryName;
}
