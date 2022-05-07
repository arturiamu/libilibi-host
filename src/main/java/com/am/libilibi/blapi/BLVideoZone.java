package com.am.libilibi.blapi;

import lombok.Data;

/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/4 11:50
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
@Data
public class BLVideoZone {

    private static final String zoneLatest = "https://api.bilibili.com/x/web-interface/dynamic/region";

    public static String getZoneLatest(int rid, int ps, int pn) {
        return zoneLatest + "?rid=" + rid + "&ps=" + ps + "&pn=" + pn;
    }

    private static final int DOUGA = 1;  // 动画主分区
    private static final int DOUGA_MAD = 24;  // 具有一定制作程度的动画或静画的二次创作视频
    private static final int DOUGA_MMD = 25;  // 使用MMD(MikuMikuDance)和其他3D建模类软件制作的视频
    private static final int DOUGA_VOICE = 47;  // 追求创新并具有强烈特色的短片、手书(绘)及ACG相关配音
    private static final int DOUGA_GARAGE_KIT = 210;  // 手办模玩的测评、改造或其他衍生内容
    private static final int DOUGA_TOKUSATSU = 86;  // 特摄相关衍生视频
    private static final int DOUGA_OTHER = 27;  // 以动画及动画相关内容为素材，包括但不仅限于音频替换、杂谈、排行榜等内容

    private static final int ANIME = 13;  // 番剧(主分区)
    private static final int ANIME_SERIAL = 33;  // 当季连载的动画番剧
    private static final int ANIME_FINISH = 32;  // 已完结的动画番剧合集
    private static final int ANIME_INFORMATION = 51;  // 动画番剧相关资讯视频
    private static final int ANIME_OFFICAL = 152;  // 动画番剧为主题的宣传节目、采访视频，及声优相关视频

    private static final int GUO_CHUANG = 167;  // 国创(主分区)
    private static final int GUO_CHUANG_CHINESE = 153;  // 我国出品的PGC动画
    private static final int GUO_CHUANG_ORIGINAL = 168;  // 国产原创相关
    private static final int GUO_CHUANG_PUPPETRY = 169;  // 布袋戏
    private static final int GUO_CHUANG_MOTIONCOMIC = 195;  // 动态漫·广播剧
    private static final int GUO_CHUANG_INFORMATION = 170;  // 资讯

    private static final int MUSIC = 3;  // 音乐(主分区)
    private static final int MUSIC_ORIGINAL = 28;  // 个人或团队制作以音乐为主要原创因素的歌曲或纯音乐
    private static final int MUSIC_COVER = 31;  // 一切非官方的人声再演绎歌曲作品
    private static final int MUSIC_VOCALOID = 30;  // 以雅马哈Vocaloid和UTAU引擎为基础，包含其他调教引擎，运用各类音源进行的歌曲创作内容
    private static final int MUSIC_ELECTRONIC = 194;  // 以电子合成器、音乐软体等产生的电子声响制作的音乐
    private static final int MUSIC_PERFORM = 59;  // 传统或非传统乐器及器材的演奏作品
    private static final int MUSIC_MV = 193;  // 音乐录影带，为搭配音乐而拍摄的短片
    private static final int MUSIC_LIVE = 29;  // 音乐实况表演视频
    private static final int MUSIC_OTHER = 130;  // 收录无法定义到其他音乐子分区的音乐视频

    private static final int DANCE = 129;  // 舞蹈(主分区)
    private static final int DANCE_OTAKU = 20;  // 宅舞 与ACG相关的翻跳、原创舞蹈
    private static final int DANCE_HIPHOP = 189;  // 收录街舞相关内容，包括赛事现场、舞室作品、个人翻跳、FREESTYLE等
    private static final int DANCE_STAR = 199;  // 国内外明星发布的官方舞蹈及其翻跳内容
    private static final int DANCE_CHINA = 200;  // 传承中国艺术文化的舞蹈内容，包括古典舞、民族民间舞、汉唐舞、古风舞等
    private static final int DANCE_THREE_D = 154;  // 收录无法定义到其他舞蹈子分区的舞蹈视频
    private static final int DANCE_DOME = 156;  // 镜面慢速，动作分解，基础教程等具有教学意义的舞蹈视频

