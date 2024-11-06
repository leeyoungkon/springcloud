package pl.piomin.services.order.messaging;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Service;

import pl.piomin.services.order.model.Customer;

@Service
public class CustomerSender {
	
	@Autowired
    private Source source;

    public boolean send(Customer customer) {
         return this.source.output().send(MessageBuilder.withPayload(customer).build());
    }

}
