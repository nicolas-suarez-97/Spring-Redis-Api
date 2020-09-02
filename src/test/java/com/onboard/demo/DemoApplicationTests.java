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
        
        //Verify request succeed
        Assertions.assertEquals(200, result.getStatusCodeValue());

    }
	
	@Test
	public void getStudent() throws URISyntaxException  {

		String id = "d6d0a1ce-f251-4d1a-9f22-80d4003b2e7d";
		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:"+port+"/students/"+id;
        URI uri = new URI(baseUrl);
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept", "application/json"); 
 
        HttpEntity<Student> request = new HttpEntity<>(null, headers);
         
        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class); //postForEntity(uri, request, String.class);
        System.out.println(result.getBody());
        //Verify request succeed
        Assertions.assertEquals(200, result.getStatusCodeValue());
        Assertions.assertEquals(
        		"{"
        		+ "\"id\":\"d6d0a1ce-f251-4d1a-9f22-80d4003b2e7d\","
        		+ "\"name\":\"juan\","
        		+ "\"email\":\"n@gmail.com\","
        		+ "\"phone\":\"3152333333\""
        		+ "}", result.getBody());
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
