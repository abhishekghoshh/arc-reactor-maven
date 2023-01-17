package com.reactor.arc.service.component;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class ServiceInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println(request);
		System.out.println("Pre Handle method is Calling ");
		System.out.println("\nAttributes of request object");
		System.out.println(request.getLocalPort());
		System.out.println(request.getMethod());
		System.out.println(request.getRequestURI());
		System.out.println(request.getServerName());
		System.out.println(request.getRemoteHost());
		Enumeration<String> it = request.getParameterNames();
		while (it.hasMoreElements()) {
			System.out.println(it.nextElement());
		}
		System.out.println("\nAttributes of session object");
		HttpSession session = request.getSession();
		System.out.println(session.getCreationTime());
		System.out.println(session.getId());
		System.out.println(session.getLastAccessedTime());
		System.out.println(session.getMaxInactiveInterval());
		System.out.println(session.isNew());
		Enumeration<String> it1 = session.getAttributeNames();
		while (it1.hasMoreElements()) {
			System.out.println(it1.nextElement());
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("Post Handle method is Calling");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) throws Exception {
		System.out.println("Request and Response is completed");
	}
}
