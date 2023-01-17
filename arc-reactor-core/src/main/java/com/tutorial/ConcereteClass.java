package com.tutorial;

import org.springframework.stereotype.Service;

@Service
public class ConcereteClass extends AbstractClass {

	@Override
	public void show() {
		this.message();
	}


}
