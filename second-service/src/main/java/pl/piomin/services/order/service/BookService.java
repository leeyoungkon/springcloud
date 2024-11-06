package pl.piomin.services.order.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import pl.piomin.services.order.controller.ThirdClient;
import pl.piomin.services.order.model.Customer;
import pl.piomin.services.order.repository.CustomerRepository;

@Service
public class BookService {
	
	@Autowired
	ThirdClient thirdClient;
	
	@Autowired
	CustomerRepository repository;
	
	@HystrixCommand(commandKey = "third-service.findByName", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "30"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000")
		})
	public Customer findByName(String name) {
		return thirdClient.findByName(name); 
	}
	public void receive(Customer customer) {
		repository.add(customer);
	}

}
