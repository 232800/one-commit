package com.common.utils;

import java.util.regex.Pattern;

/**
 * @package com.ay.sdkclient.sdk.common.utils
 * @Class NumberUtil
 * @Description 数字操作工具类
 * @Author zhangxinhua
 * @Date 19-7-15 下午2:02
 */
public class NumberUtil {
    private static NumberUtil util;

    public static NumberUtil getInstance() {
        if (null == util) {
            util = new NumberUtil();
        }
        return util;
    }

    /**
     * 判断一个字符串是否是数字。
     *
     * @param string
     * @return
     */
    public boolean isNumber(String string) {
        if (string == null) {
            return false;
        }
        Pattern pattern = Pattern.compile("^-?\\d+(\\.\\d+)?$");
        return pattern.matcher(string).matches();
    }
}
