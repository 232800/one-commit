package com.common.utils;

import java.util.List;
import java.util.Map;

import org.springframework.util.CollectionUtils;

/**
 * @package com.ay.sdkclient.custom.util
 * @Class CoordinateUtil
 * @Description 坐标工具类
 * @Author zhangxinhua
 * @Date 19-7-15 下午1:40
 */
public class CoordinateUtil {
    private static CoordinateUtil util ;

    public static CoordinateUtil getInstance() {
        if (util == null) {
            util = new CoordinateUtil();
        }
        return util;
    }

    public Long getMaxCoord(List<Map<String, Object>> list, String... keys) {
        Long max = 0L;
        if (!CollectionUtils.isEmpty(list)) {
            for (Map<String, Object> map : list) {
                for (int i = 0; i < keys.length; i++) {
                    String str = map.get(keys[i]).toString();
                    //验证str是否是数字
                    if (!NumberUtil.getInstance().isNumber(str)) {
                        continue;
                    }
                    if (max < Long.valueOf(str)) {
                        max = Long.valueOf(str);
                    }
                }
            }
        }
        return max;
    }
}
