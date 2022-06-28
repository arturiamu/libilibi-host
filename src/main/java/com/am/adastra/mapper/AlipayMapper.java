package com.am.adastra.mapper;

import com.am.adastra.entity.UserVip;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @PackagePathcom.am.adastra.mapper
 * @Classname VipService
 * @Description TODO
 * @Version 1.0.0
 * @Date 2022/6/26 21:51
 * @Created by Volcan
 */
@Mapper
public interface AlipayMapper {

    void updataVipInfo(UserVip userVip);

    boolean insertVipInfo(UserVip userVip);

    UserVip selectByOutTradeNo(String outTradeNo);
}
