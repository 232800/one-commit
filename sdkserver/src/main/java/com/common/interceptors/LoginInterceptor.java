package com.common.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.alibaba.fastjson.JSONObject;
import com.common.utils.TokenUtil;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*
    	httpServletResponse.setHeader("content-type", "text/json;charset=UTF-8");
        httpServletResponse.getWriter().println("您尚未登录");
        */
    	System.out.println(request.getServletPath());
        response.setCharacterEncoding("UTF-8");
        String token = request.getHeader("Token");
        if(token != null){
            boolean result = TokenUtil.verify(token,request);
            if(result){
                System.out.println("通过拦截器");
                return true;
            }
        }
        response.setContentType("application/json; charset=utf-8");
        try{
            JSONObject json = new JSONObject();
            json.put("message","您尚未登录");
            json.put("status",505);
            response.getWriter().append(json.toJSONString());
            System.out.println("认证失败，未通过拦截器");
        }catch (Exception e){
            e.printStackTrace();
            response.sendError(500);
            return false;
        }
        return false;
    }
}
