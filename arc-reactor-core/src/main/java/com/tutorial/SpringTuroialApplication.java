package com.tutorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringTuroialApplication implements CommandLineRunner {
	@Autowired
	private ApplicationContext appContext;

	public static void main(String[] args) {
		SpringApplication.run(SpringTuroialApplication.class, args);
	}

	@Override
	public void run(String... strings) throws ClassNotFoundException {
		
//		String[] beans = appContext.getBeanDefinitionNames();
		Class<?> classType = Class.forName("org.springframework.boot.web.servlet.FilterRegistrationBean");
		String[] beans = appContext.getBeanNamesForType(classType);
		for (String bean : beans) {
			System.out.println("Bean name: " + bean);
			Object object = appContext.getBean(bean);
			System.out.println("Bean object:" + object);
		}
	}
}
