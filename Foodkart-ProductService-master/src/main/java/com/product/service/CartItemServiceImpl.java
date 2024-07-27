package com.product.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.product.controller.ProductController;
import com.product.dao.CartItemDao;
import com.product.entity.CartItem;
import com.product.entity.Product;
import com.product.exception.ProductNotFoundException;
import com.product.exception.RestTemplateErrorHandler;
import com.product.exception.UserNotFoundException;
import com.product.utils.User;

@Service
public class CartItemServiceImpl implements CartItemService {
	@Autowired
	CartItemDao cartitemdao;

	private static final Logger logger = LogManager.getLogger(CartItemServiceImpl.class);

	@Autowired
	RestTemplate restTemplate;
	
	@Value("${gatewayHost}")
	private String gatewayHost;

	@Override
	public void updateQty(CartItem cartitem, int quantity) {
		// TODO Auto-generated method stub

		logger.info("Inside UpdateCartItem of CartItemServiceImpl");

		cartitem.setQuantity(quantity);
		cartitemdao.save(cartitem);

	}

	@Override
	public void remove(CartItem cartitem) {
		// TODO Auto-generated method stub

		logger.info("Inside removeCartItem of CartItemServiceImpl");

		cartitemdao.delete(cartitem);

	}

	@Override
	public void addProduct(Product product, long id) {

		User user = new User();

		logger.info("Inside  addProduct of CartItemServiceImpl");

		user = restTemplate.getForObject(gatewayHost+"/login-rest/fetch/" + String.valueOf(id), User.class);

		if (user.getFirstname() == null) {
			logger.warn("Invalid User ");

			throw new UserNotFoundException("Invalid User");
		}

		List<CartItem> cartitems = cartitemdao.findAllByCartProduct(id, false, product);

		if (cartitems.size() != 0) {
			cartitems.stream().forEach((cart) -> {
				if (cart.getProduct().getId() == product.getId()) {
					cart.setQuantity(cart.getQuantity() + 1);
					cartitemdao.save(cart);
					return;
				}
			}

			);
		}

		else {
			CartItem cartItem = new CartItem();
			cartItem.setProduct(product);
			cartItem.setQuantity(1);
			cartItem.setUserid(id);

			cartitemdao.save(cartItem);
		}

	}

	@Override
	public List<CartItem> fetchByUserIdCart(long id) {
		// TODO Auto-generated method stub
		User user = new User();
		List<CartItem> ls = new ArrayList<>();

		logger.info("Inside Inside fetchByUserIdCart of CartItemServiceImpl");

		try {

			user = restTemplate.getForObject(gatewayHost+"/login-rest/fetch/" + String.valueOf(id),
					User.class);
			cartitemdao.findAllByuseridandInorder(id, false).stream().forEach(cartitem -> ls.add(cartitem));
		} catch (Exception ex) {

			logger.warn("Invalid User");

			throw new UserNotFoundException("User Invalid");

		}

		return ls;

	}

	@Override
	public String Oncheckout(long id) {
		// TODO Auto-generated method stub
		List<CartItem> ls = new ArrayList<>();

		User user = new User();
		logger.info("Inside Inside OnCheckout of CartItemServiceImpl");

		user = restTemplate.getForObject(gatewayHost+"/login-rest/fetch/" + String.valueOf(id), User.class);

		if (user.getFirstname() == null) {

			logger.warn("Invalid User");

			throw new UserNotFoundException("Invalid User");

		}

		cartitemdao.findAllByuseridandInorder(id, false).stream().forEach(cartitem -> {
			cartitem.setInorder(true);
			cartitemdao.save(cartitem);
		});
		return "Checkout Done";

	}

	@Override
	public void deleteProduct(long id) {

		logger.info("Inside deleteProduct of CartItemServiceImpl");

		cartitemdao.findAll().stream().filter(cartitem -> cartitem.getProduct().getId() == id)
				.forEach(cartitem -> cartitemdao.delete(cartitem));

	}

}
