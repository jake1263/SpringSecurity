package com.irish.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {

	@RequestMapping("/login")
	public String userLogin() {
		return "demo_sign";
	}

	@RequestMapping("/login-error")
	public String loginError() {
		return "login_error";
	}

	@ResponseBody
	@RequestMapping("/hello")
	public String helloWorld() {
		return "spring security hello world";
	}
	
	@ResponseBody
	@RequestMapping("/whoim")
    public Object whoIm(){
         return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
