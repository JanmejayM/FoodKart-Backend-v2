package com.user.service;

import java.util.List;

import com.user.entity.User;


public interface UserService {
	
	public User signup(User user);
	public List<User>fetch();
	public User login(User user);
	public User fetchById(long id);
	public User updateUser(User user);




	

}
