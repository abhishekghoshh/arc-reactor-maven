package com.reactor.arc.service.component;

public class CustomRestTemplateUtils {
	private String readTimeout;
	private String connectionTimeout;
	public String getReadTimeout() {
		return readTimeout;
	}
	public void setReadTimeout(String readTimeout) {
		this.readTimeout = readTimeout;
	}
	public String getConnectionTimeout() {
		return connectionTimeout;
	}
	public void setConnectionTimeout(String connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}
	
}
