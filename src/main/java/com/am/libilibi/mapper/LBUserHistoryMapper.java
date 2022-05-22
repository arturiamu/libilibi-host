package com.am.libilibi.mapper;

import com.am.libilibi.entity.LBUser;
import com.am.libilibi.entity.LBUserHistory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

import java.util.List;


//这里的名称和数据库中的名称不一样，因此我们需要利用Result来声明不一样的变量
/*    @Result(property = "userId", column = "user_id")
    @Result(property = "historyVideoId", column = "history_video_id")*/

@Mapper
public interface LBUserHistoryMapper {

    //向用户历史表中添加数据
    @Insert("insert into history values(null,#{userId},#{historyVideoId},#{time})")
    int add(LBUserHistory lbUser);


    //查询userId用户所有的浏览信息，通过时间排序
//    asc：升序（默认）   desc:降序
//    @Select("select * from history  order by time")
    @Select("select * from history where user_id=#{userId} order by time desc")
//这里的名称和数据库中的名称不一样，因此我们需要利用Result来声明不一样的变量
    @Result(property = "userId", column = "user_id")
    @Result(property = "historyVideoId", column = "history_video_id")
    List<LBUserHistory> select(Integer userId);


}
