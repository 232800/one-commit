package com.common.utils;


import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Common3DESUtil {
    private static final Logger logger = LoggerFactory.getLogger(Common3DESUtil.class);

    public static String decode3DES(String text, String iv, String keyValue) {
        byte[] key;
        byte[] keyiv;
        try {
            byte[] encodeString = Hex.decodeHex(text.toCharArray());
            keyiv = iv.substring(0, 8).getBytes("UTF-8");
            key = keyValue.getBytes("UTF-8");
            SecretKey deskey = new SecretKeySpec(key, "DESede");
            Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            IvParameterSpec ips = new IvParameterSpec(keyiv);
            cipher.init(Cipher.DECRYPT_MODE, deskey, ips); // 解密模式
            byte[] decodeString = cipher.doFinal(encodeString);
            return new String(decodeString);
        } catch (Exception e) {
            logger.error("decode3DES", e);
            return "";
        }
    }

    public static String encode3DES(String text, String iv, String keyValue) {
        byte[] key;
        byte[] keyiv;
        try {
            keyiv = iv.substring(0, 8).getBytes("UTF-8");
            key = keyValue.getBytes("UTF-8");
            SecretKey deskey = new SecretKeySpec(key, "DESede");
            Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            IvParameterSpec ips = new IvParameterSpec(keyiv);
            cipher.init(Cipher.ENCRYPT_MODE, deskey, ips); // 加密模式
            byte[] encodeString = cipher.doFinal(text.getBytes());
            return Hex.encodeHexString(encodeString);
        } catch (Exception e) {
            logger.error("encode3DES", e);
            return "";
        }
    }
}
