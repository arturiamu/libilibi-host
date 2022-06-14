package com.am.adastra.service.impl;

import com.am.adastra.mapper.AdviseMapper;
import com.am.adastra.entity.dto.AdviseDTO;
import com.am.adastra.service.AdviseService;
import com.am.adastra.util.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @Author: lyy
 * @Version: 1.0
 * @JDK-version: 1.8
 * @Date: 2022/6/9 10:33
 * @Description:
 */
@Slf4j
@Component
public class AdviseServiceImpl implements AdviseService {
    @Resource
    private AdviseMapper adviseMapper;

    @Override
    public int add(AdviseDTO advise) {
        advise.setAdviseTime(new Date());
        return adviseMapper.add(advise);
    }

    @Override
    public List<AdviseDTO> selectById(Long userId) {
        Result<List<AdviseDTO>> result = new Result<>();
        //调用mapper查询数据
        List<AdviseDTO> list = adviseMapper.selectById(userId);
        log.info(String.valueOf(list));
        result.setSuccess("查询成功", list);
        return list;
    }
}
