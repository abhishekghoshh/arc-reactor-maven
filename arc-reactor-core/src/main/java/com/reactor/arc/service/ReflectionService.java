package com.reactor.arc.service;

public interface ReflectionService {
	void setField(Class<?> className,Object object, String fieldName,Object fieldValue);
}
