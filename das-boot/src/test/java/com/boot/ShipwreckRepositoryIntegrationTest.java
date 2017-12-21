package com.boot;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.boot.model.Shipwreck;
import com.boot.repository.ShipwreckRepository;

// Integration Testing - RUN IS EXPENSIVE AND NEED A LOT OF CPU
// This is Integration Testing that use the "SpringJUnit4ClassRunner", which is the part of Spring Test Tools
// After 1.5.1.RELEASE, Spring Boot allow "@SpringBootTest" and decline "@SpringBootConfiguration" annotation.
// "@SpringBootTest" annotation tells Spring Boot how to configure and start the application.
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes= App.class)
public class ShipwreckRepositoryIntegrationTest {

	@Autowired
	private ShipwreckRepository shipwreckRepository;
	
	@Test
	public void testFindAll() {
		List<Shipwreck> wrecks = shipwreckRepository.findAll();
		assertThat(wrecks.size(),is(greaterThanOrEqualTo(0)));
	}
}
