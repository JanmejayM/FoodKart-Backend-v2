package com.user.service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.controller.UserController;
import com.user.dao.UserDao;
import com.user.entity.User;
import com.user.exception.UserAlreadyExistsException;
import com.user.exception.UserNotFoundException;



@Service
public class UserServiceImpl implements UserService{
	
	
	@Autowired
	private UserDao userdao;
	
	@Autowired
	private PasswordEncoder bcryptPasswordEncoder;
	
	
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

	
	public User login(User user) throws UserNotFoundException
	{
		
		
		User user1=this.userdao.findByUsername(user.getUsername());
		boolean exist=false;
		if(user1!=null)
		{
		
		 exist=bcryptPasswordEncoder.matches(user.getPassword(),user1.getPassword());
		}
		
		logger.info("In UserService login Method found a user"+user1);
		
		
		if(!exist)
		{
			logger.warn("No user");
			throw new UserNotFoundException("User Not found");
		}
		return user1;
		
		
	}
	
	public User signup(User user) throws UserAlreadyExistsException
	{
		
		
		User user1=userdao.findByUsername(user.getUsername());
		logger.info("In UserService signup Method found a user"+user1);

		
		if(user1!=null)
		{
			logger.warn("User Already Exist");

			throw new UserAlreadyExistsException("User already Exists");
		}
		user.setPassword(bcryptPasswordEncoder.encode(user.getPassword()));
		  

		return this.userdao.save(user);

		
					
	
		
		 
	}
	
	public List<User> fetch()
	{
		logger.info("In UserService FetchAll Method ");

		List<User>ar=new ArrayList<>();
		   userdao.findAll()
		   .forEach(user->ar.add(user));
		   return ar;
	}
	
	
	public User fetchById(long id)throws UserNotFoundException
	{
	   
		List<User>res=new ArrayList<>();
	
			res.add(userdao.findById(id)
					.orElseThrow(()->new UserNotFoundException("User Not Found")));
		
			logger.info("In UserService FetchById Method found a user"+res.get(0));

	 return res.get(0);
	}
	
	public User updateUser(User user)throws UserNotFoundException
	{
		logger.info("In User Service Update");
		
		if(userdao.findById(user.getId()).isPresent()&& userdao.findByUsername(user.getUsername())!=null && userdao.findByUsername(user.getUsername()).getId()==user.getId())
		{
			 boolean change=user.getPassword().equals(userdao.findById(user.getId()).get().getPassword());
			 if(change==false)
			 {
			user.setPassword(bcryptPasswordEncoder.encode(user.getPassword()));
			 }
			
			logger.info("In UserService update Method for a validuser");

			return userdao.save(user);

			
		}
		logger.warn("InValid User");

		throw new UserNotFoundException("Operation cannot be completed");


		
	}

	
	
	

}
