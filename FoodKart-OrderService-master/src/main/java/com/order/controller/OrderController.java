package com.order.controller;

import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.Valid;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.order.entity.Order;
import com.order.exceptions.OrderNotFoundException;
import com.order.service.OrderService;
import com.order.utils.CartItem;
import com.order.utils.Revenue;


//@CrossOrigin("*")
@RequestMapping("order-rest")
@RestController
public class OrderController {
	
	@Autowired
	OrderService orderservice;
	
	
    private static final Logger logger = LogManager.getLogger(OrderController.class);

	
	@GetMapping("/getUserOrder/{id}")
	public ResponseEntity<List<Order>> getByUser(@PathVariable long id)
	{
    	logger.info("Fetch all User Order By ID Endpoint");
		List<Order>order= orderservice.getAllOrderOfUser(id);
		
		return new ResponseEntity<List<Order>>(order,HttpStatus.OK);
		
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Order> getByOrder(@PathVariable long id)
	{
    	logger.info("Fetch Order By ID Endpoint");

		return new ResponseEntity<Order>(orderservice.getOrderByOrderId(id),HttpStatus.OK);
	}
	
	
	@GetMapping("/getall")
	public ResponseEntity<List<Order>> getAllOrder()
	{
    	logger.info("Inside All Order");

		return new ResponseEntity<List<Order>>(orderservice.getAllOrders(),HttpStatus.OK);
	}
	
	@PostMapping("/addOrder/{id}")
	public void addOrder( @RequestParam String address,@PathVariable long id)
	{
    
    	
    	logger.info("In Add Order Endpoint");
    	
    	logger.info(address);
    	
    	if(address.equals(""))
    	{
    		throw new OrderNotFoundException("Invalid Address");
    	}
    	

	String url = "http://localhost:8081/cartitem-rest/fetchCart/"+String.valueOf(id);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<CartItem>> response = restTemplate.exchange(
		    url,
		    HttpMethod.GET,
		    null,
		    new ParameterizedTypeReference<List<CartItem>>(){});
		List<CartItem> objects = response.getBody();
		
		
		
		orderservice.addOrder(objects,address);
		
		

		
	}
    
    @GetMapping("/getdailyRevenue")
    public Revenue getRevenue(@RequestParam("date") 
    @DateTimeFormat(pattern = "yyyy-MM-dd") Date date)
    {
    	
    	logger.info("In Daily Revenue Endpoint");

    	return orderservice.getRevenue(date);
    }
    
    @GetMapping("/getRevenueMonthly")
    public Revenue getMonthlyRevenue(@RequestParam("fromdate") 
    @DateTimeFormat(pattern = "yyyy-MM-dd") Date date1,@RequestParam("todate") 
    @DateTimeFormat(pattern = "yyyy-MM-dd") Date date2)
    {
    	logger.info("In Monthly Revenue Endpoint");

    	return orderservice.getRevenueMonthly(date1, date2);
    }
    
    
    
 
	
	


}
