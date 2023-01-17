package com.reactor.arc.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.reactor.arc.configuration.ArcReactorConfiguration;
import com.reactor.arc.configuration.WebConfiguration;



@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import({ArcReactorConfiguration.class,WebConfiguration.class})
@Configuration
public @interface ArcReactor {
}