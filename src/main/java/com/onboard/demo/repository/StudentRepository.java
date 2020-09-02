package com.onboard.demo.repository;

import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.onboard.demo.model.Student;

import javax.annotation.PostConstruct;

@Repository
public class StudentRepository implements StudentInterface{
	
	private static final String KEY = "Student";

	private RedisTemplate<String,Student> redisTemplate;
	private HashOperations<String, String, Student> hashOperations;
	
	@Autowired
	public StudentRepository(RedisTemplate<String, Student> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	@PostConstruct
	private void init() {
		hashOperations = redisTemplate.opsForHash();
	}
	
	
	@Override
	public Map<String, Student> findAll() {
		return hashOperations.entries(KEY);
	}

	@Override
	public Student findById(String id) {
		return (Student) hashOperations.get(KEY, id);
	}

	@Override
	public String save(Student student) {
		student.setId(UUID.randomUUID().toString());
		hashOperations.put(KEY, student.getId(), student);
		return student.getId();
	}

	@Override
	public void update(Student student) {
		hashOperations.put(KEY,student.getId(), student);
	}

	@Override
	public void delete(String id) {
		hashOperations.delete(KEY, id);		
	}


}
