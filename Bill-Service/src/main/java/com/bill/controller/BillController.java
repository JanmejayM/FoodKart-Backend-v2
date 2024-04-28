package com.bill.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bill.config.RabbitMQConsumer;


@RequestMapping("bill")
@RestController
public class BillController {
	
	private static final Logger logger = LogManager.getLogger(BillController.class);
	
	@Autowired
	RabbitMQConsumer consumer;
	
	@PostMapping("/generateBill")
	public void generateBill(@RequestParam ("orderid") long id)
	{
		logger.info("inside generate bill");
		
	}

}