    private static final int GAME = 4;  // 游戏(主分区)
    private static final int GAME_STAND_ALONE = 17;  // 以所有平台(PC、主机、移动端)的单机或联机游戏为主的视频内容，包括游戏预告、CG、实况解说及相关的评测、杂谈与视频剪辑等
    private static final int GAME_ESPORTS = 171;  // 具有高对抗性的电子竞技游戏项目，其相关的赛事、实况、攻略、解说、短剧等视频
    private static final int GAME_MOBILE = 172;  // 以手机及平板设备为主要平台的游戏，其相关的实况、攻略、解说、短剧、演示等视频
    private static final int GAME_ONLINE = 65;  // 由网络运营商运营的多人在线游戏，以及电子竞技的相关游戏内容。包括赛事、攻略、实况、解说等相关视频
    private static final int GAME_BOARD = 173;  // 桌游、棋牌、卡牌对战等及其相关电子版游戏的实况、攻略、解说、演示等视频
    private static final int GAME_GMV = 121;  // 由游戏素材制作的MV视频。以游戏内容或CG为主制作的，具有一定创作程度的MV类型的视频
    private static final int GAME_MUSIC = 136;  // 各个平台上，通过配合音乐与节奏而进行的音乐类游戏视频
    private static final int GAME_MUGEN = 19;  // 以Mugen引擎为平台制作、或与Mugen相关的游戏视频

    private static final int KNOWLEDGE = 36;  // 知识(主分区)
    private static final int KNOWLEDGE_SCIENCE = 201;  // 回答你的十万个为什么
    private static final int KNOWLEDGE_SOCIAL_SCIENCE = 124;  // 基于社会科学、法学、心理学展开或个人观点输出的知识视频
    private static final int KNOWLEDGE_HUMANITY_HISTORY = 228;  // 看看古今人物，聊聊历史过往，品品文学典籍
    private static final int KNOWLEDGE_BUSINESS = 207;  // 说金融市场，谈宏观经济，一起畅聊商业故事
    private static final int KNOWLEDGE_CAMPUS = 208;  // 老师很有趣，同学多人才，我们都爱搞学习
    private static final int KNOWLEDGE_CAREER = 209;  // 职场加油站，成为最有料的职场人
    private static final int KNOWLEDGE_DESIGN = 229;  // 天马行空，创意设计，都在这里
    private static final int KNOWLEDGE_SKILL = 122;  // 炫酷技能大集合，是时候展现真正的技术了

    private static final int TECH = 188;  // 科技(主分区)
    private static final int TECH_DIGITAL = 95;  // 科技数码产品大全，一起来做发烧友
    private static final int TECH_APPLICATION = 230;  // 超全软件应用指南
    private static final int TECH_COMPUTER_TECH = 231;  // 研究分析、教学演示、经验分享......有关计算机技术的都在这里
    private static final int TECH_INDUSTRY = 232;  // 前方高能，机甲重工即将出没
    private static final int TECH_DIY = 233;  // 炫酷技能，极客文化，硬核技巧，准备好你的惊讶

    private static final int SPORTS = 234;  // 运动(主分区
    private static final int SPORTS_BASKETBALL_FOOTBALL = 235;  // 与篮球、足球相关的视频，包括但不限于篮足球赛事、教学、评述、剪辑、剧情等相关内容
    private static final int SPORTS_AEROBICS = 164;  // 与健身相关的视频，包括但不限于瑜伽、CrossFit、健美、力量举、普拉提、街健等相关内容
    private static final int SPORTS_ATHLETIC = 236;  // 与竞技体育相关的视频，包括但不限于乒乓、羽毛球、排球、赛车等竞技项目的赛事、评述、剪辑、剧情等相关内容
    private static final int SPORTS_CULTURE = 237;  // 与运动文化相关的视频，包络但不限于球鞋、球衣、球星卡等运动衍生品的分享、解读，体育产业的分析、科普等相关内容
    private static final int SPORTS_COMPREHENSIVE = 238;  // 与运动综合相关的视频，包括但不限于钓鱼、骑行、滑板等日常运动分享、教学、Vlog等相关内容

    private static final int CAR = 223;  // 汽车(主分区)
    private static final int CAR_LIFE = 176;  // 分享汽车及出行相关的生活体验类视频
    private static final int CAR_CULTURE = 224;  // 车迷的精神圣地，包括汽车赛事、品牌历史、汽车改装、经典车型和汽车模型等
    private static final int CAR_RACING = 245;  // F1等汽车运动相关
    private static final int CAR_GEEK = 225;  // 汽车硬核达人聚集地，包括DIY造车、专业评测和技术知识分享
    private static final int CAR_MOTORCYCLE = 240;  // 骑士们集合啦
    private static final int CAR_SMART = 226;  // 探索新能源汽车和未来智能出行的前沿阵地
    private static final int CAR_STRATEGY = 227;  // 丰富详实的购车建议和新车体验

    private static final int LIFE = 160;  // 生活(主分区)
    private static final int LIFE_FUNNY = 138;  // 各种沙雕有趣的搞笑剪辑，挑战，表演，配音等视频
    private static final int LIFE_HOME = 239;  // 与买房、装修、居家生活相关的分享
    private static final int LIFE_HANDMAKE = 161;  // 手工制品的制作过程或成品展示、教程、测评类视频
    private static final int LIFE_PAINTING = 162;  // 绘画过程或绘画教程，以及绘画相关的所有视频
    private static final int LIFE_DAILY = 21;  // 记录日常生活，分享生活故事

