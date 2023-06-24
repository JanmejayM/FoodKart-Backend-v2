package com.product.service;

import java.util.List;

import com.product.entity.CartItem;
import com.product.entity.Product;

public interface CartItemService {
	public void updateQty(CartItem cartitem,int quantity);
	public void remove(CartItem cartitem);
	public void addProduct(Product product,long id);
	public List<CartItem> fetchByUserIdCart(long id);
    public String Oncheckout(long id);
    public void deleteProduct(long id);

	

}
