package com.user.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.controller.AddressController;
import com.user.dao.AddressDao;
import com.user.entity.Address;
import com.user.entity.User;
import com.user.exception.UserNotFoundException;
import com.user.exception.InvalidAddressException;
import com.user.exception.RestTemplateErrorHandler;
import com.user.utils.AddressOnly;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressDao addressdao;

	@Autowired
	RestTemplate restTemplate;

	private static final Logger logger = LogManager.getLogger(AddressServiceImpl.class);

	@Override
	public List<Address> fetchByUser(long id) throws UserNotFoundException {
		// TODO Auto-generated method stub
		List<Address> res = new ArrayList<>();

		User user = new User();

		user = restTemplate.getForObject("http://localhost:8080/login-rest/fetch/" + String.valueOf(id), User.class);

		logger.info("User fetched=" + user);
		if (user.getUsername() == null) {
			logger.warn("Invalid user");
			throw new UserNotFoundException("Invalid User");

		}
		addressdao.findAll().stream().filter(u -> u.getUser().getId() == id).forEach(ar -> res.add(ar));
		return res;

	}

	@Override
	public Address addToUser(long id, AddressOnly address) throws UserNotFoundException {
		// TODO Auto-generated method stub

		Address add = new Address();

		User user = new User();

		user = restTemplate.getForObject("http://localhost:8080/login-rest/fetch/" + String.valueOf(id), User.class);
		logger.info("User fetched=" + user);

		if (user.getUsername() != null) {
			logger.info("Valid user");

			add.setCity(address.getCity());
			add.setHouseno(address.getHouseno());
			add.setPincode(address.getPincode());
			add.setState(address.getState());
			add.setStreet(address.getStreet());
			add.setUser(user);
			return addressdao.save(add);
		}

		logger.warn("Invalid user");

		throw new UserNotFoundException("User Not Valid");

	}

	@Override
	public Address updateAddress(Address address) throws InvalidAddressException {

		if (addressdao.findById(address.getAddress_id()).isPresent()) {
			logger.info("Valid Address");

			return addressdao.save(address);
		}
		logger.warn("Invalid Address");

		throw new InvalidAddressException("Invalid Address");

	}

	@Override
	public void deleteAddress(long id) throws InvalidAddressException {
		// TODO Auto-generated method stub

		if (addressdao.findById(id).isPresent()) {
			logger.info("Valid Address");

			addressdao.deleteById(id);
		} else {
			logger.warn("Invalid Address");

			throw new InvalidAddressException("Invalid Address");
		}

	}

}
