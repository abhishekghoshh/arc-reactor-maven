package com.tutorial.repository;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.tutorial.model.User;

@Repository
public class JedisRepository {
	public static final String USER = "USER";
	final Logger logger = LoggerFactory.getLogger(JedisRepository.class);

	private HashOperations<String, String, User> hashOperations;

	public JedisRepository(@Autowired @Qualifier("UserTemplate") RedisTemplate<String, User> redisTemplate) {
		this.hashOperations = redisTemplate.opsForHash();
	}

	public void create(User user) {
		hashOperations.put(USER, user.getUserId(), user);
		logger.info(String.format("User with ID %s saved", user.getUserId()));
	}

	public User get(String userId) {
		return (User) hashOperations.get(USER, userId);
	}

	public Map<String, User> getAll() {
		return hashOperations.entries(USER);
	}

	public void update(User user) {
		hashOperations.put(USER, user.getUserId(), user);
		logger.info(String.format("User with ID %s updated", user.getUserId()));
	}

	public void delete(String userId) {
		hashOperations.delete(USER, userId);
		logger.info(String.format("User with ID %s deleted", userId));
	}
}
