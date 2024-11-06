package pl.piomin.services.order.controller;

import org.springframework.cloud.netflix.feign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import pl.piomin.services.order.model.Customer;

@FeignClient(name = "third-service")
public interface ThirdClient {
	
	@GetMapping("/view/{name}")
	Customer findByName(@PathVariable("name") String name);
}
