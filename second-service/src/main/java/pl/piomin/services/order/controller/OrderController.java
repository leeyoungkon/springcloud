package pl.piomin.services.order.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import pl.piomin.services.order.model.Customer;
import pl.piomin.services.order.repository.CustomerRepository;
import pl.piomin.services.order.service.BookService;

@RestController
public class OrderController {

/*	@GetMapping("/find/{name}")
	public Customer findByName(@PathVariable("name") String name) {
		return new Customer(name, 21); 
	}  */
	@Autowired
	ThirdClient thirdClient; 
	
	@Autowired
	BookService bookService;
	
	@Autowired
	CustomerRepository repository;
	
	@GetMapping("/find/{name}")
	public String findByName(@PathVariable("name") String name) {
		Customer customer = bookService.findByName(name);
		return customer.getName(); 
	}
	@GetMapping("/find")
	public String receiveAll() {
		List<Customer> customers = repository.find(); 
		String temp="";
		for(Customer customer:customers) 
			temp +=(customer.getName()+":"+customer.getAge());
		return temp;
	}
	
}
