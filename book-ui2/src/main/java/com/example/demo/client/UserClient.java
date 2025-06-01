package com.example.demo.client;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.entity.User;

@FeignClient(name = "user-api", url = "http://sixth-service:8094/api/users")
public interface UserClient {
	
	@GetMapping
    public List<User> getAllUsers() ;

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) ;

    @PostMapping
    public User createUser(@RequestBody User user) ;

    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User userDetails) ;

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) ;
}

