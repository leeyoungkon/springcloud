package com.example.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
@RequestMapping("/producer1")
public class Producer1Controller {

	@GetMapping(value = "/data", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getData() {
        return Flux.interval(Duration.ofSeconds(10)) // 10초마다 데이터 생성
                   .map(i -> "Producer1 Data " + i)
                   .take(6) // 총 6번(60초 동안) 실행
                   .doOnSubscribe(sub -> System.out.println("Producer1 started..."));
    }
}