package com.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.model.Shipwreck;

// Import the model into this interface
public interface ShipwreckRepository extends JpaRepository<Shipwreck, Long>{

}
