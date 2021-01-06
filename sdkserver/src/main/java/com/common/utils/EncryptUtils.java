package com.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EncryptUtils {

    /**
     * 附件加密
     *
     * @param fileName
     */
    public static String attachEncrypt(String fileName) {
        String[] file = fileName.split("_");
        if (file == null) {
            return fileName;
        } else if (file.length != 2) {
            return fileName;
        } else {
            String md5 = MD5Utils.md5ToLower(file[0] + EncryptUtils.getDay());
            return md5.substring(5, 15) + fileName;
        }
    }


    /**
     * 附件解密
     *
     * @param encryptFileName
     */
    public static String attachDecrypt(String encryptFileName) {
        String[] file = encryptFileName.split("_");
        if (file == null) {
            return encryptFileName;
        } else if (file.length != 2 || file[0].length() <= 10) {
            return encryptFileName;
        } else {
            String encryptWord = file[0];
            String fileId = encryptWord.substring(10);
            String md5 = MD5Utils.md5ToLower((fileId + EncryptUtils.getDay())).substring(5, 15);
            String password = md5 + fileId;
            return (encryptWord.equals(password) ? fileId + "_" + file[1] : "");
        }
    }

    public static String getDay() {
        SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
        return sdfDay.format(new Date());
    }

}
