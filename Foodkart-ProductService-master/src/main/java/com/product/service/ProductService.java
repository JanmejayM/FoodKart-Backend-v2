package com.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.product.entity.Product;


public interface ProductService {

	public ResponseEntity<String> addProduct(Product p);
	public List<Product>fetch();
	public ResponseEntity<String> deleteProduct(long id);
	public ResponseEntity<String> updateProduct(Product p);
	public Product fetchById(long id);



	
}
