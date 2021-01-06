package com.common.utils;

import org.springframework.cglib.beans.BeanMap;

import java.util.Map;

public class BeanUtils {


    /**
     * map转bean
     *
     * @param map
     * @param bean
     * @param <T>
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> map, T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }


}
