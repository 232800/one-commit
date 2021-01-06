package com.common.base;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    protected final String SUCCESS = "操作成功";
    protected final String FAIL = "操作失败";
    /**
     * 获取数据成功
     *
     * <pre>
     * status = 200
     * data = data
     * message = “获取数据成功”
     * </pre>
     *
     * @param data 返回的数据
     * @return
     */
    protected Map<String, Object> formatReturnData(Object data,String message,Integer status) {
        Map<String, Object> formatReturnData = new HashMap<String, Object>();
        formatReturnData.put("status", status);
        formatReturnData.put("data", data);
        formatReturnData.put("message", message);
        return formatReturnData;
    }

}
