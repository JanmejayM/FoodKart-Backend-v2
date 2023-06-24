package com.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.product.controller.ProductController;
import com.product.dao.CartDao;
import com.product.entity.Cart;
import com.product.entity.CartItem;
import com.product.entity.Product;
import com.product.exception.RestTemplateErrorHandler;
import com.product.exception.UserNotFoundException;
import com.product.utils.User;


@Service
public class CartServiceImpl implements CartService{

	
	@Autowired
	CartDao cartdao;
	
	
	@Autowired
	RestTemplate restTemplate;
	
    private static final Logger logger = LogManager.getLogger(CartServiceImpl.class);


	RestTemplateBuilder builder = new RestTemplateBuilder();

	public Cart addCart(long id, List<CartItem> cartitem) {

		RestTemplate restTemplate = new RestTemplate();
		restTemplate = builder.errorHandler(new RestTemplateErrorHandler()).build();
		User user = new User();

		
    	logger.info("Inside addCart of CartServiceImpl");

		user = restTemplate.getForObject("http://localhost:8080/login-rest/fetch/" + String.valueOf(id), User.class);
		if (user.getFirstname() == null) {
			
			logger.warn("Invalid User");
			throw new UserNotFoundException("Invalid User");
		}

		if (checkCartExists(id)) {
			
	    	logger.info("Inside existingCart of CartServiceImpl");

			Cart c = cartdao.getByuserid(id);
            
			c.setCartitem(cartitem);
			return cartdao.save(c);
		} else {
	    	logger.info("Inside newCart of CartServiceImpl");

			Cart c = new Cart(id, cartitem);
			return cartdao.save(c);
		}

	}

	public boolean checkCartExists(long id) {
		Cart cart = cartdao.getByuserid(id);
		if (cart != null) {
			return true;
		}
		return false;

	}

	@Override
	public Cart fetchCart(long id) {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = new RestTemplate();
		restTemplate = builder.errorHandler(new RestTemplateErrorHandler()).build();
		User user = new User();
		user = restTemplate.getForObject("http://localhost:8080/login-rest/fetch/" + String.valueOf(id), User.class);

		if (user.getFirstname() == null) {
			logger.warn("Invalid User");

			throw new UserNotFoundException("Invalid User");
		}

		return cartdao.getByuserid(id);

	}

	public Cart removeCartItem(CartItem c) {
		Cart ls = cartdao.getByuserid(c.getUserid());

		logger.info("Inside removeCartItem");

		List<CartItem> t = ls.getCartitem();

		t.removeIf(item -> item.getProduct().getId() == c.getProduct().getId());

		ls.setCartitem(t);

		Cart cart = cartdao.save(ls);
		if (cart != null) {
			restTemplate.postForEntity("http://localhost:8081/cartitem-rest/delete", c, null);
		}
		return cart;

	}

	@Override
	public void deleteAllwithProduct(long id) {
		// TODO Auto-generated method stub
		
		logger.info("Inside deleteAll with Product");

		List<Cart> cart = cartdao.findAll();

		for (Cart ls : cart) {

			List<CartItem> t = ls.getCartitem();

			t.removeIf(item -> item.getProduct().getId() == id);

			ls.setCartitem(t);

			cartdao.save(ls);

		}

	}


	


}
