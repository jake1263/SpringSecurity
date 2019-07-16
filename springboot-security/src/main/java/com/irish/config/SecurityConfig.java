package com.irish.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationProvider provider;

	@Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;
	
    @Autowired
    private AuthenticationFailureHandler myAuthenticationFailHander;
	
	//注入我们自己的AuthenticationProvider
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		   http 
		   //表示表单登录的url是/login,表单提交的url是/login/form
		   //myAuthenticationSuccessHandler   登录成功处理器
		   //myAuthenticationFailHander   登录失败处理器
          .formLogin().loginPage("/login").loginProcessingUrl("/login/form")
          .successHandler(myAuthenticationSuccessHandler)
          .failureHandler(myAuthenticationFailHander)
          .permitAll() 
          //permitAll()表示允许访问/login,/login/form
          .and()
          //设置授权请求，任何请求都要经过下面的权限表达式处理
          .authorizeRequests()
          .anyRequest().access("@rbacService.hasPermission(request,authentication)") //权限表达式          
          .and()
          //在Security的默认拦截器里，默认会开启CSRF处理，判断请求是否携带了token，如果没有就拒绝访问,所以这里要关闭CSRF处理
          .csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		  auth.authenticationProvider(provider);
	}
	
}