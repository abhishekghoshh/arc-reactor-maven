package com.tutorial.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.AbstractClass;
import com.tutorial.interceptor.custom.ValidateAction;
import com.tutorial.model.User;
import com.tutorial.service.impl.WorkFlow;

@RestController
@RequestMapping("/api")
public class TutorialController {
	@Autowired
	AbstractClass abstractClass;

	private TutorialController(@Autowired WorkFlow workflow) {
		System.out.println(workflow.applicationName);
	}

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
		abstractClass.show();
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
}
