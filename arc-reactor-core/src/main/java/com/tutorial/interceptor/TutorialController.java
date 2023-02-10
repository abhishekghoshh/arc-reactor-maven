package com.tutorial.interceptor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TutorialController {

	private static Map<String, User> data;
	static {
		data = new HashMap<>();
		data.put("Abhishek", new User("Abhishek Ghosh", 23, "male"));
	}

	@ValidateAction(resource = "Abhishek", release = "Ghosh")
	@GetMapping(path = "/data", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Map<String, User>> getData() {
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	@GetMapping(path = "/test", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Map<String, User>> test() {
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
}
