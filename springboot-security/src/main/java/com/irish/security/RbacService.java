package com.irish.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

/**
 * 
 *基于RBAC的权限控制
 *
 */
@Component("rbacService")
public class RbacService   {
	
      private AntPathMatcher antPathMatcher = new AntPathMatcher();
      
      public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
    	    //取出request
    	  System.out.println(" URI : "+request.getRequestURI());
    	  System.out.println(" URL : "+request.getRequestURL().toString());
            Object principal = authentication.getPrincipal();
            boolean hasPermission = false;
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for(GrantedAuthority authority : authorities) {
//            	if(authority.getAuthority().equalsIgnoreCase(anotherString)) {
//            		
//            	}
            }
            return hasPermission;
      }
}