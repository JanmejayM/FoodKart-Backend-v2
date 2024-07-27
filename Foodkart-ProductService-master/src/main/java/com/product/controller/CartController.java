package com.product.controller;

import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.product.entity.Cart;
import com.product.entity.CartItem;
import com.product.entity.Product;
import com.product.exception.ProductNotFoundException;
import com.product.service.CartService;

//@CrossOrigin("*")
@RestController
@RequestMapping("cart-rest")
public class CartController {
	
	@Autowired
	CartService cartservice;
	
	@Value("${gatewayHost}")
	private String gatewayHost;
	
    private static final Logger logger = LogManager.getLogger(CartController.class);

	
	@GetMapping("/addCart/{id}")
	public void addCart(@PathVariable long id)
	{
    	
    	logger.info("In AddCart of CartController endpoint");
    	
		String url = gatewayHost+"/cartitem-rest/fetchCart/"+String.valueOf(id);
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<CartItem>> response = restTemplate.exchange(
		    url,
		    HttpMethod.GET,
		    null,
		    new ParameterizedTypeReference<List<CartItem>>(){});
		List<CartItem> objects = response.getBody();
		
		Cart cart=cartservice.addCart(id, objects);
		
		if(cart.getCartId()==0)
		{
			logger.warn("Not a valid User");
			throw new ProductNotFoundException("Not a Valid User");
		}
		
		
    	logger.info("Succesfully added to cart");

		
		
		
		
	}
	@GetMapping("/getCart/{id}")
	public Cart getCart(@PathVariable long id)
	{
    	
    	logger.info("Inside getCart of CartController");

		return cartservice.fetchCart(id);
	}
    
   	@PostMapping("/deleteCartItem")
    public void deletecartItem(@RequestBody CartItem c)
    {
    	
    	logger.info("Inside deleteCartItem of CartController");

    	
    	cartservice.removeCartItem(c);
    	

    	
    }
    
   	@DeleteMapping("/deleteProduct/{id}")
    public void deleteProduct(@PathVariable long id)
    {
    	logger.info("Inside deleteProduct of CartController");

    	cartservice.deleteAllwithProduct(id);

    	
    }

}
