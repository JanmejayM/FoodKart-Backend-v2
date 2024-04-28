package com.bill.entity;

import java.util.ArrayList;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.bill.utils.OrderDetails;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


@Entity
@Table(name = "order_table")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name="user_id",nullable = false)
	@NotNull
	private long userid;
	
	@Column(name="summary",nullable = false,columnDefinition = "TEXT")
	@NotEmpty
	private String summary;
	
	
	@Column(name="address",nullable = false)
	@NotEmpty
	private String address;
	
	
	@Temporal(TemporalType.DATE)
	private Date calendarDate;
	
	@Column(name="price",nullable = false)
	@NotNull
	private long price;
	
	
	@Transient
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
