package com.goshine.web.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class TimeInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("after handler");
		Long start = (Long) arg0.getAttribute("startTime"); 
		System.out.println("post handler 耗时 ： " + (new Date().getTime() - start));
		System.out.println("ex is :" + arg2);
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		System.out.println("post handler");
		Long start = (Long) arg0.getAttribute("startTime"); 
		System.out.println("post handler 耗时 ： " + (new Date().getTime() - start));
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		System.out.println("pre handler");
		arg0.setAttribute("startTime", new Date().getTime());
		return true;
	}

}
