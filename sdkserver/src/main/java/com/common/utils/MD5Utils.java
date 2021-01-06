package com.common.utils;

import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MD5Utils {
    private static final Logger logger = LoggerFactory.getLogger(MD5Utils.class);

    /**
     * 获取小写md5加密值
     *
     * @param str
     * @return 入参为null时，返回空字符串
     */
    public static String md5ToLower(String str) {
        if (str != null) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(str.getBytes());
                byte[] b = md.digest();

                StringBuffer buf = new StringBuffer("");
                for (int offset = 0; offset < b.length; offset++) {
                    int i = b[offset];
                    if (i < 0) {
                        i += 256;
                    }
                    if (i < 16) {
                        buf.append("0");
                    }
                    buf.append(Integer.toHexString(i));
                }
                str = buf.toString();
            } catch (Exception e) {
                logger.error("create md5 error", e);
            }
        } else {
            str = "";
        }

        return str;
    }

    /**
     * 获取大写md5加密值
     *
     * @param str
     * @return 入参为null时，返回空字符串
     */
    public static String md5ToUpper(String str) {
        return md5ToLower(str).toUpperCase();
    }

}
