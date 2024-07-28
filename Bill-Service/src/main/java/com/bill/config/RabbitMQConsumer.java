package com.bill.config;

import java.io.IOException;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bill.service.EmailSenderService;
import com.bill.utils.Order;
import com.bill.utils.PDFGenerate;
import com.bill.utils.User;
import com.itextpdf.text.DocumentException;

@Service
public class RabbitMQConsumer {

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private PDFGenerate pdfGenerate;
	
	@Value("${gatewayHost}")
	private String gatewayHost;
	
	@Value("${file.location}")
	private String fileLocation;


	@Autowired
	EmailSenderService emailSenderService;

	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQConsumer.class);

	@RabbitListener(queues = { "${rabbitmq.queue.name}" })
	public void consume(Order order) throws IOException, DocumentException, MessagingException {
		LOGGER.info(String.format("Received message -> %s", order));

	
				pdfGenerate.generate(order,fileLocation);
		User user = restTemplate
				.getForEntity(gatewayHost+"/login-rest/fetch/" + String.valueOf(order.getUserid()), User.class)
				.getBody();
		LOGGER.info(String.format("User -> %s", user));
		String filePath=fileLocation;
		String orderId = String.valueOf(order.getId());
		String subject = "FoodKart Bill Order Number : " + orderId;
		String message = "Hi " + user.getFirstname() + " " + user.getLastname() + ",\n"
				+ "Please find your Bill for Order Number : " + orderId + "\n \n"
				+ "Thank you for ordering from FoodKart.";
		emailSenderService.sendTextWithAttachment(user.getUsername(), subject, message, filePath, orderId);

	}

}
