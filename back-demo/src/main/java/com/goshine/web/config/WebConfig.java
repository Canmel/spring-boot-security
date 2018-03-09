package com.goshine.web.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.goshine.web.filter.TimeFilter;
import com.goshine.web.interceptor.TimeInterceptor;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private TimeInterceptor timeInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(timeInterceptor);
	}

	@Bean
	public FilterRegistrationBean timeFilter() {
		FilterRegistrationBean registBean = new FilterRegistrationBean();
		TimeFilter timeFilter = new TimeFilter();
		registBean.setFilter(timeFilter);
		List<String> urls = new ArrayList<>();
		urls.add("/*");
		registBean.setUrlPatterns(urls);
		return registBean;
	}
}
