package com.irish.security;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * 
 * 自定义认证Provider
 *
 */
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
      /**
       * 注入我们自己定义的用户信息获取对象，MyUserDetailsService
       */
      @Autowired
      private UserDetailsService userDetailService;
      
      //这里就是执行认证的逻辑
      @Override
      public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    	     logger.info("^^^^^^^^^^^ 自定义authenticationProvider start ^^^^^^^^^^^");
            //从前台获取到用户名和密码和数据库中的比较
            String userName = (String) authentication.getPrincipal();// 相当于账号
            String password = (String) authentication.getCredentials();//相当于密码
            //这里构建来判断用户是否存在和密码是否正确
            UserInfo userInfo = (UserInfo) userDetailService.loadUserByUsername(userName); // 这里调用我们的自己写的获取用户的方法；
            if (userInfo == null) {
                  throw new BadCredentialsException("用户名不存在");
            }
            if (!userInfo.getPassword().equals(password)) {
                  throw new BadCredentialsException("密码不正确");
            }
            //将用户的权限取出来
            Collection<? extends GrantedAuthority> authorities = userInfo.getAuthorities();
            logger.info("^^^^^^^^^^^ 自定义authenticationProvider end ^^^^^^^^^^^");
            return new UsernamePasswordAuthenticationToken(userName, password, authorities);
      }
      
      @Override
      public boolean supports(Class<?> authentication) {
    	  
            return true;
      }
}