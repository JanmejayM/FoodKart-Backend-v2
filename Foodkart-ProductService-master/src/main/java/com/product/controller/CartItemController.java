package com.product.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.entity.CartItem;
import com.product.entity.Product;
import com.product.service.CartItemService;

//@CrossOrigin("*")
@RestController
@RequestMapping("cartitem-rest")
public class CartItemController {
	
	@Autowired 
	CartItemService cartitemservice;
	
	
    private static final Logger logger = LogManager.getLogger(CartItemController.class);

	
	@PostMapping("/addProduct/{id}")
	public void addProduct(@RequestBody Product product,@PathVariable long id)
	{
    	
    	logger.info("Inside addProduct of CartItemController");

		cartitemservice.addProduct(product, id);
	}
	
    
	
    @GetMapping("/fetchCart/{id}")
	public List<CartItem> fetchbyUserCart(@PathVariable long id)
	{
    	logger.info("Inside fetchByUserCart of CartItemController");

     return cartitemservice.fetchByUserIdCart(id);	
	}
    
    @PutMapping("/updateQty")
    public void update(@RequestBody CartItem c)
    {
    	logger.info("Inside UpdateQuantity of CartItemController");

    	cartitemservice.updateQty(c,c.getQuantity());
    }
    
    
    @PostMapping("/delete")
    public void delete(@RequestBody CartItem c)
    {
    	logger.info("Inside deleteCartItem of CartItemController");

    	cartitemservice.remove(c);
    }
    
    @PostMapping("/onCheckout/{id}")
    public ResponseEntity<String> Checkout(@PathVariable long id)
    {
    	
    	logger.info("Inside Checkout of CartItemController");

    	return new ResponseEntity<String>(cartitemservice.Oncheckout(id),HttpStatus.OK);
    }
    
    
    @DeleteMapping("/deleteProduct/{id}")
    public void removeProduct(@PathVariable long id)
    {
    	
    	logger.info("Inside removeProduct of CartItemController");

    	cartitemservice.deleteProduct(id);
    }
	
    
    
    
	
	

}
