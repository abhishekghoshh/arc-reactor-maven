package com.tutorial.interceptor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@SuppressWarnings("deprecation")
public class IntercepterRegistry extends WebMvcConfigurerAdapter {
	@Autowired
	private ApplicationContext appContext;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

//		registry.addInterceptor(null).addPathPatterns(Arrays.asList("/intercepted/call"));

//		String[] beans = appContext.getBeanDefinitionNames();
//		for (String bean : beans) {
//			System.out.println("Bean name: " + bean);
//			Object object = appContext.getBean(bean);
//			System.out.println("Bean object:" + object);
//		}
		try {
			Class<?> classType = Class.forName("com.tutorial.interceptor.FilteredIntercepter");
			String[] beans = appContext.getBeanNamesForType(classType);
			HandlerInterceptor interceptor = (HandlerInterceptor) appContext.getBean(beans[0]);
			registry.addInterceptor(interceptor).addPathPatterns(Arrays.asList("/intercepted/call"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] arg) {
		Map<String, String> gfg = new HashMap<String, String>();

		// enter name/url pair
		gfg.put("GFG", "geeksforgeeks.org");
		gfg.put("Practice", "practice.geeksforgeeks.org");
		gfg.put("Code", "code.geeksforgeeks.org");
		gfg.put("Quiz", "quiz.geeksforgeeks.org");

		// using iterators
		Iterator<Map.Entry<String, String>> itr = gfg.entrySet().iterator();

		while (itr.hasNext()) {
			Map.Entry<String, String> entry = itr.next();
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
			System.out.println("itr.hasNext() "+itr.hasNext());
			System.out.println();
		}
	}

}
