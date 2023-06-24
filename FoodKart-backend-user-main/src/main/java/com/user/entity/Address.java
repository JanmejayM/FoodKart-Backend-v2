package com.user.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="address")
public class Address {
	
	@Override
	public String toString() {
		return "Address [address_id=" + address_id + ", houseno=" + houseno + ", street=" + street + ", city=" + city
				+ ", state=" + state + ", pincode=" + pincode + ", user=" + user + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long address_id;
	
	@Column(name="house_no",nullable = false)
	@NotEmpty
	private String houseno;
	
	@Column(name="street",nullable = false)
	@NotEmpty
	private String street;
	
	
	@Column(name="city",nullable = false)
	@NotEmpty
	private String city;
	
	@Column(name="state",nullable = false)
	@NotEmpty
	private String state;
	
	@Column(name="pincode",nullable = false)
	@NotEmpty
	private String pincode;
	
	
	
	@ManyToOne
	@JoinColumn(name="user_id",nullable = false)
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getAddress_id() {
		return address_id;
	}

	public void setAddress_id(long address_id) {
		this.address_id = address_id;
	}

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

	

	public Address(long address_id, String houseno, String street, String city, String state, String pincode,
			User user) {
		super();
		this.address_id = address_id;
		this.houseno = houseno;
		this.street = street;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.user = user;
	}

	public Address() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	

}
