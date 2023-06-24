package com.order.utils;

import java.util.Date;

public class Revenue {
	
	private Date fromDate;
	private Date toDate;
    private long price;
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public Revenue(Date fromDate, Date toDate, long price) {
		super();
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.price = price;
	}
	public Revenue() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Revenue [fromDate=" + fromDate + ", toDate=" + toDate + ", price=" + price + "]";
	}
    
    
    
	

}
