package com.product.dao;
import com.product.entity.CartItem;
import com.product.entity.Product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemDao extends JpaRepository<CartItem,Long>{

	public List<CartItem> findAllByuserid(long id);
	
	@Query("SELECT c FROM CartItem c WHERE c.userid = ?1 and c.inorder= ?2")
	public List<CartItem> findAllByuseridandInorder(long id,boolean inorder);

	
	@Query("SELECT c FROM CartItem c WHERE c.userid = ?1 and c.inorder= ?2 and c.product= ?3")
	public List<CartItem> findAllByCartProduct(long id,boolean inorder,Product product);

}
