package com.reactor.arc.service.impl;

import static com.reactor.arc.constant.ArcReactorConstant.YAMLSERVICE;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.reactor.arc.service.FileService;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Qualifier(YAMLSERVICE)
public class YamlService extends FileService {

	private static ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
	
	@Override
	public Object initializeFromFile(String path, Class<?> className, String fileName) throws IOException {
		return mapper.readValue(getBufferedReader(path,fileName), className);
	}

}
