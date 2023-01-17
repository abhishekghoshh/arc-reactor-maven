package com.tutorial;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FileTest {
	public static void main(String args[]) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Map expectedResponse = mapper
				.readValue(new File(System.getProperty("user.dir") + "/src/main/resources/data.json"), Map.class);
		Map actualResponse = mapper
				.readValue(new File(System.getProperty("user.dir") + "/src/main/resources/response.json"), Map.class);
		Long start = System.currentTimeMillis();
		System.out.println(new ObjectMatcher().check(expectedResponse, actualResponse));
		Long end = System.currentTimeMillis();
		System.out.println(end-start);
		
		Object ob1= 1;
		Object ob2 = 1;
		System.out.println(ob1.equals(ob2));
	}

	
}
