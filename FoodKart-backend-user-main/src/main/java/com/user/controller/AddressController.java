package com.user.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.user.entity.Address;
import com.user.entity.User;
import com.user.exception.UserNotFoundException;
import com.user.service.AddressService;
import com.user.utils.AddressOnly;

//@CrossOrigin("*")
@RestController
@RequestMapping("address-rest")
public class AddressController {
	
	@Autowired
	AddressService addressservice;
	

    private static final Logger logger = LogManager.getLogger(AddressController.class);

	
	@PutMapping("/update")
	public ResponseEntity<Address> update(@Valid @RequestBody Address address)
	{
		logger.info("In Address Controller Update endpoint");
		return new ResponseEntity<Address>(addressservice.updateAddress(address),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable long id)
	{
		logger.info("In Address Controller delete endpoint");

		addressservice.deleteAddress(id);
	}
	
	@PostMapping("/add/{id}")
	public ResponseEntity<Address> add(@PathVariable long id,@Valid @RequestBody AddressOnly address)
	{
		
		
			 //return new ResponseEntity(addressservice.addToUser(id, address),HttpStatus.OK);
		logger.info("In Address Controller add Address to user endpoint");

		Address saved=addressservice.addToUser(id, address);
	
		return new ResponseEntity<Address>(saved,HttpStatus.OK);
		
    
		
	}
	
	@GetMapping("/fetch/{id}")
	public ResponseEntity<List<Address>>getByUser(@PathVariable long id)
	{
		
		logger.info("In Address Controller Get All User Address endpoint");

		List<Address>address=addressservice.fetchByUser(id);
		
		return new ResponseEntity<List<Address>>(address,HttpStatus.OK);
	}
	
		
	
	

}
