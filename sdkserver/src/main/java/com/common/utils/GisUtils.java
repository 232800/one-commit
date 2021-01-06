package com.common.utils;
/**
 * @Auther: zcc
 * @Date: 19-6-19 09:23
 * @Description: gis地图对接时用到的工具类
 */
public class GisUtils {

    private static final double EARTH_RADIUS = 6371008.8;    //单位M

    /**
     * 转化为弧度(rad)
     */
    private static double getRad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 利用两点间的经纬度计算两点间的距离（圆球）
     *
     * @param lng1 第一个点的经度
     * @param lat1 第一个点的纬度
     * @param lng2 第二个点的经度
     * @param lat2 第二个点的纬度
     * @returns {*} 返回两点距离 (m)
     */
    public static double calDistance(double lng1, double lat1, double lng2, double lat2) {
        double radLat1 = getRad(lat1);
        double radLat2 = getRad(lat2);
        double a = radLat1 - radLat2;
        double b = getRad(lng1) - getRad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * 6378.137;
        return s * 1000;
    }

    /**
     * 利用两点间的经纬度计算两点间的距离（椭球）
     *
     * @param lng1
     * @param lat1
     * @param lng2
     * @param lat2
     * @returns {number}
     */
    public static double getFlatternDistance(double lng1, double lat1, double lng2, double lat2) {
        double f = getRad((lat1 + lat2) / 2);
        double g = getRad((lat1 - lat2) / 2);
        double l = getRad((lng1 - lng2) / 2);

        double sg = Math.sin(g);
        double sl = Math.sin(l);
        double sf = Math.sin(f);

        double s, c, w, r, d, h1, h2;
        double a = EARTH_RADIUS;
        double fl = 1 / 298.257;

        sg = sg * sg;
        sl = sl * sl;
        sf = sf * sf;

        s = sg * (1 - sl) + (1 - sf) * sl;
        c = (1 - sg) * (1 - sl) + sf * sl;

        w = Math.atan(Math.sqrt(s / c));
        r = Math.sqrt(s * c) / w;
        d = 2 * w * a;
        h1 = (3 * r - 1) / 2 / c;
        h2 = (3 * r + 1) / 2 / s;
        return d * (1 + fl * (h1 * sf * (1 - sg) - h2 * (1 - sf) * sg));
    }

}
