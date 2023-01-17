package com.reactor.arc.service.impl;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.reactor.arc.service.FileService;

@RunWith(MockitoJUnitRunner.class)
public class FileServiceTest {
	
	FileService jsonService = new JsonService();
	FileService yamlService = new YamlService();
	
	private ReflectionServiceImpl ReflectionServiceImpl = new ReflectionServiceImpl();
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		ReflectionServiceImpl.setField(JsonService.class, jsonService, "mapper", new ObjectMapper(new JsonFactory()));
		ReflectionServiceImpl.setField(YamlService.class, yamlService, "mapper", new ObjectMapper(new YAMLFactory()));
	}

	@Test
	public void initializeFromJsonTest() throws IOException {
		Object obj = jsonService.initializeFromFile("src/main/java/resources", Map.class, "Sample.json");
		System.out.print(obj);
		assertNotNull(obj);
	}
	@Test
	public void initializeFromYamlTest() throws IOException {
		Object obj = yamlService.initializeFromFile("src/main/java/resources", Map.class, "Sample.yml");
		System.out.print(obj);
		assertNotNull(obj);
	}
	
	public void noTest() {
//		ConfigurableApplicationContext context=SpringApplication.run(AnnotationDemoApp.class, args);
//		Object theCoach= context.getBean("tennisCoach",Coach.class);
//		context.close();
	}
}
