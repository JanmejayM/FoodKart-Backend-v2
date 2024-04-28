package com.bill.config;


import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bill.utils.Order;
import com.bill.utils.OrderDetails;

@Service
public class RabbitMQConsumer {
	
	@Autowired
	RestTemplate restTemplate;
	
	
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);

	    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
	    public void consume(String message) throws IOException{
	        LOGGER.info(String.format("Received message -> %s", message));
	       Order order=(Order) restTemplate.getForObject("http://localhost:8083/order-rest/get/"+message,Order.class);
	        LOGGER.info("Order -->"+order);

		
	    }

}
