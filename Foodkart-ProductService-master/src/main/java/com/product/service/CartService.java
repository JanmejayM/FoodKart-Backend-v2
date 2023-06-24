package com.product.service;

import java.util.List;

import com.product.dao.CartDao;
import com.product.entity.Cart;
import com.product.entity.CartItem;
import com.product.entity.Product;

public interface CartService {
	
	   public Cart addCart(long id,List<CartItem> cartitem);
       public Cart fetchCart(long id);
       public Cart removeCartItem(CartItem c);
       public void deleteAllwithProduct(long id);
   	public boolean checkCartExists(long id);

	
}
