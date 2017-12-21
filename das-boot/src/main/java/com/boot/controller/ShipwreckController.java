package com.boot.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boot.model.Shipwreck;
import com.boot.repository.ShipwreckRepository;

@RestController
@RequestMapping("/api/v1/")
public class ShipwreckController {

	// Before JPA, using ShipwreckStub.java
	// Inject Shipwreck Repo by autowiring and defining the interface.
	@Autowired
	private ShipwreckRepository shipwreckRepository;
	
	// RequestMapping allows GET request to an endpoint (api/v1/shipwrecks) from Front-end client
	// (IMPORTANT!!!) If you are using IE Explorer, you cannot run the list() method
	// Function: List all shipwrecks from the repo
	@RequestMapping(value="shipwrecks", method= RequestMethod.GET)
	public List<Shipwreck> list(){
		// Before JPA: return ShipwreckStub.list();
		// The findAll() method query the database for all shipwreck models.
		return shipwreckRepository.findAll();
	}
	
	// Function: Create a shipwreck
	@RequestMapping(value="shipwrecks", method= RequestMethod.POST)
	public Shipwreck create(@RequestBody Shipwreck shipwreck) {
		// Before JPA: return ShipwreckStub.create(shipwreck);
		// The saveAndFlush() method will pass the "shipwreck" object from the browser
		// save it and then return the saved copy.
		return shipwreckRepository.saveAndFlush(shipwreck);
	}
	
	// Function: Create a shipwreck
	@RequestMapping(value="shipwrecks/{id}", method= RequestMethod.GET)
	public Shipwreck get(@PathVariable Long id) {
		// Before JPA: return ShipwreckStub.get(id);
		// The findOne() method will use the shipwreckRepository and find the shipwreck
		// by ID.
		return shipwreckRepository.findOne(id);
	}
		
	// Function: Update a shipwreck
	@RequestMapping(value="shipwrecks/{id}", method= RequestMethod.PUT)
	public Shipwreck update(@PathVariable Long id,@RequestBody Shipwreck shipwreck) {
		// Before JPA: return ShipwreckStub.update(id,shipwreck);
		// In updating a data, we need to pull existing shipwreck and copy the  
		// updated attributes into it and resave the object.
		Shipwreck existingShipwreck = shipwreckRepository.findOne(id);
		BeanUtils.copyProperties(shipwreck, existingShipwreck);
		return shipwreckRepository.saveAndFlush(existingShipwreck);
	}
	
	// Function: Delete a shipwreck
	@RequestMapping(value="shipwrecks/{id}", method= RequestMethod.DELETE)
	public Shipwreck delete(@PathVariable Long id) {
		// Before JPA: return ShipwreckStub.delete(id);
		// To delete a data, we need to look up for the object by id. delete it and
		// return the existing one.
		Shipwreck existingShipwreck = shipwreckRepository.findOne(id);
		shipwreckRepository.delete(existingShipwreck);
		return existingShipwreck;
	}
	
}
