package com.user.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.user.entity.Address;
import com.user.utils.AddressOnly;

public interface AddressService {
	
	public List<Address>fetchByUser(long id);
	public Address addToUser(long id,AddressOnly address);
	public Address updateAddress(Address address);
	public void deleteAddress(long id);
	
	
	
	

}
