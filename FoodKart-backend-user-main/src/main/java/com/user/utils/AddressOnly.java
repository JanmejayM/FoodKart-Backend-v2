package com.user.utils;


import javax.validation.constraints.NotEmpty;

public class AddressOnly {
	
	@NotEmpty
	private String houseno;
	
	@NotEmpty
	private String street;
	
	@NotEmpty
	private String city;
	
	@NotEmpty
	private String state;
	
	@NotEmpty
	private String pincode;

	

	public String getHouseno() {
		return houseno;
	}

	public void setHouseno(String houseno) {
		this.houseno = houseno;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public AddressOnly(String houseno, String street, String city, String state, String pincode) {
		super();
		this.houseno = houseno;
		this.street = street;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
	}

	public AddressOnly() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	

}
