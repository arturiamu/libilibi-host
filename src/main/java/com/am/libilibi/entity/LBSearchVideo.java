package com.am.libilibi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/18 9:55
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LBSearchVideo {
    String aid;
    String tid;
    String pic;
    String tittle;
    String play;
    String review;

    public LBDisplayVideo toDisplayVideo() {
        return new LBDisplayVideo(this.aid, this.tid, this.pic, this.tittle, this.play, this.review);
    }

//    {
//        "type": "video",
//            "id": 299173094,
//            "author": "艾兰火影手游转录",
//            "mid": 352111425,
//            "typeid": "172",
//            "typename": "手机游戏",
//            "arcurl": "http://www.bilibili.com/video/av299173094",
//            "aid": 299173094,
//            "bvid": "BV18F411L7yJ",
//            "title": "<em class=\"keyword\">1</em>",
//            "description": "-",
//            "arcrank": "0",
//            "pic": "//i0.hdslb.com/bfs/archive/e12f9a2a98fb4a3aafe960b94b44d19f6714e149.jpg",
//            "play": 2268,
//            "video_review": 0,
//            "favorites": 33,
//            "tag": "手机游戏,火影忍者手游,打卡挑战,拖更云,必剪创作,万物皆可游戏",
//            "review": 76,
//            "pubdate": 1652803820,
//            "senddate": 1652831396,
//            "duration": "0:32",
//            "badgepay": false,
//            "hit_columns": [
//        "title"
//                        ],
//        "view_type": "",
//            "is_pay": 0,
//            "is_union_video": 0,
//            "rec_tags": null,
//            "new_rec_tags": [],
//        "rank_score": 101420585,
//            "like": 179,
//            "upic": "http://i0.hdslb.com/bfs/face/bb4f9c68a84bd8d35f65691fe31ba5ed6f5edad6.jpg",
//            "corner": "",
//            "cover": "",
//            "desc": "",
//            "url": "",
//            "rec_reason": ""
//    }
}
