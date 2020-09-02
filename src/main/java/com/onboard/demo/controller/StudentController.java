package com.onboard.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.onboard.demo.model.Student;
import com.onboard.demo.repository.StudentRepository;

@RestController
public class StudentController {
	@Autowired
	private StudentRepository studentRepository;

	
	@GetMapping("/students")
	public Map<String, Student> findAll(){ 		
		return studentRepository.findAll();
		
	}
	
	@GetMapping("/test")
	public ResponseEntity<String> test(){ 		
		return new ResponseEntity<String>("Hello World",
				HttpStatus.OK);
	}
	
	@GetMapping("/students/{id}")
	public Student findById(@PathVariable String id){ 
		return studentRepository.findById(id);
	}
	
	@PostMapping("/students")
	public ResponseEntity<String> createStudent(@RequestBody Student student) {
		try {
			studentRepository.save(student);
			return new ResponseEntity<String>("{\"msg\": \"Se ha agregado correctamente.\"}",
					HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("{\"msg\": \"Hubo un error.\"}",
					HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/students")
	public ResponseEntity<String> updateStudent(@RequestBody Student student) {
		try {
			studentRepository.update(student);
			return new ResponseEntity<String>("{\"msg\": \"Se ha agregado correctamente.\"}",
					HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("{\"msg\": \"Hubo un error.\"}",
					HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/students/{id}")
	public ResponseEntity<String> delteStudent(@PathVariable String id) {
		try {
			studentRepository.delete(id);
			return new ResponseEntity<String>("{\"msg\": \"Estudiante eliminado.\"}",
					HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			return new ResponseEntity<String>("{\"msg\": \"Hubo un error.\"}",
					HttpStatus.BAD_REQUEST);
		}
	}
}
