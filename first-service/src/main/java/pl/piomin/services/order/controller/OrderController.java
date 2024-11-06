package pl.piomin.services.order.controller;


import org.springframework.beans.factory.annotation.Autowired;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pl.piomin.services.order.client.FourthClient;
import pl.piomin.services.order.model.Customer;

@RestController
public class OrderController {
		

	@Autowired
	FourthClient fourthClient;
	
	@RequestMapping(value="/name/{name}", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String findByName(@PathVariable("name") String name) {
		return fourthClient.getMapName(name); 
	}
	@GetMapping("/find/{name}")
	public String findString(@PathVariable("name") String name) {
		return name; 
	}
	
/*	@PostMapping("/find")
	public boolean sendCustomer(@RequestBody Customer customer) {
		return bookService.;
	}   */
		
}
