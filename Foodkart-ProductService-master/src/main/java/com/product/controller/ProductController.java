package com.product.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.product.entity.Product;
import com.product.service.ProductService;


//@CrossOrigin("*")
@RestController
@RequestMapping("product-rest")
public class ProductController {
	
	@Autowired
	ProductService productservice;
	
    private static final Logger logger = LogManager.getLogger(ProductController.class);

	
	@PostMapping("/add")
	public ResponseEntity<String> addProduct(@Valid @RequestBody Product product)
	{
    	logger.info("Inside addProduct of ProductController");

		return this.productservice.addProduct(product);
	}

	@GetMapping("/fetch")
	public ResponseEntity<List<Product>> fetchProduct()
	{
    	
    	logger.info("Inside fetchProduct of ProductController");

		return new ResponseEntity<List<Product>>(this.productservice.fetch(),HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String>deleteProduct(@PathVariable long id)
	{
    	logger.info("Inside deleteProduct of ProductController");

		return this.productservice.deleteProduct(id);
	}
	
	@PutMapping("/update")
	public ResponseEntity<String>updateProduct(@Valid @RequestBody Product p)
	{
    	
    	logger.info("Inside updateProduct of ProductController");

		return this.productservice.updateProduct(p);
	}
    
    @GetMapping("/fetch/{Id}")
	public ResponseEntity<Product>fetchById(@PathVariable long Id)
	{
    	
    	logger.info("Inside fetchProductById of ProductController");

		return new ResponseEntity<Product>(productservice.fetchById(Id),HttpStatus.OK);
	}
	
  
    
    
    
	

}
