package com.example.demo.controller;


import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/consumer")
@RequiredArgsConstructor
public class ConsumerController {

    private final WebClient webClient;

    @GetMapping(value = "/data", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> fetchData() {
        // producer1: 1분 동안 데이터를 스트리밍
        Flux<String> producer1Data = webClient.get()
                .uri("http://producer-service:8081/producer1/data")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .retrieve()
                .bodyToFlux(String.class)
                .map(data -> "Producer1: " + data);

        // producer2: 즉시 데이터를 반환
        Mono<String> producer2Data = webClient.get()
                .uri("http://producer2-service:8082/producer2/data")
                .accept(MediaType.TEXT_EVENT_STREAM)
                .retrieve()
                .bodyToMono(String.class)
                .map(data -> "Producer2: " + data);

        // Mono 데이터를 Flux로 변환 후 병합
        return Flux.concat(producer2Data, producer1Data);
    }
}

