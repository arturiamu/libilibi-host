package com.am.adastra.service.impl;

import com.am.adastra.entity.dto.MessageDTO;
import com.am.adastra.entity.vo.MessageVO;
import com.am.adastra.entity.vo.UserVO;
import com.am.adastra.mapper.UserMapper;
import com.am.adastra.mapper.UserMessageMapper;
import com.am.adastra.service.UserMessageService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @Resource
    private UserMapper userMapper;

    @Override
    public List<MessageDTO> getAll(Long uid) {
        return userMessageMapper.getAll(uid);
    }

    @Override
    public int sendMessage(MessageDTO messageDTO) {
        return userMessageMapper.sendMessage(messageDTO);
    }

    @Override
    public void sendAllMessage(MessageDTO messageDTO) {
        //1.得到所有的用户信息
        List<UserVO> list = userMapper.list();
        //2.发送信息给所有用户
        for (int i = 0; i < list.size(); i++) {
            messageDTO.setTargetUserId(list.get(i).getId());
            //发送消息
            userMessageMapper.sendMessage(messageDTO);
        }

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
        List<MessageVO> allMessage = userMessageMapper.getAllMessage(isAdmin);
        List<MessageVO> allMessageCopy = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < allMessage.size(); i++) {
            if (set.add(allMessage.get(i).getText())){
                allMessageCopy.add(allMessage.get(i));
            }
        }

        return allMessageCopy;
    }


}
