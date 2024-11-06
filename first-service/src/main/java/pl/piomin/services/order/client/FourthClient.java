package pl.piomin.services.order.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.piomin.services.order.model.Customer;

@FeignClient(name = "fourth-service", url="http://133.186.135.68:30009")
public interface FourthClient {

	@RequestMapping(value="/name/{name}", method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String getMapName(@PathVariable("name") String name) ;
}
