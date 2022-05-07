package com.am.libilibi.mapper;

import com.am.libilibi.entity.LBProxy;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/5 16:11
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Mapper
@Repository
public interface LBProxyMapper {
    public List<LBProxy> allProxy();
}
