package com.yushu.intecpter;

import com.alibaba.fastjson.JSONObject;
import com.yushu.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class LoginIntecpter implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 进入controller之前
        String token = request.getParameter("token");
        if(StringUtils.isEmpty(token)) {
            response.setContentType("application/json; charset=utf-8");
            PrintWriter writer = response.getWriter();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code","-1");
            jsonObject.put("message","请登录");
            writer.print(jsonObject.toString());
            writer.close();
            response.flushBuffer();
            return false;
        }else{
            Claims claims = JwtUtils.decodeJwt(token);
            String openId = (String) claims.get("openId");
            String nickName = (String) claims.get("nickName");
            String headImg = (String) claims.get("headImg");

            request.setAttribute("openId",openId);
            request.setAttribute("nickName",nickName);
            request.setAttribute("headImg",headImg);
        }
        return HandlerInterceptor.super.preHandle(request,response,handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        // controller处理之后，进入视图之前
        System.out.println("TestIntecpter => postHandle");
        HandlerInterceptor.super.postHandle(request,response,handler,modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        // 整个请求结束后，视图渲染后，用于资源的清理
        System.out.println("TestIntecpter => afterCompletion");
        HandlerInterceptor.super.afterCompletion(request,response,handler,ex);
    }
}
