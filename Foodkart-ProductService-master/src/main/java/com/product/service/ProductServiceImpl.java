package com.product.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.product.controller.ProductController;
import com.product.dao.ProductDao;
import com.product.entity.Product;
import com.product.exception.ProductNotFoundException;



@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductDao productdao;
	
	@Autowired
	RestTemplate restTemplate;
	
	
    private static final Logger logger = LogManager.getLogger(ProductServiceImpl.class);

	
	public ResponseEntity<String> addProduct(Product p)
	{
	
		logger.info("Inside Add Product in ProductServiceImpl");

			productdao.save(p);
			return new ResponseEntity<>(
			          "Product Added", 
			          HttpStatus.OK);
		
	}
	
	public List<Product>fetch()
	{
		logger.info("Inside fetch All Product in ProductServiceImpl");

		List<Product>ar=new ArrayList<>();
		   productdao.findAll()
		   .forEach(product->ar.add(product));
		   return ar;
	}
	
	
	
	public ResponseEntity<String> deleteProduct(long id)
	{
		logger.info("Inside delete Product in ProductServiceImpl");

		if(productdao.findById(id).isPresent())
		{
			
			logger.info("Inside  validProduct in delete ProductServiceImpl");

			restTemplate.delete("http://localhost:8081/cart-rest/deleteProduct/"+String.valueOf(id));
			restTemplate.delete("http://localhost:8081/cartitem-rest/deleteProduct/"+String.valueOf(id));
			
			productdao.deleteById(id);
			return new ResponseEntity<>(
			          "Product Deleted", 
			          HttpStatus.OK);
		}
		logger.warn("Invalid Product");

		throw new ProductNotFoundException("Error in deleting Product"); 

		
	}
	
	
	public ResponseEntity<String> updateProduct(Product p) throws ProductNotFoundException
	{
		logger.info("Inside updateProduct in delete ProductServiceImpl");

		
		if(productdao.findById(p.getId()).isPresent())
		{
			
			logger.info("Inside valid product of updateProduct in  ProductServiceImpl");

			productdao.save(p);
			return new ResponseEntity<>("Product Updated",HttpStatus.OK);
			
		}
		
		logger.warn("Invalid Product");
		throw new ProductNotFoundException("No such Product Exist"); 
		
	}
	
	public Product fetchById(long id)throws ProductNotFoundException
	{
		
		logger.info("Inside fetchProductby in fetchbyId ProductServiceImpl");

		List<Product>res=new ArrayList<>();
		Optional<Product>op=productdao.findById(id);
		
			if ( op.isPresent() )
			{
				logger.info("Inside validProduct in fetchbyId ProductServiceImpl");

			  return op.get();
			}
			logger.warn("Invalid Product");

			throw new ProductNotFoundException("No such Product Exist"); 
			  
			 
		
	}
	

	
	

}
