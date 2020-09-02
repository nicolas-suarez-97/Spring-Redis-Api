package com.onboard.demo;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.onboard.demo.model.Student;

@SpringBootTest (webEnvironment=WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

	@LocalServerPort
	int port;
	
	Student created;

	@Test
	public void addStudent() throws URISyntaxException  {

		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:"+port+"/students";
        URI uri = new URI(baseUrl);
        
        Student student = new Student(null, "Adam", "test@email.com","3152333322");
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept", "application/json");      
 
        HttpEntity<Student> request = new HttpEntity<>(student, headers);
         
        ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
        System.out.println(result.getBody());
        
        //Verify request succeed
        Assertions.assertEquals(201, result.getStatusCodeValue());

    }

	
	@Test
	public void getAllStudents() throws URISyntaxException  {

		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:"+port+"/students";
        URI uri = new URI(baseUrl);
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept", "application/json"); 
 
        HttpEntity<Student> request = new HttpEntity<>(null, headers);
         
        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class); //postForEntity(uri, request, String.class);
        System.out.println(result.getBody());
        //Verify request succeed
        Assertions.assertEquals(200, result.getStatusCodeValue());
        Assertions.assertNotNull(result.getBody());
    }
	
	
	
	
	

}
