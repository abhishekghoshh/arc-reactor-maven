package com.tutorial.model;

import java.util.UUID;

public class JedisModel implements EnableCaching {

	private String uniqueId;
	private Object payload;
	private String collectionName;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JedisModel() {
		uniqueId = UUID.randomUUID().toString();
	}

	public JedisModel(Object payload) {
		uniqueId = UUID.randomUUID().toString();
		this.payload = payload;
	}

	@Override
	public String uniqueId() {
		return uniqueId;
	}

	public void uniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	@Override
	public String collectionName() {
		return collectionName;
	}

	@Override
	public Object getPayload() {
		return payload;
	}

	public void setPayload(Object payload) {
		this.payload = payload;
	}

}
