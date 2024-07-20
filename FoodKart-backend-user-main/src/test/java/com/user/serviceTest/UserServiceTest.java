package com.user.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.user.dao.UserDao;
import com.user.entity.User;
import com.user.exception.UserAlreadyExistsException;
import com.user.exception.UserNotFoundException;
import com.user.service.UserServiceImpl;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class UserServiceTest {
	
	
	@InjectMocks
	private UserServiceImpl userservice;
	
	
	@Mock
	private UserDao userdao;
	
	
	@Mock
	private PasswordEncoder pswd;

	@Test
	public void signupNewAccount() {
		User user=new User();
		user.setId(1);
		user.setFirstname("janmejay");
		user.setLastname("mohapatra");
		user.setPhone("979688787686");
		user.setPassword("12345");
		user.setRole("user");
		user.setUsername("tester@gmail.com");
		
		when(userdao.findByUsername(user.getUsername())).thenReturn(null);
		//System.out.println(user.getFirstname());
		userservice.signup(user);
		verify(userdao,times(1)).save(user);

	}
	
	
	@Test
	public void signupExistingAccount() {
	
	                User user = new User();
	                user.setId(1);
	                user.setFirstname("janmejay");
	    			user.setLastname("mohapatra");
	    			user.setPhone("979688787686");
	    			user.setPassword("12345");
	    			user.setRole("user");
	    			user.setUsername("tester@gmail.com");
	    			when(userdao.findByUsername(user.getUsername())).thenReturn(user);
	    			//System.out.println(user.getFirstname());
	    			
	    			
	    			Assertions.assertThrows(UserAlreadyExistsException.class, () -> {
	    	            userservice.signup(user);
	    	        });
	    			verify(userdao,times(0)).save(user);
		    		
		    		
	}
	    			
                  	    		
		

	
	
	@Test
	public void loginAccountPresent() {
		User user=new User();

		user.setId(1);
		user.setFirstname("janmejay");
		user.setLastname("mohapatra");
		user.setPhone("979688787686");
		user.setPassword("12345");
		user.setRole("user");
		user.setUsername("tester@gmail.com");
		
        when(userdao.findByUsername(user.getUsername())).thenReturn(user);
        when(pswd.matches(user.getPassword(), user.getPassword())).thenReturn(true);
        
        User log=userservice.login(user);
        Assertions.assertEquals(user, log);

		
	}
	
	@Test
	public void loginAccountNotPresent() {
		User user=new User();

		user.setId(1);
		user.setFirstname("janmejay");
		user.setLastname("mohapatra");
		user.setPhone("979688787686");
		user.setPassword("12345");
		user.setRole("user");
		user.setUsername("tester@gmail.com");
		
        when(userdao.findByUsername(user.getUsername())).thenReturn(null);
        when(pswd.matches(user.getPassword(), user.getPassword())).thenReturn(false);
        Assertions.assertThrows(UserNotFoundException.class, () -> {
            userservice.login(user);
        });
        
        verify(userdao, times(1)).findByUsername(user.getUsername());

		
	}

	
	
	@Test
	public void fetchAllUsers() {
		
		User user=new User();
		user.setId(1);
		user.setFirstname("janmejay");
		user.setLastname("mohapatra");
		user.setPhone("979688787686");
		user.setPassword("12345");
		user.setRole("user");
		user.setUsername("tester@gmail.com");
  
		
		User user1=new User();
		user.setId(1);
		user.setFirstname("janmejay");
		user.setLastname("mohapatra");
		user.setPhone("979688787686");
		user.setPassword("12345");
		user.setRole("user");
		user.setUsername("tter@gmail.com");


		when(userdao.findAll()).thenReturn(Stream.of(user,user1).collect(Collectors.toList()));
		
		assertEquals(2,userservice.fetch().size());


   }
	
	@Test
	public void fetchUserByIdUserExists() {
		
		Optional<User> user=Optional.of(new User());
		user.get().setId(1);
		user.get().setFirstname("janmejay");
		user.get().setLastname("mohapatra");
		user.get().setPhone("979688787686");
		user.get().setPassword("12345");
		user.get().setRole("user");
		user.get().setUsername("tester@gmail.com");
		
				


		Mockito.<Optional<User>>when(userdao.findById(user.get().getId())).thenReturn(user);
		
		assertEquals(user.get(),userservice.fetchById(user.get().getId()));


   }
	
	
@Test	
public void fetchUserByIdUserNotExists() {
		
		Optional<User> user=Optional.of(new User());
		user.get().setId(1);
		user.get().setFirstname("janmejay");
		user.get().setLastname("mohapatra");
		user.get().setPhone("979688787686");
		user.get().setPassword("12345");
		user.get().setRole("user");
		user.get().setUsername("tester@gmail.com");
		
				


		Mockito.<Optional<User>>when(userdao.findById(user.get().getId())).thenReturn(Optional.empty());
		
		
		 Assertions.assertThrows(UserNotFoundException.class, () -> {
			 
				userservice.fetchById(user.get().getId());

	        });
        verify(userdao,times(1)).findById(user.get().getId());

   }

@Test	
public void updateValidUser() {
		
		Optional<User> user=Optional.of(new User());
		user.get().setId(1);
		user.get().setFirstname("janmejay");
		user.get().setLastname("mohapatra");
		user.get().setPhone("979688787686");
		user.get().setPassword("12345");
		user.get().setRole("user");
		user.get().setUsername("tester@gmail.com");
		
				


		Mockito.<Optional<User>>when(userdao.findById(user.get().getId())).thenReturn(user);
		Mockito.when(userdao.findByUsername(user.get().getUsername())).thenReturn(user.get());

		userservice.updateUser(user.get());
        verify(userdao,times(1)).save(user.get());

   }
@Test	
public void updateInvalidValidUser() {
		
		Optional<User> user=Optional.of(new User());
		user.get().setId(1);
		user.get().setFirstname("janmejay");
		user.get().setLastname("mohapatra");
		user.get().setPhone("979688787686");
		user.get().setPassword("12345");
		user.get().setRole("user");
		user.get().setUsername("tester@gmail.com");
		
				


		Mockito.<Optional<User>>when(userdao.findById(user.get().getId())).thenReturn(Optional.empty());
		
		
		 Assertions.assertThrows(UserNotFoundException.class, () -> {
			 
				userservice.updateUser(user.get());

	        });
        verify(userdao,times(0)).save(user.get());
        verify(userdao,times(1)).findById(user.get().getId());

   }
   

	
}
