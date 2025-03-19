package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/producer2")
public class Producer2Controller {

	   @GetMapping("/data")
	    public Mono<String> getData() {
	        return Mono.just("Producer2 Data <br>")
	                   .doOnSubscribe(sub -> System.out.println("Producer2 started..."));
	    }
}