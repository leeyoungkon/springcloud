package com.example.demo.repository;


import com.example.demo.entity.Restaurant;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
	List<Restaurant> findByNameContainingIgnoreCase(String name);
}
