package com.example.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.example.common.JWTUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.HashMap;
import java.util.Map;

public class JWTInterceptors implements HandlerInterceptor {
    public boolean preHandler(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        Map<String, Object> map = new HashMap<>();

        //获取请求头中令牌
        String token = request.getHeader("token");

        try{
            //验证令牌
            JWTUtils.verify(token);
            return true;  //请求放行
        } catch (SignatureVerificationException e) {
            e.printStackTrace();
            map.put("msg", "无效的签名，请重试！");
        } catch (TokenExpiredException e) {
            e.printStackTrace();
            map.put("msg", "token过期啦，once again");
        } catch (AlgorithmMismatchException e) {
            e.printStackTrace();
            map.put("msg", "算法不一致，自己找原因");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "token无效！");
        }
        map.put("state", false);  //设置状态

        //将map以json格式相应到前台 map -->json / （jackson）
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);
        return false;
    }
}
