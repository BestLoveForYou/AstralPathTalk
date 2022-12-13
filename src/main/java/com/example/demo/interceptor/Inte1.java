package com.example.demo.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Inte1 implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler)
	{
		String id = (String) request.getAttribute("id");
		if (id.isEmpty()) {
			return false;
		} else {
			return true;
		}
		
	}
}
