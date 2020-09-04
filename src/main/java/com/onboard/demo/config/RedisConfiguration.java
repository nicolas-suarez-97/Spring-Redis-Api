package com.onboard.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

import com.onboard.demo.model.Student;


@Configuration
@ComponentScan("com.onboard.demo")
public class RedisConfiguration {
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		//return new JedisConnectionFactory();
		/*JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName("http://3.92.28.19:6379");
        //jedisConnectionFactory.setPort(6379);
        jedisConnectionFactory.afterPropertiesSet();
        return jedisConnectionFactory;*/
        
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("3.92.28.19", 6379);
        //redisStandaloneConfiguration.setPassword(RedisPassword.of("yourRedisPasswordIfAny"));
        return new JedisConnectionFactory(redisStandaloneConfiguration);
	}
	
	@Bean
	public RedisTemplate<String, Student> redisTemplate() {
		final RedisTemplate<String, Student> redisTemplate = new RedisTemplate<String, Student>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		redisTemplate.setValueSerializer(new GenericToStringSerializer<Student>(Student.class));
		return redisTemplate;
	}
}
