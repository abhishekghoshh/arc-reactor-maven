package com.tutorial.interceptor;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InterceptedRestController {
	private static Map<String, Object> data;
	static {
		data = new HashMap<>();
		data.put("Abhishek", new User("Abhishek Ghosh", 23, "male"));
		data.put("currentTime", Instant.now().toString());
	}

	@GetMapping(path = "/intercepted/call", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Map<String, Object>> getData(HttpServletRequest request) {
		System.out.println(request.getHeader("userid"));
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@GetMapping(path = "/nonintercepted/call", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Map<String, Object>> getAnother() {
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
}
