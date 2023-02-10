package com.tutorial.interceptor;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectClass {

  @Around("@annotation(com.tutorial.interceptor.custom.ValidateAction)")
  public Object validateAspect(ProceedingJoinPoint pjp) throws Throwable {
    MethodSignature signature = (MethodSignature) pjp.getSignature();
    Method method = signature.getMethod();

    ValidateAction validateAction = method.getAnnotation(ValidateAction.class);
    String release = validateAction.release();
    String resource = validateAction.resource();
    System.out.println(release);
    System.out.println(resource);
    System.out.println("");
    return null;
    // Call your Authorization server and check if all is good

  }
}