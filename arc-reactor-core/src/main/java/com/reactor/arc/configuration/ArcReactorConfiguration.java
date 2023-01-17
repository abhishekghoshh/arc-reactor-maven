package com.reactor.arc.configuration;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.task.TaskExecutor;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.reactor.arc.annotation.ArcReactor;

@Configuration
@ComponentScan(basePackages = { "com.reactor.arc.service" })
public class ArcReactorConfiguration implements ImportAware {

	private final Logger log = LoggerFactory.getLogger(ArcReactorConfiguration.class.getName());

	@Override
	public void setImportMetadata(AnnotationMetadata importMetadata) {
		Map<String, Object> values = importMetadata.getAnnotationAttributes(ArcReactor.class.getName());
		if (log.isDebugEnabled()) {
			log.debug("values are {}", values);
		}
	}
	 @Bean("threadPoolTaskExecutor")
	 public TaskExecutor getAsyncExecutor() {
		 ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		 executor.setCorePoolSize(20);
		 executor.setMaxPoolSize(500);
		 executor.setWaitForTasksToCompleteOnShutdown(true);
		 executor.setThreadNamePrefix("Arc-");
		 return executor;
	  }

}
