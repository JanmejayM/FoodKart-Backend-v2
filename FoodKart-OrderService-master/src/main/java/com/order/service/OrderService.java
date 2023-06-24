package com.order.service;

import java.util.Date;
import java.util.List;

import com.order.entity.Order;
import com.order.utils.CartItem;
import com.order.utils.Revenue;

public interface OrderService {
	
	public List<Order>getAllOrderOfUser(long uid);
	
	public Order getOrderByOrderId(long oid);
	
	public void addOrder(List<CartItem> cartitem,String Address);
	
	public Revenue getRevenue(Date date);
	public Revenue getRevenueMonthly(Date date1,Date date2);
	public List<Order>getAllOrders();


	

}
