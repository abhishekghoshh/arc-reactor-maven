package com.reactor.arc.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.reactor.arc.service.component.ServiceInterceptor;

@Configuration
@ComponentScan(basePackages = { "com.reactor.arc.service" })
public class WebConfiguration implements WebMvcConfigurer{
//	
//	CustomRestTemplateUtils customRestTemplateUtils;
//	SSLContext cloudContext;
	@Autowired
	ServiceInterceptor serviceInterceptor;
	
//	@Bean(CUSTOMRESTTEMPLATE)
//	public RestTemplate getCustomRestTemplate() {
//		return new CustomRestTemplate(customRestTemplateUtils,cloudContext).getCustomRestTemplate();
//	}

	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		WebRequestInterceptor interceptor;
//		registry.addWebRequestInterceptor(interceptor);
		registry.addInterceptor(serviceInterceptor);
//		registry.addInterceptor(productServiceInterceptor).addPathPatterns("path1","path2");
	}

}
