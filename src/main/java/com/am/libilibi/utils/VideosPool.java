package com.am.libilibi.utils;

import com.am.libilibi.blapi.BLVideoZone;
import com.am.libilibi.entity.GeneralVideo;
import com.am.libilibi.entity.LBProxy;

import java.util.*;


/**
 * @Author : ArturiaMu KMUST-Stu
 * @Date : 2022/5/4 15:19
 * @Params : @param null
 * @Exception :
 * @Return :
 * @Description ：
 */
public class VideosPool {
    private static final Map<String, ArrayList<GeneralVideo>> generalVideosPools = new HashMap<>();

    private static final String[] vNames = new String[]{
            "DOUGA", "ANIME", "GUO_CHUANG", "MUSIC", "DANCE", "GAME", "KNOWLEDGE", "TECH"
            , "SPORTS", "CAR", "LIFE", "FOOD", "ANIMAL", "KICHIKU", "FASHION", "INFORMATION", "ENT"
            , "CINEPHILE", "DOCUMENTARY", "MOVIE", "TV"
    };

    private static final int[][] items = {
            {1, 24, 25, 47, 210, 86, 27}, {13, 33, 32, 51, 152}, {167, 153, 168, 169, 195, 170},
            {3, 28, 31, 30, 194, 59, 193, 29, 130}, {129, 20, 189, 199, 200, 154, 156},
            {4, 17, 171, 172, 65, 173, 121, 136, 19}, {36, 201, 124, 228, 207, 208, 209, 229, 122},
            {188, 95, 230, 231, 232, 233}, {234, 235, 164, 236, 237, 238}, {223, 176, 224, 245, 225, 240, 226, 227},
            {160, 138, 239, 161, 162, 21}, {211, 76, 212, 213, 214, 215}, {217, 218, 219, 220, 221, 222, 75},
            {119, 22, 26, 126, 216, 127}, {155, 157, 158, 159}, {202, 203, 204, 205, 206}, {5, 71, 241, 242, 137},
            {181, 182, 183, 85, 184}, {177, 37, 178, 179, 180}, {23, 147, 145, 146, 83}, {11, 185, 187},
    };

    public static void run() {
        System.out.println("init videos pool...");
        for (String vName : vNames) {
            generalVideosPools.put(vName, new ArrayList<GeneralVideo>());
        }
        Thread t = new Thread(new VideoRunnable());
        t.setDaemon(true);
        t.start();
    }

    public VideosPool() {

    }


    static class VideoRunnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < vNames.length; i++) {
                String jsStr = null;
                try {
                    jsStr = HttpUtils.httpRequest(BLVideoZone.getZoneLatest(items[i][0], 50, 1), "GET", null, null);
                    generalVideosPools.get(vNames[i]).addAll(JsonUtils.jsStrToGeneralVideo(jsStr));
                    System.out.println(Arrays.toString(items[i]));
                } catch (Exception e) {
                    e.printStackTrace();
                    i -= 1;
                    System.out.println("http err...");
                    try {
                        Thread.sleep(10 * 1000);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                }
            }
            LBProxy proxy = ProxyPool.getRandomProxy();
            int cnt = 0;

            for (int i = 0; i < vNames.length; i++) {
                for (int j = 1; j < items[i].length; j++) {
                    System.out.println(2);
                    if (cnt == 8) {
                        proxy = ProxyPool.getRandomProxy();
                        cnt = 0;
                    }
                    System.out.println(proxy);
                    System.setProperty("proxyHost", proxy.getHost());
                    System.setProperty("proxyPort", proxy.getPort());
                    String jsStr = null;
                    try {
                        jsStr = HttpUtils.httpRequest(BLVideoZone.getZoneLatest(items[i][j], 50, 1), "GET", null, null);
                        cnt += 1;
                    } catch (Exception e) {
                        System.out.println("proxy timeout...");
                        proxy = ProxyPool.getRandomProxy();
                        j -= 1;
                        cnt = 0;
                        continue;
                    }
                    System.out.println(vNames[i] + " " + (j + 1) + " / " + items[i].length);
                    try {
                        generalVideosPools.get(vNames[i]).addAll(JsonUtils.jsStrToGeneralVideo(jsStr));
                    } catch (Exception e) {
                        j -= 1;
                    }
                }
            }
        }
    }

    public List<GeneralVideo> getGeneralVideoByReco(int ps) {

        List<GeneralVideo> relist = new ArrayList<>();
        for (String vName : vNames) {
            relist.addAll(this.getGeneralVideoByType(vName, 1));
        }
        return relist;
    }

    public List<GeneralVideo> getGeneralVideoByType(String type, int cnt) {
        ArrayList<GeneralVideo> generalVideos = generalVideosPools.get(type);
        int s = new Random().nextInt(generalVideos.size()) - cnt;
        if (s < 0) {
            s = 0;
        }
        List<GeneralVideo> relist = new ArrayList<>();
        for (int i = s; i < s + cnt; i++) {
            relist.add(generalVideos.get(i));
        }
        return relist;
    }
}
