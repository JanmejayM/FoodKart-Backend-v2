package com.order.utils;

public class OrderDetails {
	
	private String name;
	private int quantity;
	private long price;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	
	public OrderDetails(String name, int quantity, long price ) {
		super();
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}
	public OrderDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "OrderDetails [name=" + name + ", quantity=" + quantity + ", price=" + price + "]";
	}
	
	
	
	
	
	

}
