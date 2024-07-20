package com.order.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime; // import the LocalTime class
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.order.dao.OrderDao;
import com.order.entity.Order;
import com.order.exceptions.UserNotFoundException;
import com.order.exceptions.OrderNotFoundException;
import com.order.exceptions.RestTemplateErrorHandler;
import com.order.utils.CartItem;
import com.order.utils.OrderDetails;
import com.order.utils.Product;
import com.order.utils.Revenue;
import com.order.utils.User;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDao orderdao;

	/*
	 * public OrderServiceImpl(OrderDao orderdao) { super(); this.orderdao =
	 * orderdao; }
	 */

	@Autowired
	RestTemplate restTemplate;
	
	
    private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);


	@Override
	public List<Order> getAllOrderOfUser(long uid) throws UserNotFoundException {
		// TODO Auto-generated method stub
		
		logger.info("In Get all Orders of User Service");
		User user = new User();

		user = restTemplate.getForObject("http://localhost:8080/login-rest/fetch/" + String.valueOf(uid), User.class);
        
		
		logger.info("User fetched"+user);
		if (user.getUsername() == null) {
			
			logger.warn("Invalid User");
			throw new UserNotFoundException("Invalid User");

		}

		List<Order> res = new ArrayList<>();

		orderdao.findByuserid(uid).stream().forEach(order -> {
			String summary = order.getSummary();

			String[] subSummary = summary.split(";");
			for (String s : subSummary) {
				OrderDetails o = new OrderDetails();

				String[] subs = s.split(",");

				o.setName(subs[0]);
				o.setPrice(Long.parseLong(subs[1]));
				o.setQuantity(Integer.parseInt(subs[2]));

				order.getDescription().add(o);

			}
			res.add(order);


		});
		

		return res;

	}

	@Override
	public Order getOrderByOrderId(long oid) throws OrderNotFoundException {

		
		logger.info("In Get Orders Service");

		if (!orderdao.findById(oid).isPresent()) {
			
			logger.warn("Invalid Order ID");

			throw new OrderNotFoundException("Invalid Order ID");
		}

		Order order = orderdao.findById(oid).get();

		String summary = order.getSummary();

		String[] subSummary = summary.split(";");

		for (String s : subSummary) {
			String[] subs = s.split(",");
			order.getDescription().add(new OrderDetails(subs[0], Integer.parseInt(subs[2]), Long.parseLong(subs[1])));
		}

		return order;
	}

	@Override
	public void addOrder(List<CartItem> cartitems, String address) throws OrderNotFoundException {
		// TODO Auto-generated method

		if (cartitems.size() == 0) {
			
			logger.warn("Invalid Order Call");

			throw new OrderNotFoundException("Invalid Order");
		}

		Date today = Calendar.getInstance().getTime();

		long id = cartitems.get(0).getUserid();
		Order order = new Order();
		order.setUserid(id);
		order.setCalendarDate(today);
		order.setAddress(address);
		String summary = "";

		for (CartItem cartitem : cartitems) {
			summary += cartitem.toString() + ";";
		}

		long price = cartitems.stream().map(p -> p.getQuantity() * p.getProduct().getPrice())
				.collect(Collectors.toList()).stream().reduce(0L, (ans, i) -> ans + i);

		System.out.println(price);
		order.setDescription(null);

		order.setSummary(summary);
		order.setPrice(price);
		Order o=orderdao.save(order);
		
		
		if(o!=null)
		{
		restTemplate.postForEntity(
				"http://localhost:8081/cartitem-rest/onCheckout/" + String.valueOf(id), null, null);
		}

	}

	public Revenue getRevenue(Date date) {
		long sum = 0;
		List<Order> orders = new ArrayList<>();
		orderdao.getAllOrderBydate(date).forEach(order -> {
			orders.add(order);
		});

		for (Order o : orders) {
			sum += o.getPrice();
		}

		Revenue revenue = new Revenue();
		revenue.setFromDate(date);
		revenue.setToDate(null);
		revenue.setPrice(sum);

		return revenue;

	}

	public Revenue getRevenueMonthly(Date date1, Date date2) {
		long sum = 0;
		List<Order> orders = new ArrayList<>();
		orderdao.getAllOrderBetweendate(date1, date2).forEach(order -> {
			orders.add(order);
		});

		for (Order o : orders) {
			sum += o.getPrice();
		}

		Revenue revenue = new Revenue();
		revenue.setFromDate(date1);
		revenue.setToDate(date2);
		revenue.setPrice(sum);

		return revenue;

	}
	
	public List<Order>getAllOrders()
	{
		return orderdao.findAll();
	}

}
