package pl.piomin.services.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import pl.piomin.services.order.model.Customer;
import pl.piomin.services.order.repository.CustomerRepository;
import pl.piomin.services.order.service.BookService;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableHystrix
@EnableHystrixDashboard
@EnableBinding(Processor.class)
public class OrderApplication {
	
	@Autowired
	BookService service;
	
	public static void main(String[] args) {
		new SpringApplicationBuilder(OrderApplication.class).web(true).run(args);
	}
	
	@StreamListener(Processor.INPUT)
	public void receiveCustomer(Customer customer)  {
		service.receive(customer);
	}
	
	@Bean
	CustomerRepository repository() {
		CustomerRepository repository = new CustomerRepository();
		return repository; 
	}
	
}
