package com.product.entity;



import javax.persistence.CascadeType;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.GenerationType;

@Entity
@Table(name="cartitem")
public class CartItem{
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long cartItemId;

@Column(name="user_id",nullable=false)
@NotNull
private long userid;


@ManyToOne   // remove    // 
@JoinColumn(name="product_id",nullable = true)
private Product product;

@Column(name="product_qty",nullable=false)
@Range(min = 1, max =5)
private int quantity;



@Column(name="in_order", nullable=false)
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










}

