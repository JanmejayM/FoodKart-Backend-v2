package com.order.utils;





public class CartItem{
	

private long cartItemId;

private long userid;



private Product product;

private int quantity;



private boolean inorder;



public long getUserid() {
	return userid;
}


public void setUserid(long userid) {
	this.userid = userid;
}



public int getQuantity() {
	return quantity;
}


public void setQuantity(int quantity) {
	this.quantity = quantity;
}



public boolean isInorder() {
	return inorder;
}


public void setInorder(boolean inorder) {
	this.inorder = inorder;
}


public long getCartItemId() {
	return cartItemId;
}


public void setCartItemId(long cartItemId) {
	this.cartItemId = cartItemId;
}


public Product getProduct() {
	return product;
}


public void setProduct(Product product) {
	this.product = product;
}


public CartItem() {
	super();
	// TODO Auto-generated constructor stub
}


public CartItem(long cartItemId, long userid, Product product, int quantity, boolean inorder) {
	super();
	this.cartItemId = cartItemId;
	this.userid = userid;
	this.product = product;
	this.quantity = quantity;
	this.inorder = inorder;
}


@Override
public String toString() {
	return product+","+quantity;
}












}

