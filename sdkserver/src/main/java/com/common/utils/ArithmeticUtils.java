package com.common.utils;

import java.text.DecimalFormat;

/**
 * @program: sdkclient
 * @description: 运算工具类
 * @author: ChengQi
 * @create: 2019-07-14 16:00
 **/
public class ArithmeticUtils {

    /**
    * @Description:  占比计算保留小数的位数方法,转成百分数
    * @Param: [num1, num2] 
    * @return: java.lang.String 
    * @Author: ChengQi 
    * @Date: 2019/7/14 
    */ 
    public static String division(int num1, int num2) {
        String rate = "0.00%";
        //定义格式化起始位数
        String format = "0.00";
        if (num2 != 0 && num1 != 0) {
            DecimalFormat dec = new DecimalFormat(format);
            rate = dec.format((double) num1 / num2 * 100) + "%";
        } else {
            rate = "0%";
        }
        return rate;
    }
}
