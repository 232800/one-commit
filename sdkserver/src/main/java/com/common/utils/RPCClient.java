package com.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.mortbay.util.MultiMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerMethodMappingNamingStrategy;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * @package com.ay.sdkclient.custom.component.websocket
 * @Class RPCClient
 * @Description TODO
 * @Author zhangxinhua
 * @Date 19-7-10 下午5:29
 */
public class RPCClient {
    public static Object getResultFromUrlParam(String requestMapping, MultiMap multiMap) {
        Object object = new Object();
        DispatcherServlet dispatcherServlet = ContextUtil.getApplicationContext().getBean(DispatcherServlet.class);
        RequestMappingHandlerMapping requestMappingHandlerMapping = getRequestMappingHandlerMapping(dispatcherServlet);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
        HandlerMethodMappingNamingStrategy<RequestMappingInfo> namingStrategy = requestMappingHandlerMapping.getNamingStrategy();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
            Set<String> patterns = entry.getKey().getPatternsCondition().getPatterns();
            for (String pattern : patterns) {
                if (pattern != null && (pattern.toString().equals(requestMapping)||pattern.toString().equals("/"+requestMapping))) {
                    HandlerMethod handlerMethod = entry.getValue();
                    Object bean = handlerMethod.getBean();
                    Method method = handlerMethod.getMethod();
                    if (handlerMethod.getMethodParameters().length>0) {
                        try {
                            object = method.invoke(ContextUtil.getApplicationContext().getBean(handlerMethod.getBeanType()), (HashMap) multiMap);
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }
        Map<String, Object> resultMap = new HashMap<>();
        String key = requestMapping.substring(requestMapping.lastIndexOf("/") + 1);
        resultMap.put(key, object);
        return resultMap;
    }

    private static RequestMappingHandlerMapping getRequestMappingHandlerMapping(DispatcherServlet dispatcherServlet) {
        RequestMappingHandlerMapping requestMappingHandlerMapping = null;
        //获取所有的handlermapping
        List<HandlerMapping> handlerMappings = dispatcherServlet.getHandlerMappings();
        if (!CollectionUtils.isEmpty(handlerMappings)) {
            for (HandlerMapping handlerMapping : handlerMappings) {
                if (handlerMapping instanceof RequestMappingHandlerMapping) {
                    return (RequestMappingHandlerMapping) handlerMapping;
                }
            }
        }
        return requestMappingHandlerMapping;
    }
}
