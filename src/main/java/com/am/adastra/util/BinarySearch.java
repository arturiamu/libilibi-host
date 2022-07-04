package com.am.adastra.util;

import com.am.adastra.app.VideoPool;
import com.am.adastra.entity.Video;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 通过视频aid，二分查找视频的详细信息
 */
@Component
public class BinarySearch {

    public Video GetVideo(int pid,Long aid){
        List<Video> videos = VideoPool.VIDEO_POOL.get(pid);
        int L = 0;
        int R = videos.size();
        int ans = 0;
        while (L <= R){
            int mid =(L + R)/2;
            Long minAid = videos.get(Math.toIntExact(mid)).getAid();
            if (aid >= minAid){
                L = mid + 1;
                ans = Math.toIntExact(mid);
                if (aid.equals(minAid)) break;
            }else {
                R = mid -1 ;
            }
        }

        return videos.get(ans);
    }

}
