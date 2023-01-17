package com.tutorial.interceptor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Configuration
@Order(1)
public class FilterResgistry implements Filter {
	private static final Map<String, OneDlpFilter> filterResgistryMap = new HashMap<>();

	FilterResgistry(@Autowired ApplicationContext appContext) throws ClassNotFoundException {
		Class<?> classType = Class.forName("com.tutorial.interceptor.FilteredIntercepter");
		String[] beans = appContext.getBeanNamesForType(classType);
		OneDlpFilter oneDlpFilter = (OneDlpFilter) appContext.getBean(beans[0]);
		filterResgistryMap.put("/intercepted/call", oneDlpFilter);
	}

	@Bean
	public FilterRegistrationBean<FilterResgistry> loggingFilter(@Autowired FilterResgistry filterResgistry) {
		FilterRegistrationBean<FilterResgistry> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(filterResgistry);
		registrationBean.addUrlPatterns("*");
		return registrationBean;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (filterResgistryMap.containsKey(((HttpServletRequest) request).getRequestURI())) {
			filterResgistryMap.get(((HttpServletRequest) request).getRequestURI()).doFilter(request, response, chain);
		}else {
			chain.doFilter(request, response);
		}
	}
}
