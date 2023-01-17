package com.tutorial.configuration;

import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.tutorial.model.JedisModel;
import com.tutorial.model.User;

@Configuration
public class JedisConfiguration {
	@Autowired
	JedisConnectionFactory jedisConnectionFactory;

	@Bean
	public JedisConnectionFactory redisConnectionFactory(
			@Autowired JedisConnectionConfiguration jedisConnectionConfiguration) throws UnknownHostException {
//		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("localhost", 6379);
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setHostName(jedisConnectionConfiguration.getHostName());
		redisStandaloneConfiguration.setPort(jedisConnectionConfiguration.getPort());
		redisStandaloneConfiguration.setPassword(jedisConnectionConfiguration.getPassword());
		return new JedisConnectionFactory(redisStandaloneConfiguration);
	}

	@Bean
	@Qualifier("UserTemplate")
	public RedisTemplate<String, User> redisTemplate() {
		RedisTemplate<String, User> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory);
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}

	@Bean
	@Qualifier("JedisModelTemplate")
	public RedisTemplate<String, JedisModel> redisTemplate2() {
		RedisTemplate<String, JedisModel> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory);
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}
}
