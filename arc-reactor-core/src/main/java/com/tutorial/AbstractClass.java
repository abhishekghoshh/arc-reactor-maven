package com.tutorial;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractClass {
	@Autowired
	private MessageService messageService;

	public abstract void show();

	public void message() {
		messageService.message();
	}
}
