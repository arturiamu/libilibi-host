package com.am.adastra.service.impl;

import com.am.adastra.entity.dto.MessageDTO;
import com.am.adastra.entity.vo.MessageVO;
import com.am.adastra.mapper.UserMessageMapper;
import com.am.adastra.service.UserMessageService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/6/24 16:06
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Component
public class UserMessageServiceImpl implements UserMessageService {
    @Resource
    UserMessageMapper userMessageMapper;

    @Override
    public List<MessageDTO> getAll(Long uid) {
        return userMessageMapper.getAll(uid);
    }

    @Override
    public int sendMessage(MessageDTO messageDTO) {
        return userMessageMapper.sendMessage(messageDTO);
    }

    @Override
    public int fakeDel(Long id) {
        return userMessageMapper.fakeDel(id);
    }

    @Override
    public int fakeDelAll(Long aid) {
        return userMessageMapper.fakeDelAll(aid);
    }

    @Override
    public int fakeRead(Long id) {
        return userMessageMapper.fakeRead(id);
    }

    @Override
    public int fakeReadAll(Long aid) {
        return userMessageMapper.fakeReadAll(aid);
    }

    @Override
    public List<MessageVO> getAllMessage(Integer isAdmin) {
        isAdmin = 1;
        return userMessageMapper.getAllMessage(isAdmin);
    }
}
