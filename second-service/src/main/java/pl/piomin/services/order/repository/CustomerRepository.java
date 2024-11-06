package pl.piomin.services.order.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import pl.piomin.services.order.model.Customer;

public class CustomerRepository {
	private List<Customer> customers = new ArrayList<>();
	
	public Customer add(Customer customer) {
		customers.add(customer);
		return customer;
	}
	
	public List<Customer> find() {
		return customers.stream().collect(Collectors.toList());
	}

}
