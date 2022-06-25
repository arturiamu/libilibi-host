package com.am.adastra.mapper;

import com.am.adastra.entity.dto.MessageDTO;
import com.am.adastra.entity.vo.MessageVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/6/24 15:57
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Mapper
public interface UserMessageMapper {
    List<MessageDTO> getAll(Long uid);

    int sendMessage(MessageDTO messageDTO);

    int fakeDel(Long id);

    int fakeDelAll(Long aid);

    int fakeRead(Long id);

    int fakeReadAll(Long aid);

    /**
     * 获取所有管理员的信息
     * @return
     */
    List<MessageVO> getAllMessage(Integer isAdmin);
}
