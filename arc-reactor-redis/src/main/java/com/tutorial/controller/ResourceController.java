package com.tutorial.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tutorial.model.User;
import com.tutorial.repository.JedisModelRepository;
import com.tutorial.repository.JedisRepository;

@RestController
public class ResourceController {
	final Logger logger = LoggerFactory.getLogger(ResourceController.class);
	JedisRepository jedisRepository;

	@Autowired
	JedisModelRepository jedisModelRepository;

	@GetMapping("/users")
	public Object getUsers() {
		return jedisRepository.getAll();
	}

	@GetMapping("/resource")
	public ResponseEntity<Object> getResource(HttpServletRequest request) {
		if (uniqueIdIsPresent(request)) {
			String uniqueId = request.getHeader("uniqueId");
			Object object = jedisModelRepository.get(uniqueId, User.class);
			if (null != object) {
				ResponseEntity<Object> response = new ResponseEntity<>(object, HttpStatus.OK);
				return response;
			} else {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			}
		}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/resource")
	public ResponseEntity<Object> createResource(HttpServletRequest request) {
		String uniqueId = null;
		if (uniqueIdIsPresent(request)) {
			uniqueId = request.getHeader("uniqueId");
			jedisModelRepository.create(uniqueId, new User("100", "Abhishek Ghosh"), User.class);
		} else {
			uniqueId = jedisModelRepository.create(new User("100", "Abhishek Ghosh"), User.class);
		}
		return ResponseEntity.noContent().header("uniqueId", uniqueId).build();
	}

	private boolean uniqueIdIsPresent(HttpServletRequest request) {
		return null != request.getHeader("uniqueId") && !"".equalsIgnoreCase(request.getHeader("uniqueId").trim());
	}
}
