package com.product.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.product.entity.Cart;
import com.product.entity.CartItem;

@Repository
public interface CartDao extends JpaRepository<Cart,Long>{

	public Cart getByuserid(long id);

	public Cart findAllBycartitem(CartItem c);
	
	



}
