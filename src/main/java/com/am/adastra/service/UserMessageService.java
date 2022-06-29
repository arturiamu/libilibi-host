package com.am.adastra.service;

import com.am.adastra.entity.dto.MessageDTO;
import com.am.adastra.entity.vo.MessageVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/6/24 16:06
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Service
public interface UserMessageService {
    List<MessageDTO> getAll(Long uid);

    int sendMessage(MessageDTO messageDTO);

    int fakeDel(Long id);

    int fakeDelAll(Long aid);

    int fakeRead(Long id);

    int fakeReadAll(Long aid);

    List<MessageVO> getAllMessage(Integer isAdmin);

    void sendAllMessage(MessageDTO messageDTO);
}
