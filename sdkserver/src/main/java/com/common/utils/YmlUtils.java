package com.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.yaml.snakeyaml.Yaml;

public class YmlUtils {

    private static Logger logger = LogManager.getLogger(YmlUtils.class);

    private static final String APPLICATION_YML = "application.yml";

    private static Map<String, String> resultMap = new HashMap<>();

    /**
     * 根据文件名获取yml的文件内容
     *
     * @param file
     * @return
     */
    public static Map<String, String> getYmlByFileName(String file) {
        resultMap = new HashMap<>();
        try {
            ClassPathResource classPathResource = new ClassPathResource(file);
            InputStream inputStream = classPathResource.getInputStream();
            Yaml props = new Yaml();
            Object obj = props.loadAs(inputStream, Map.class);
            Map<String, Object> param = (Map<String, Object>) obj;
            for (Map.Entry<String, Object> entry : param.entrySet()) {
                String key = entry.getKey();
                Object val = entry.getValue();
                if (val instanceof Map) {
                    forEachYaml(key, (Map<String, Object>) val);
                } else {
                    resultMap.put(key, val.toString());
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return resultMap;
    }


    /**
     * 遍历yml文件，获取map集合
     *
     * @param key_str
     * @param obj
     * @return
     */
    public static Map<String, String> forEachYaml(String key_str, Map<String, Object> obj) {
        for (Map.Entry<String, Object> entry : obj.entrySet()) {
            String key = entry.getKey();
            Object val = entry.getValue();
            String str_new = "";
            if (key_str != null && key_str.length() != 0) {
                str_new = key_str + "." + key;
            } else {
                str_new = key;
            }
            if (val instanceof Map) {
                forEachYaml(str_new, (Map<String, Object>) val);
            } else {
                resultMap.put(str_new, val.toString());
            }
        }
        return resultMap;
    }

    public static String getWeb_Protocol(String defaultValue) {
        String value = getYmlByFileName(APPLICATION_YML).get("web_protocol");
        if (StringUtils.isNotBlank(defaultValue)) {
            value = defaultValue;
        }
        return value;
    }

    public static String getRoot_Domain(String defaultValue) {
        String value = getYmlByFileName(APPLICATION_YML).get("root_domain");
        if (StringUtils.isNotBlank(defaultValue)) {
            value = defaultValue;
        }
        return value;
    }

    public static String getWWW_Domain(String defaultValue) {
        String value = getYmlByFileName(APPLICATION_YML).get("www_domain");
        if (StringUtils.isNotBlank(defaultValue)) {
            value = defaultValue;
        }
        return value;
    }

    public static String getStatic_Domain(String defaultValue) {
        String value = getYmlByFileName(APPLICATION_YML).get("static_domain");
        if (StringUtils.isNotBlank(defaultValue)) {
            value = defaultValue;
        }
        return value;
    }

    public static String getFileIO_Domain(String defaultValue) {
        String value = getYmlByFileName(APPLICATION_YML).get("fileIO_domain");
        if (StringUtils.isNotBlank(defaultValue)) {
            value = defaultValue;
        }
        return value;
    }

    public static String getApplicationName(String defaultValue) {
        String value = getYmlByFileName(APPLICATION_YML).get("application_name");
        if (StringUtils.isNotBlank(defaultValue)) {
            value = defaultValue;
        }
        return value;
    }

    public static String getDomain(String defaultValue) {
        String value = getYmlByFileName(APPLICATION_YML).get("domain");
        if (StringUtils.isNotBlank(defaultValue)) {
            value = defaultValue;
        }
        return value;
    }

    public static String getDocWriterTempPath(String defaultValue) {
        String value = getYmlByFileName(APPLICATION_YML).get("docWriter.tempPath");
        if (StringUtils.isNotBlank(defaultValue)) {
            value = defaultValue;
        }
        return value;
    }
    public static String getSdkSecurityDes3IV(String defaultValue) {
        String value = getYmlByFileName(APPLICATION_YML).get("sdk_securityDes3IV");
        if (StringUtils.isNotBlank(defaultValue)) {
            value = defaultValue;
        }
        return value;
    }
    public static String getSdkSecurityDes3KeyV(String defaultValue) {
        String value = getYmlByFileName(APPLICATION_YML).get("sdk_securityDes3Key");
        if (StringUtils.isNotBlank(defaultValue)) {
            value = defaultValue;
        }
        return value;
    }

    public static String getRpcMapperLocation(String defaultValue) {
        String value = getYmlByFileName(APPLICATION_YML).get("rpc.mapperLocation");
        if (StringUtils.isNotBlank(defaultValue)) {
            value = defaultValue;
        }
        return value;
    }

    public static String getRpcBasePackage(String defaultValue) {
        String value = getYmlByFileName(APPLICATION_YML).get("rpc.basePackage");
        if (StringUtils.isNotBlank(defaultValue)) {
            value = defaultValue;
        }
        return value;
    }


    public static String getSelfMapperLocation(String defaultValue) {
        String value = getYmlByFileName(APPLICATION_YML).get("self.mapperLocation");
        if (StringUtils.isNotBlank(defaultValue)) {
            value = defaultValue;
        }
        return value;
    }

    public static String getSelfBasePackage(String defaultValue) {
        String value = getYmlByFileName(APPLICATION_YML).get("self.basePackage");
        if (StringUtils.isNotBlank(defaultValue)) {
            value = defaultValue;
        }
        return value;
    }

    public static String getSendFlag(String defaultValue) {
        String value = getYmlByFileName(APPLICATION_YML).get("send_flag");
        if (StringUtils.isNotBlank(defaultValue)&&StringUtils.isBlank(value)) {
            value = defaultValue;
        }
        return value;
    }

    public static void main(String[] args) {
        String aFalse = getSendFlag("false");
        System.out.println(aFalse);
    }
}
