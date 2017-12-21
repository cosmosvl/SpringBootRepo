package com.boot;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

// Integration Testing - RUN IS EXPENSIVE AND NEED A LOT OF CPU
// Web Integration Testing
//After 1.5.1.RELEASE, Spring Boot allow "@SpringBootTest" and decline "@WebIntegrationTest" annotation.
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=App.class, webEnvironment=WebEnvironment.DEFINED_PORT)
@ConfigurationProperties(prefix="WebTesting")
public class ShipwreckControllerWebIntegrationTest {
	
	@Autowired
	private String url;
	
	@Test
	public void testListAll() throws IOException{
		// Create RestTemplate and call Shipwreck API and holds the response
		// By using SpringBootTest WebEnvironment, we do not have to use "TestRestTemplate" class in RestTemplate
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity(url,String.class);
		
		// Run Assertion that the application get response back (200 - OK).
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		
		// Convert the response into JSON-Object.
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode responseJson = objectMapper.readTree(response.getBody());
		
		// Perform Assertion to make sure that it does not contain null.
		assertThat(responseJson.isMissingNode(), is(false));
		assertThat(responseJson.toString(), equalTo("[]"));
	}
	
}
