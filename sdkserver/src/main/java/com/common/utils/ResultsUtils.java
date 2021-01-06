package com.common.utils;

import java.util.List;
import java.util.Map;

/**
 * @program: sdkclient
 * @description: 查询结果处理
 * @author: ChengQi
 * @create: 2019-06-27 11:01
 **/
public class ResultsUtils {

    /** 
    * @Description: 统计返回List转int 
    * @Param: [result] 
    * @return: int 
    * @Author: ChengQi 
    * @Date: 2019/6/27 
    */ 
    public static int listToCount(List<Map<String, Object>> result) {
        return Integer.parseInt(result.get(0).get("count").toString());
    }
}
