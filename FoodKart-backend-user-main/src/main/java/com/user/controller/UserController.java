package com.user.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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

import com.user.entity.User;
import com.user.exception.UserNotFoundException;
import com.user.service.UserService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



//@CrossOrigin("*")
@RestController
@RequestMapping("login-rest")
public class UserController {
	
	@Autowired
	UserService userservice;

    private static final Logger logger = LogManager.getLogger(UserController.class);

	
	@PostMapping("/signup")
	public ResponseEntity<User> signup(@Valid @RequestBody User user)
	{
    	
      logger.info("In signup Controller" +user);
	   User newUser= this.userservice.signup(user);
	   logger.info("After Signup"+newUser);
	   return new ResponseEntity<User>(newUser,HttpStatus.OK);
		
	}

	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody User user)
	{
	      logger.info("In login Controller" +user);

		User userExisting= this.userservice.login(user);
		
		   logger.info("After login"+userExisting);

	    return new ResponseEntity<User>(userExisting,HttpStatus.OK);

		
		
	}
	
    
	@GetMapping("/fetch")
	public ResponseEntity<List<User>>fetch()
	{


		return new ResponseEntity<List<User>>(this.userservice.fetch(),HttpStatus.OK);
	}
    
	
	@GetMapping("/fetch/{id}")
	public ResponseEntity<User>fetchById(@PathVariable long id)
	{
		return new ResponseEntity<User>(userservice.fetchById(id),HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<User>updateUser(@RequestBody User user)
	{
		return new ResponseEntity<User>(userservice.updateUser(user),HttpStatus.OK);
	}
	

}
