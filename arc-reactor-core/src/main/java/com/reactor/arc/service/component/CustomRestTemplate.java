package com.reactor.arc.service.component;

import javax.net.ssl.SSLContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class CustomRestTemplate {
	private final Logger log = LoggerFactory.getLogger(this.getClass().getName());
	private RestTemplate customRestTemplate = new RestTemplate();
	
	@SuppressWarnings("unused")
	private CustomRestTemplate() {
		if(log.isDebugEnabled()) {
			log.debug("this is a private constructor");
		}
	}
	public CustomRestTemplate(CustomRestTemplateUtils customRestTemplateUtils, SSLContext cloudContext) {
		HttpComponentsClientHttpRequestFactory requestFactory = getHttpComponentsClientRequestFactory(customRestTemplateUtils);
		customRestTemplate.setRequestFactory(requestFactory);
	}

	private HttpComponentsClientHttpRequestFactory getHttpComponentsClientRequestFactory(
			CustomRestTemplateUtils customRestTemplateUtils) {
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setReadTimeout(Integer.parseInt(customRestTemplateUtils.getReadTimeout()));
		requestFactory.setConnectTimeout(Integer.parseInt(customRestTemplateUtils.getConnectionTimeout()));
		return requestFactory;
	}

	public RestTemplate getCustomRestTemplate() {
		return customRestTemplate;
	}

}
