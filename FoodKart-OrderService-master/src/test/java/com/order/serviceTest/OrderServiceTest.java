package com.order.serviceTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.order.dao.OrderDao;
import com.order.entity.Order;
import com.order.exceptions.OrderNotFoundException;
import com.order.exceptions.UserNotFoundException;
import com.order.service.OrderService;
import com.order.service.OrderServiceImpl;
import com.order.utils.CartItem;
import com.order.utils.OrderDetails;
import com.order.utils.Product;
import com.order.utils.Revenue;
import com.order.utils.User;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class OrderServiceTest {
	
	
	@InjectMocks
	private OrderServiceImpl orderservice;
	
	@Mock
	private OrderDao orderdao;
	
	@Mock
	private RestTemplate restTemplate;
	
	@Test
    public void testGetAllOrderOfValidUser()  {
        // Mock user response
        User user = new User();
        user.setId(1L);
        user.setUsername("omm");
        
        when(restTemplate.getForObject("http://localhost:8080/login-rest/fetch/1", User.class)).thenReturn(user);

        // Mock order data
        Order order1 = new Order();
        order1.setUserid(1L);
        
        order1.setSummary("chowmein,230,1");
        

        Order order2 = new Order();
        order2.setUserid(1L);
        order2.setSummary("chowmein,230,1");

        
        List<Order> mockOrders = new ArrayList<>();
        mockOrders.add(order1);
        mockOrders.add(order2);
        when(orderdao.findByuserid(1)).thenReturn(mockOrders);


        List<Order> result = orderservice.getAllOrderOfUser(1L);

        // Assertions
        Assertions.assertEquals(2, result.size());
        Assertions.assertSame(order1, result.get(0));
        Assertions.assertSame(order2, result.get(1));

        
    }	
	
	@Test
    public void testGetAllOrderOfInvalidValidUser() throws UserNotFoundException {
        // Mock user response
        User user = new User();
        user.setId(1L);
        
        when(restTemplate.getForObject("http://localhost:8080/login-rest/fetch/1", User.class)).thenReturn(user);

        // Mock order data
        Order order1 = new Order();
        order1.setUserid(1L);
        
        order1.setSummary("chowmein,230,1");
        

        Order order2 = new Order();
        order2.setUserid(1L);
        order2.setSummary("chowmein,230,1");

        
        List<Order> mockOrders = new ArrayList<>();
        mockOrders.add(order1);
        mockOrders.add(order2);
        when(orderdao.findByuserid(1)).thenReturn(mockOrders);

Assertions.assertThrows(UserNotFoundException.class, () -> {
    orderservice.getAllOrderOfUser(1);


        });

        

        
    }	
	
	@Test
    public void testGetOrderforValidOrderID() throws OrderNotFoundException {
        // Mock user response
        User user = new User();
        user.setId(1L);
        user.setUsername("omm");
        

        // Mock order data
        Order order1 = new Order();
        order1.setUserid(1L);
        order1.setId(1L);
        
        order1.setSummary("chowmein,230,1");
        

      

        when(orderdao.findById(1L)).thenReturn(Optional.of(order1));


        Order result = orderservice.getOrderByOrderId(1L);

        // Assertions
        Assertions.assertSame(order1, result);

        
    }	
	
	@Test
    public void testGetOrderforInValidOrderID() throws OrderNotFoundException {
        // Mock user response
        User user = new User();
        user.setId(1L);
        user.setUsername("omm");
        

        // Mock order data
        Order order1 = new Order();
        order1.setUserid(1L);
        order1.setId(1L);
        
        order1.setSummary("chowmein,230,1");
        

      

        when(orderdao.findById(1L)).thenReturn(Optional.empty());


        Assertions.assertThrows(OrderNotFoundException.class, () -> {
            orderservice.getOrderByOrderId(123445L);


                });

        
    }	
	
	
	
	@Test
    public void getDayRevenue() {
        // Mock user response
        
        

        // Mock order data
        Order order1 = new Order();
        order1.setUserid(1L);
        order1.setId(1L);
        order1.setPrice(200L);
        
        Order order2 = new Order();
        order2.setUserid(1L);
        order2.setId(1L);
        order2.setPrice(200L);
        List<Order>ls=new ArrayList<>();

        ls.add(order1);
        ls.add(order2);


      

        when(orderdao.getAllOrderBydate(new Date(13253678576L))).thenReturn(ls);
        
        
        
        Revenue result=orderservice.getRevenue(new Date(13253678576L));
        assertEquals(400L,result.getPrice());
        




        
    }	
	@Test
    public void getMonthlyRevenue() {
        // Mock user response
        
        

        // Mock order data
        Order order1 = new Order();
        order1.setUserid(1L);
        order1.setId(1L);
        order1.setPrice(200L);
        
        Order order2 = new Order();
        order2.setUserid(1L);
        order2.setId(1L);
        order2.setPrice(200L);
        List<Order>ls=new ArrayList<>();

        ls.add(order1);
        ls.add(order2);


      

        when(orderdao.getAllOrderBetweendate(new Date(13253678576L),new Date(13253678578L))).thenReturn(ls);
        
        
        
        Revenue result=orderservice.getRevenueMonthly(new Date(13253678576L),new Date(13253678578L));
        assertEquals(400L,result.getPrice());
        




        
    }	
	
	@Test
	public void addToCartEmptyCartItems()
	{
		List<CartItem>ls=new ArrayList<>();
		
		
        Assertions.assertThrows(OrderNotFoundException.class, () -> {
            orderservice.addOrder(ls, "gkgg");


                });
		
		
		
	}
	
	@Test
	public void addToCartValidCartItems()
	{

		
		
	    CartItem cartItem1 = new CartItem();
	    cartItem1.setUserid(1L);
	    cartItem1.setProduct(new Product());
	    cartItem1.setQuantity(2);

	    CartItem cartItem2 = new CartItem();
	    cartItem2.setUserid(1L);
	    cartItem2.setProduct(new Product());
	    cartItem2.setQuantity(3);

	    List<CartItem> cartItems = Arrays.asList(cartItem1, cartItem2);

	    // Mock order save response
	    Order savedOrder = new Order();
	    savedOrder.setId(1L);
	    savedOrder.setUserid(1L); // Set the correct userid
	    when(orderdao.save(any(Order.class))).thenReturn(savedOrder);

	    // Mock restTemplate postForEntity response

	    // Invoke the method
	    orderservice.addOrder(cartItems, "Test Address");

	    verify(orderdao, times(1)).save(any(Order.class)); // Verify that orderDao.save is called

		
		
	}
	
	

}
