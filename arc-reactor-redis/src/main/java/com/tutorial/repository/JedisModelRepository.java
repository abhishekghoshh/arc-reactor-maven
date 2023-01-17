package com.tutorial.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tutorial.model.JedisModel;

@Repository
public class JedisModelRepository {
	final Logger logger = LoggerFactory.getLogger(JedisModelRepository.class);
	private HashOperations<String, String, JedisModel> hashOperations;
	ObjectMapper objectMapper = new ObjectMapper();

	public JedisModelRepository(
			@Autowired @Qualifier("JedisModelTemplate") RedisTemplate<String, JedisModel> redisTemplate) {
		this.hashOperations = redisTemplate.opsForHash();
	}

	public String create(Object payload, Class<?> className) {
		JedisModel jedisModel = new JedisModel(payload);
		hashOperations.put(className.getCanonicalName().toUpperCase(), jedisModel.uniqueId(), jedisModel);
		return jedisModel.uniqueId();
	}

	public String create(String uniqueId, Object payload, Class<?> className) {
		JedisModel jedisModel = new JedisModel(payload);
		hashOperations.put(className.getCanonicalName().toUpperCase(), jedisModel.uniqueId(), jedisModel);
		return jedisModel.uniqueId();
	}

	public <T> T get(String uniqueId, Class<T> className) {
		JedisModel jedisModel = (JedisModel) hashOperations.get(className.getCanonicalName().toUpperCase(), uniqueId);
		logger.error("collectionName {}", className.getCanonicalName().toUpperCase());
		if (null != jedisModel && null != jedisModel.getPayload()) {
			try {
				String jsonString = objectMapper.writeValueAsString(jedisModel.getPayload());
				logger.error("Payload is {}", jsonString);
				return (T) objectMapper.readValue(jsonString, className);
			} catch (Exception ex) {
				logger.error("Can not convert to {}", className.getSimpleName());
			}
		}
		return null;
	}
}
