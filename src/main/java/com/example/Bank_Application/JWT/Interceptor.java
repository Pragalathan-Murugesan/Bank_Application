package com.example.Bank_Application.JWT;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class Interceptor implements HandlerInterceptor {
    @Autowired
    private JwtTokens jwtTokens;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
      String jwtToken=null;
     String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer")) {
            jwtToken = token.substring(7, token.length());
        }
        if (!(request.getRequestURI().contains("/bankapi/create")
                || request.getRequestURI().contains("/bankapi/login"))){
            Claims claims=this.jwtTokens.verifyToken(jwtToken);
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}