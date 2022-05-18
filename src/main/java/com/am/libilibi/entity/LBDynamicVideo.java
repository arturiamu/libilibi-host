package com.am.libilibi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/18 10:00
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LBDynamicVideo {
    String aid;
    String tid;
    String pic;
    String tittle;
    Stat stat;

    public DisplayVideo toDisplayVideo() {
        return new DisplayVideo(this.aid, this.tid, this.pic, this.tittle, this.stat.view, this.stat.danmaku);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    class Stat {
        String view;
        String danmaku;
    }
//    {
//        "aid": 976055160,
//            "videos": 1,
//            "tid": 27,
//            "tname": "综合",
//            "copyright": 1,
//            "pic": "http://i1.hdslb.com/bfs/archive/cc76aeebed8d4aa2058569edc0c2dea56c6fe1c4.jpg",
//            "title": "【凹凸世界/赞德】这位天使麻烦穿好裤子",
//            "pubdate": 1634368876,
//            "ctime": 1634368876,
//            "desc": "BGM：The Other Side Of Paradise\n\n菜头师兄可真是太顶了！！！",
//            "state": 0,
//            "duration": 66,
//            "rights": {
//        "bp": 0,
//                "elec": 0,
//                "download": 0,
//                "movie": 0,
//                "pay": 0,
//                "hd5": 0,
//                "no_reprint": 1,
//                "autoplay": 1,
//                "ugc_pay": 0,
//                "is_cooperation": 0,
//                "ugc_pay_preview": 0,
//                "no_background": 0,
//                "arc_pay": 0,
//                "pay_free_watch": 0
//    },
//        "owner": {
//        "mid": 1043016599,
//                "name": "一只佩佩狗",
//                "face": "http://i2.hdslb.com/bfs/face/428a4baeb0af044e1922f32fb633cceaa6786c65.jpg"
//    },
//        "stat": {
//        "aid": 976055160,
//                "view": 238243,
//                "danmaku": 183,
//                "reply": 133,
//                "favorite": 6870,
//                "coin": 641,
//                "share": 307,
//                "now_rank": 0,
//                "his_rank": 0,
//                "like": 6873,
//                "dislike": 0
//    },
//        "dynamic": "",
//            "cid": 425986313,
//            "dimension": {
//        "width": 1440,
//                "height": 720,
//                "rotate": 0
//    },
//        "short_link": "https://b23.tv/BV1s44y1x7Yt",
//            "short_link_v2": "https://b23.tv/BV1s44y1x7Yt",
//            "up_from_v2": 9,
//            "first_frame": "http://i0.hdslb.com/bfs/storyff/n211016a2got5ap4x2m7q2laoxm3ls43_firsti.jpg",
//            "bvid": "BV1s44y1x7Yt",
//            "season_type": 0,
//            "is_ogv": false,
//            "ogv_info": null,
//            "rcmd_reason": ""
//    }
}
