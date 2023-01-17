package com.tutorial.service.impl;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tutorial.service.IWorkFlow;

@Service
public class WorkFlow extends IWorkFlow {
	@Value("${spring.application.name:Default}")
	public String applicationName;
	private WorkFlow() {
		System.out.println("I am constructor of WorkFLow");

		String id = (int) (10000 * Math.random()) + "";

	}

	public ResponseEntity<Object> returnCreatedLoaction(String id) {
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.created(location).build();
	}
}
