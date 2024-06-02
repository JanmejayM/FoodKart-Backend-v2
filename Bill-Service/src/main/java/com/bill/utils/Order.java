package com.bill.utils;

import java.util.ArrayList;

import com.bill.utils.OrderDetails;

import java.util.Date;
import java.util.List;




public class Order {
	
	
	private long id;
	
	
	private long userid;
	
	
	private String summary;
	
	

	private String address;
	
	
	private Date calendarDate;
	
	
	private long price;
	
	
	
	private List<OrderDetails> description=new ArrayList<>();

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getUserid() {
		return userid;
	}


	public void setUserid(long userid) {
		this.userid = userid;
	}


	public String getSummary() {
		return summary;
	}


	public void setSummary(String summary) {
		this.summary = summary;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public long getPrice() {
		return price;
	}


	public void setPrice(long price) {
		this.price = price;
	}


	public List<OrderDetails> getDescription() {
		return description;
	}


	public void setDescription(List<OrderDetails> description) {
		this.description = description;
	}
	

	public Date getCalendarDate() {
		return calendarDate;
	}


	public void setCalendarDate(Date calendarDate) {
		this.calendarDate = calendarDate;
	}



	


	public Order(long userid, String summary, String address, Date calendarDate, long price,
			List<OrderDetails> description) {
		super();
		this.userid = userid;
		this.summary = summary;
		this.address = address;
		this.calendarDate = calendarDate;
		this.price = price;
		this.description = description;
	}



	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "Order [id=" + id + ", userid=" + userid + ", summary=" + summary + ", address=" + address
				+ ", calendarDate=" + calendarDate + ", price=" + price + ", description=" + description + "]";
	}


	


	
	
	

}
