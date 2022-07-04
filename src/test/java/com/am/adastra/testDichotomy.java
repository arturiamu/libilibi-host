package com.am.adastra;

import com.am.adastra.app.VideoPool;
import com.am.adastra.entity.Video;

import java.util.List;

public class testDichotomy {
    public static void main(String[] args) {
        int[] a = {1,2,3,4,5,6,8,10,15,20,24,33,78,220};
        int dd = 220;
        int L = 0;
        int R = a.length;
        int ans = 0;
        while (L <= R){
            int mid =(L + R)/2;
            int midValue = a[mid];
            if (dd >= midValue){
                L = mid + 1;
                ans = Math.toIntExact(mid);
                if (a[ans] == dd) break;
            }else {
                R = mid -1 ;
            }
        }
        System.out.println(ans);
    }

    public Video GetVideo(int pid, int aid){
        List<Video> videos = VideoPool.VIDEO_POOL.get(pid);
        Long L = 0L;
        Long R = Long.valueOf(videos.size()-1);
        int ans = Math.toIntExact(0L);
        while (L <= R){
            Long mid =(L + R)/2;
            Long minAid = videos.get(Math.toIntExact(mid)).getAid();
            if (aid >= minAid){
                L = mid + 1;
                ans = Math.toIntExact(mid);
            }else {
                R = mid -1 ;
            }
        }

        return videos.get(ans);
    }
}