    private static final int FOOD = 211;  // 美食(主分区)
    private static final int FOOD_MAKE = 76;  // 学做人间美味，展示精湛厨艺
    private static final int FOOD_DETECTIVE = 212;  // 寻找美味餐厅，发现街头美食
    private static final int FOOD_MEASUREMENT = 213;  // 吃货世界，品尝世间美味
    private static final int FOOD_RURAL = 214;  // 品味乡野美食，寻找山与海的味道
    private static final int FOOD_RECORD = 215;  // 记录一日三餐，给生活添一点幸福感

    private static final int ANIMAL = 217;  // animal(主分区)
    private static final int ANIMAL_CAT = 218;  // 喵喵喵喵喵
    private static final int ANIMAL_DOG = 219;  // 汪汪汪汪汪
    private static final int ANIMAL_PANDA = 220;  // 芝麻汤圆营业中
    private static final int ANIMAL_WILD_ANIMAL = 221;  // 内有“猛兽”出没
    private static final int ANIMAL_REPTILES = 222;  // 鳞甲有灵
    private static final int ANIMAL_COMPOSITE = 75;  // 收录除上述子分区外，其余动物相关视频以及非动物主体或多个动物主体的动物相关延伸内容

    private static final int KICHIKU = 119;  // 鬼畜(主分区)
    private static final int KICHIKU_GUIDE = 22;  // 使用素材在音频、画面上做一定处理，达到与BGM一定的同步感
    private static final int KICHIKU_MAD = 26;  // 使用素材音频进行一定的二次创作来达到还原原曲的非商业性质稿件
    private static final int KICHIKU_MANUAL_VOCALOID = 126;  // 将人物或者角色的无伴奏素材进行人工调音，使其就像VOCALOID一样歌唱的技术
    private static final int KICHIKU_THEATRE = 216;  // 使用素材进行人工剪辑编排的有剧情的作品
    private static final int KICHIKU_COURSE = 127;  // 鬼畜相关的教程演示

    private static final int FASHION = 155;  // 时尚(主分区)
    private static final int FASHION_MAKEUP = 157;  // 涵盖妆容、发型、美甲等教程，彩妆、护肤相关产品测评、分享等
    private static final int FASHION_CLOTHING = 158;  // 服饰风格、搭配技巧相关的展示和教程视频
    private static final int FASHION_CATWALK = 159;  // 发布会走秀现场及模特相关时尚片、采访、后台花絮

    private static final int INFORMATION = 202;  // 资讯(主分区)
    private static final int INFORMATION_HOTSPOT = 203;  // 全民关注的时政热门资讯
    private static final int INFORMATION_GLOBAL = 204;  // 全球范围内发生的具有重大影响力的事件动态
    private static final int INFORMATION_SOCIAL = 205;  // 日常生活的社会事件、社会问题、社会风貌的报道
    private static final int INFORMATION_MULTIPLE = 206;  // 除上述领域外其它垂直领域的综合资讯

    private static final int ENT = 5;  // 娱乐(主分区)
    private static final int ENT_VARIETY = 71;  // 国内外有趣的综艺和综艺相关精彩剪辑
    private static final int ENT_TALKER = 241;  // 娱乐人物解读、娱乐热点点评、娱乐行业分析
    private static final int ENT_FANS = 242;  // 粉丝向创作视频
    private static final int ENT_CELEBRITY = 137;  // 娱乐圈动态、明星资讯相关

    private static final int CINEPHILE = 181;  // 影视(主分区)
    private static final int CINEPHILE_CINECISM = 182;  // 影视评论、解说、吐槽、科普等
    private static final int CINEPHILE_MONTAGE = 183;  // 对影视素材进行剪辑再创作的视频
    private static final int CINEPHILE_SHORTFILM = 85;  // 追求自我表达且具有特色的短片
    private static final int CINEPHILE_TRAILER_INFO = 184;  // 影视类相关资讯，预告，花絮等视频

    private static final int DOCUMENTARY = 177;  // 纪录片(主分区)
    private static final int DOCUMENTARY_HISTORY = 37;  // 人文·历史
    private static final int DOCUMENTARY_SCIENCE = 178;  // 科学·探索·自然
    private static final int DOCUMENTARY_MILITARY = 179;  // 军事
    private static final int DOCUMENTARY_TRAVEL = 180;  // 社会·美食·旅行

    private static final int MOVIE = 23;  // 电影(主分区)
    private static final int MOVIE_CHINESE = 147;  // 华语电影
    private static final int MOVIE_WEST = 145;  // 欧美电影
    private static final int MOVIE_JAPAN = 146;  // 日本电影
    private static final int MOVIE_MOVIE = 83;  // 其他国家

    private static final int TV = 11;  // 电视剧(主分区)
    private static final int TV_MAINLAND = 185;  // 国产剧
    private static final int TV_OVERSEAS = 187;  // 海外剧
}
