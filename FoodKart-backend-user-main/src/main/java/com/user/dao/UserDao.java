package com.user.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.entity.User;

@Repository
public interface UserDao extends JpaRepository<User,Long>{
     public User findByUsernameAndPassword(String username,String password);
	public User findByUsername(String username);

}
