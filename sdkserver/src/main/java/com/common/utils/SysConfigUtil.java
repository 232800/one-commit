package com.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Desc   : 系统配置文件获取
 * Param  :
 * Author :羊天祥
 * Date   :2020-07-31 22:17
 */
public class SysConfigUtil {

    private static SysConfigUtil sysConfigUtil=null;

    private String fileUploadPath; //文件上传路径
    private String tabDatapool; //业务表拼接

    public static SysConfigUtil getInstance() {
        if (sysConfigUtil == null) {
            sysConfigUtil = new SysConfigUtil();
        }
        return sysConfigUtil;
    }

    public SysConfigUtil(){
        InputStream inputStream = this.getClass().getResourceAsStream("/sysconfig.properties");
        Properties config = new Properties();
        try{
            config.load(inputStream);
            this.fileUploadPath = config.getProperty("fileUploadPath");
            this.tabDatapool = config.getProperty("tab_datapool");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    //获取文件上传路径
    public String getFileUploadPath() {
        return this.fileUploadPath;
    }
    //获取业务表全表名
    public String getTableName(String tableName) {
        return this.tabDatapool + tableName ;
    }


}
