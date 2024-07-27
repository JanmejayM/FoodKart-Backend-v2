package com.user.serviceTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.user.dao.AddressDao;
import com.user.entity.Address;
import com.user.entity.User;
import com.user.exception.InvalidAddressException;
import com.user.exception.UserNotFoundException;
import com.user.service.AddressService;
import com.user.service.AddressServiceImpl;
import com.user.utils.AddressOnly;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
class AddressServiceTest {

	@InjectMocks
	private AddressServiceImpl addressservice;

	@Mock
	private RestTemplate restTemplate;

	@Mock
	private AddressDao addressdao;

	@Test
	public void deleteValidAddress() {

		Address add = new Address();
		User user = new User();
		user.setId(1);
		user.setFirstname("janmejay");
		user.setLastname("mohapatra");
		user.setPhone("979688787686");
		user.setPassword("12345");
		user.setRole("user");
		user.setUsername("tester@gmail.com");

		add.setAddress_id(1);
		add.setCity("Cuttack");
		add.setHouseno("84");
		add.setPincode("753012");
		add.setState("Odisha");
		add.setStreet("Bishram Nagar Link Road");
		add.setUser(user);

		when(addressdao.findById(add.getAddress_id())).thenReturn(Optional.of(add));

		addressservice.deleteAddress(add.getAddress_id());
		verify(addressdao, times(1)).findById(add.getAddress_id());

		verify(addressdao, times(1)).deleteById(add.getAddress_id());

	}

	@Test
	public void deleteInValidAddress() {

		Address add = new Address();
		User user = new User();
		user.setId(1);
		user.setFirstname("janmejay");
		user.setLastname("mohapatra");
		user.setPhone("979688787686");
		user.setPassword("12345");
		user.setRole("user");
		user.setUsername("tester@gmail.com");

		add.setAddress_id(1);
		add.setCity("Cuttack");
		add.setHouseno("84");
		add.setPincode("753012");
		add.setState("Odisha");
		add.setStreet("Bishram Nagar Link Road");
		add.setUser(user);

		when(addressdao.findById(add.getAddress_id())).thenReturn(Optional.empty());

		Assertions.assertThrows(InvalidAddressException.class, () -> {

			addressservice.deleteAddress(user.getId());

		});

		verify(addressdao, times(1)).findById(add.getAddress_id());
		verify(addressdao, times(0)).deleteById(add.getAddress_id());

	}

	@Test
	public void updateValidAddress() {
		Address add = new Address();
		User user = new User();
		user.setId(1);
		user.setFirstname("janmejay");
		user.setLastname("mohapatra");
		user.setPhone("979688787686");
		user.setPassword("12345");
		user.setRole("user");
		user.setUsername("tester@gmail.com");

		add.setAddress_id(1);
		add.setCity("Cuttack");
		add.setHouseno("84");
		add.setPincode("753012");
		add.setState("Odisha");
		add.setStreet("Bishram Nagar Link Road");
		add.setUser(user);

		when(addressdao.findById(add.getAddress_id())).thenReturn(Optional.of(add));
		addressservice.updateAddress(add);

		verify(addressdao, times(1)).findById(add.getAddress_id());
		verify(addressdao, times(1)).save(add);

	}

	@Test
	public void updateInValidAddress() {
		Address add = new Address();
		User user = new User();
		user.setId(1);
		user.setFirstname("janmejay");
		user.setLastname("mohapatra");
		user.setPhone("979688787686");
		user.setPassword("12345");
		user.setRole("user");
		user.setUsername("tester@gmail.com");

		add.setAddress_id(1);
		add.setCity("Cuttack");
		add.setHouseno("84");
		add.setPincode("753012");
		add.setState("Odisha");
		add.setStreet("Bishram Nagar Link Road");
		add.setUser(user);

		when(addressdao.findById(add.getAddress_id())).thenReturn(Optional.empty());

		Assertions.assertThrows(InvalidAddressException.class, () -> {

			addressservice.updateAddress(add);

		});

		verify(addressdao, times(1)).findById(add.getAddress_id());
		verify(addressdao, times(0)).save(add);

	}

	@Test
	public void getAddressforValidUser() {
		Address add = new Address();
		User user = new User();
		user.setId(1);
		user.setFirstname("janmejay");
		user.setLastname("mohapatra");
		user.setPhone("979688787686");
		user.setPassword("12345");
		user.setRole("user");
		user.setUsername("tester@gmail.com");

		add.setAddress_id(1);
		add.setCity("Cuttack");
		add.setHouseno("84");
		add.setPincode("753012");
		add.setState("Odisha");
		add.setStreet("Bishram Nagar Link Road");
		add.setUser(user);

		when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(User.class))).thenReturn(user);
		when(addressdao.findAll()).thenReturn(Stream.of(add).collect(Collectors.toList()));

		addressservice.fetchByUser(user.getId());

		verify(addressdao, times(1)).findAll();

		assertNotNull(user.getUsername());

	}

	@Test
	public void getAddressforInValidUser() {

		User user1 = new User();
		Address add = new Address();
		User user = new User();
		user.setId(1);
	

		when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(User.class))).thenReturn(user1);
		when(addressdao.findAll()).thenReturn(Stream.of(add).collect(Collectors.toList()));

		Assertions.assertThrows(UserNotFoundException.class, () -> {
			addressservice.fetchByUser(user.getId());

		});

		verify(addressdao, times(0)).findAll();

		assertNull(user1.getUsername());

	}

	@Test
	public void addAddressforValidUser() {

		Address add = new Address();
		AddressOnly address = new AddressOnly();

		User user = new User();
		user.setUsername("jsgdkgdg");

		// when(addressdao.save(add)).thenReturn(add);

		when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(User.class))).thenReturn(user);

		addressdao.save(add);

		when(addressdao.save(add)).thenReturn(add);

		addressservice.addToUser(1, address);

		verify(addressdao, times(1)).save(add);

	}

	@Test
	public void addAddressforInValidUser() {

		Address add = new Address();
		AddressOnly address = new AddressOnly();

		User user = new User();

		// when(addressdao.save(add)).thenReturn(add);

		when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(User.class))).thenReturn(new User());
		// when(user.getUsername()).thenReturn(null);

		// when(addressdao.save(add)).thenReturn(add);

		Assertions.assertThrows(UserNotFoundException.class, () -> {
			addressservice.addToUser(1, address);
		});

	}

}
