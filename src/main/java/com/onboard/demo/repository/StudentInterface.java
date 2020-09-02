package com.onboard.demo.repository;

import java.util.Map;

import com.onboard.demo.model.Student;

public interface StudentInterface {
	Map<String, Student> findAll();
	Student findById(String id);
	String save(Student student);
	void update(Student student);
	void delete(String id);
}
