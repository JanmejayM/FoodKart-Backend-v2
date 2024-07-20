package com.product.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.product.dao.CartDao;
import com.product.entity.Cart;
import com.product.entity.CartItem;
import com.product.entity.Product;
import com.product.utils.User;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CartServiceTest {
	
	
	@InjectMocks
	private CartServiceImpl cartservice;
	
	
	@Mock
	private CartDao cartdao;
	
	@Mock
	private RestTemplate restTemplate;
	
	
	@Test
	public void CartExists()
	{
		
		when(cartdao.getByuserid(1)).thenReturn(new Cart());
		
		assertTrue(cartservice.checkCartExists(1));
		
		verify(cartdao,times(1)).getByuserid(1);
		
		
	}
	
	@Test
	public void CartNotExists()
	{
		
		when(cartdao.getByuserid(1)).thenReturn(null);
		
		assertFalse(cartservice.checkCartExists(1));
		
		verify(cartdao,times(1)).getByuserid(1);
		
		
	}
	
	@Test
	public void fetchCartValidUser()
	{
		User u=new User();
		u.setFirstname("omm");
		when(restTemplate.getForObject("http://localhost:8080/login-rest/fetch/" + String.valueOf(1), User.class)).thenReturn(u);
		
        cartservice.fetchCart(1);		
		verify(cartdao,times(1)).getByuserid(1);
		
		
	}

	@Test
	public void fetchCartInValidUser()
	{
		User mockUser = new User();
		mockUser.setFirstname(null);
		String userId = "ds";
		String fetchUrl = "http://localhost:8080/login-rest/fetch/" + String.valueOf(userId);
		when(restTemplate.getForObject(fetchUrl, User.class)).thenReturn(mockUser);



		assertThrows(Exception.class, () -> cartservice.fetchCart(Long.parseLong(userId)));

		
	}
	
	
	@Test
	public void deleteAllWithProduct()
	{


		
		List<Cart> cartList = new ArrayList<>();

		Cart cart1 = new Cart();
		CartItem item1 = new CartItem();
		Product product1 = new Product();
		product1.setId(1L);
		item1.setProduct(product1);
		List<CartItem> itemlist = new ArrayList<>();
		itemlist.add(item1);
		cart1.setCartitem(itemlist);
		cartList.add(cart1);

		Cart cart2 = new Cart();
		CartItem item2 = new CartItem();
		Product product2 = new Product();
		product2.setId(2L);
		item2.setProduct(product2);
		List<CartItem> itemlist2 = new ArrayList<>();
		itemlist2.add(item2);
		cart2.setCartitem(itemlist2); // Set itemlist2 on cart2
		cartList.add(cart2);

		// Mock the behavior of the cartdao.findAll() method to return the test cartList
		when(cartdao.findAll()).thenReturn(cartList);

		// Call the deleteAllwithProduct() method
		long productIdToDelete = 1L;
		cartservice.deleteAllwithProduct(productIdToDelete);

		// Verify that the cartDao.save() method was called for the first Cart object only
		verify(cartdao, times(1)).save(cart1);

		

		// Verify that the CartItem with the specified productId was removed from the first Cart object
		assertEquals(0, cart1.getCartitem().size());


		
	}
	
	
    @Test
    public void removeCartItem() {
        // Arrange
        Cart cart = new Cart();
        CartItem cartItem1 = new CartItem();
        Product p1=new Product();
        p1.setId(1L);
        p1.setName("Product 1");
        cartItem1.setProduct(p1);
        cartItem1.setUserid(1L);

        CartItem cartItem2 = new CartItem();
        
        Product p2=new Product();
        p2.setId(1L);
        p2.setName("Product 2");
        cartItem2.setProduct(p2);
        cartItem2.setUserid(1L);

        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem1);
        cartItems.add(cartItem2);

        cart.setCartitem(cartItems);

        when(cartdao.getByuserid(1)).thenReturn(cart);
        when(cartdao.save(any(Cart.class))).thenReturn(cart);

        // Act
        Cart removedCart = cartservice.removeCartItem(cartItem1);

        // Assert
        assertEquals(0, removedCart.getCartitem().size());

        verify(cartdao, times(1)).getByuserid(1);
        verify(cartdao, times(1)).save(any(Cart.class));
    }
    
    
    
    @Test
    public void addCart_ValidUser_CartAdded() {
        // Arrange
        long userId = 1L;
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(new CartItem());

        User user = new User();
        user.setFirstname("Janmejay");
        user.setLastname("Mohapatra");

        when(restTemplate.getForObject(anyString(), eq(User.class))).thenReturn(user);
        when(cartdao.getByuserid(anyLong())).thenReturn(null);
        when(cartdao.save(any(Cart.class))).thenReturn(new Cart());

        // Act
        Cart result = cartservice.addCart(userId, cartItems);

        // Assert
        assertNotNull(result);


       
    }




}
