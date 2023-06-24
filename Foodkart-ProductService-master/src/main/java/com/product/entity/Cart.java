package com.product.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="cart")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long cartId;
	
	
	@Column(name="user_id" ,nullable=false)
	@NotNull
	private long userid;
	
	@OneToMany
	private List<CartItem> cartitem;

	public long getCartId() {
		return cartId;
	}

	public void setCartId(long cartId) {
		this.cartId = cartId;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	
	public Cart(long cartId, long userid, List<CartItem> cartitem) {
		super();
		this.cartId = cartId;
		this.userid = userid;
		this.cartitem = cartitem;
	}
	
	public Cart(long userid, List<CartItem> cartitem) {
		super();
		
		this.userid = userid;
		this.cartitem = cartitem;
	}


	public List<CartItem> getCartitem() {
		return cartitem;
	}

	public void setCartitem(List<CartItem> cartitem) {
		this.cartitem = cartitem;
	}

	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	
	
	
	

}
