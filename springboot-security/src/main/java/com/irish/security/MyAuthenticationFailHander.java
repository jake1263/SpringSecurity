package com.irish.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * 自定义登录失败处理器
 *
 */
@Component("myAuthenticationFailHander")
public class MyAuthenticationFailHander extends SimpleUrlAuthenticationFailureHandler {
	
      @Autowired
      private ObjectMapper objectMapper;
      
      private Logger logger = LoggerFactory.getLogger(getClass());
      
      @Override
      public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                  AuthenticationException exception) throws IOException, ServletException {
            //以Json格式返回
            Map<String,String> map=new HashMap<>();
            map.put("code", "503");
            map.put("msg", "登录失败");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");   
            response.getWriter().write(objectMapper.writeValueAsString(map));
      }
}