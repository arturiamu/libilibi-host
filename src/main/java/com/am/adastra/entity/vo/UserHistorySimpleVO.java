package com.am.adastra.entity.vo;


import com.am.adastra.entity.Video;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class UserHistorySimpleVO implements Serializable {
    private int id;
    private Date createTime;//浏览时间时间
    private Video videoList;
}
