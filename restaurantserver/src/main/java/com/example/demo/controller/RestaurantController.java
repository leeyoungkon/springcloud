package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Restaurant;
import com.example.demo.repository.RestaurantRepository;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

	   @Autowired
	    private RestaurantRepository restaurantRepository;

	    // 전체 목록 조회
	    @GetMapping
	    public List<Restaurant> getAll() {
	        return restaurantRepository.findAll();
	    }

	    // ID로 단건 조회
	    @GetMapping("/{id}")
	    public ResponseEntity<Restaurant> getById(@PathVariable Long id) {
	        return restaurantRepository.findById(id)
	                .map(ResponseEntity::ok)
	                .orElse(ResponseEntity.notFound().build());
	    }

	    // 이름 검색 (부분 일치)
	    @GetMapping("/search")
	    public List<Restaurant> searchByName(@RequestParam String name) {
	        return restaurantRepository.findByNameContainingIgnoreCase(name);
	    }

	    // 등록
	    @PostMapping
	    public Restaurant create(@RequestBody Restaurant restaurant) {
	        return restaurantRepository.save(restaurant);
	    }

	    // 수정
	    @PutMapping("/{id}")
	    public ResponseEntity<Restaurant> update(@PathVariable Long id, @RequestBody Restaurant newData) {
	        return restaurantRepository.findById(id)
	                .map(restaurant -> {
	                    restaurant.setName(newData.getName());
	                    restaurant.setLatitude(newData.getLatitude());
	                    restaurant.setLongitude(newData.getLongitude());
	                    restaurant.setFeatures(newData.getFeatures());
	                    restaurant.setImage(newData.getImage());
	                    return ResponseEntity.ok(restaurantRepository.save(restaurant));
	                })
	                .orElse(ResponseEntity.notFound().build());
	    }

	    // 삭제
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> delete(@PathVariable Long id) {
	        return restaurantRepository.findById(id)
	                .<ResponseEntity<Void>>map(restaurant -> {
	                    restaurantRepository.delete(restaurant);
	                    return ResponseEntity.noContent().build();
	                })
	                .orElse(ResponseEntity.notFound().build());
	    }
	
}
