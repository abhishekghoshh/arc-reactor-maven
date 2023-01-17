package com.tutorial.interceptor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class FilteredIntercepter implements HandlerInterceptor, OneDlpFilter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("Pre Handle method is Calling for FilteredIntercepter");
		return true;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("I am executed for " + ((HttpServletRequest) request).getRequestURI());
		String uri = ((HttpServletRequest) request).getRequestURI();

		String httpMethod = ((HttpServletRequest) request).getMethod();
		Map<String, String> extraHeaderMap = new HashMap<>();
		extraHeaderMap.put("USERID", "100997");
		final HttpServletRequestWrapper httpServletRequestWrapper = new HttpServletRequestWrapper(
				(HttpServletRequest) request) {
			@Override
			public String getHeader(String name) {
				if (null == name || "".equals(name.trim())) {
					return null;
				}
				if (extraHeaderMap.containsKey(name.toUpperCase())) {
					return extraHeaderMap.get(name.toUpperCase());
				}
				return super.getHeader(name);
			}
		};
		chain.doFilter(httpServletRequestWrapper, response);
	}
}
