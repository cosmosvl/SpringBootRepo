package com.boot;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import com.boot.controller.ShipwreckController;
import com.boot.model.Shipwreck;
import com.boot.repository.ShipwreckRepository;

public class ShipwreckControllerTest {

	/*
	 * Goal of this test:
	 * Ask for the shipwreck of Id1 and get that shipwreck back from the controller.
	 * Using Mockito framework, it allows us to test our controller code as an actual
	 * unit test. We also do not have to wired up the test with the database or Spring.
	 * 
	 * */
	
	@InjectMocks
	private ShipwreckController sc;
	
	@Mock
	private ShipwreckRepository shipwreckRepository;
	
	// Setup Method to initialiye all of the mocked objects together when the test runs.
	// This init() method will run initially in the test.
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	// The when() method call allows us to provide the actual mocking behavior.
	// It tells Mockito that when the findOne() method is called on the repository,
	// it should return the stubbed Shipwreck Object.
	@Test
	public void testShipwreckGet() {
		Shipwreck sw = new Shipwreck();
		sw.setId(1l);
		when(shipwreckRepository.findOne(1l)).thenReturn(sw);
		
		Shipwreck wreck = sc.get(1L);
		
		verify(shipwreckRepository).findOne(1l);
		
		// Before Hamcrest: assertEquals(1L,wreck.getId().longValue());
		// Hamcrest provide many option and matchers for testing in the documentation.
		assertThat(wreck.getId(),is(1l));
	}
}
