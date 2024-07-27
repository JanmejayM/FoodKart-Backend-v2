package com.product.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.product.dao.CartItemDao;
import com.product.entity.CartItem;
import com.product.entity.Product;
import com.product.utils.User;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CartItemServiceTest {
	
	@InjectMocks
	private CartItemServiceImpl cartitemservice;
	
	
	@Mock
	private CartItemDao cartitemdao;
	
	@Mock
	private RestTemplate restTemplate;
	
	
	
	@Test
	public void testUpdateQty() {
	    CartItem cartItem = new CartItem();
	    cartItem.setQuantity(5);
	    
	    
	    // Call the updateQty method with a new quantity
	    int newQuantity = 2;
	    cartitemservice.updateQty(cartItem, newQuantity);
	    
	    // Assert that the quantity of the cartItem has been updated
	    assertEquals(newQuantity, cartItem.getQuantity());
	}
	
	
	@Test
	public void testRemove() {
	    CartItem cartItem = new CartItem();
	    

	    
	    cartitemservice.remove(cartItem);
	    
	   
	    verify(cartitemdao,times(1)).delete(cartItem);
	}
	
	
	@Test
	public void testDeleteProduct() {
	   // Create a list of CartItems with a specific product ID
	    List<CartItem> cartItems = new ArrayList<>();
	    long productId = 123; // Specify the product ID to filter
	    cartItems.add(new CartItem(1,1,new Product(productId,"","","",100),5,false));
	    cartItems.add(new CartItem(2,1,new Product(productId,"","","",100),3,false));
	    cartItems.add(new CartItem(3,1,new Product(4,"","","",100),3,false));


	    
	    // Mock the findAll method to return the list of CartItems
	    Mockito.when(cartitemdao.findAll()).thenReturn(cartItems);
	    
	    // Call the deleteProduct method with the specified product ID
	    cartitemservice.deleteProduct(productId);
	    
	    // Verify that the delete method was called for the CartItems with the specified product ID
	    Mockito.verify(cartitemdao, Mockito.times(2)).delete(Mockito.any(CartItem.class));
	}
   
	
	@Test
	public void testFetchByUserIdCart() {
	    // Create a mock CartItemDAO
	    CartItemDao cartItemDAO = Mockito.mock(CartItemDao.class);
	    
	    
	    // Create a mock User object
	    User user = new User();
	    user.setId(123);
	    
	    // Mock the getForObject method to return the User object
	    Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(User.class)))
	           .thenReturn(user);
	    
	    // Create a list of CartItems
	    List<CartItem> cartItems = new ArrayList<>();
	    cartItems.add(new CartItem(1,123L,new Product(1,"","","",100),5,false));
	    cartItems.add(new CartItem(1,123L,new Product(2,"","","",100),5,false));
	    
	    // Mock the findAllByuseridandInorder method to return the list of CartItems
	    Mockito.when(cartItemDAO.findAllByuseridandInorder(Mockito.eq(123L), Mockito.eq(false)))
	           .thenReturn(cartItems);
	    cartitemservice.fetchByUserIdCart(123L);
	    
	    // Verify that the RestTemplate and CartItemDAO methods were called with the correct arguments
	    Mockito.verify(cartitemdao).findAllByuseridandInorder(Mockito.eq(123L), Mockito.eq(false));
	    
	}
	
	@Test
	public void testOnCheckout()
	{
	    User user = new User();
	    user.setId(1);
	    user.setFirstname("John");

	    // Mock the getForObject method to return the User object
	    Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(User.class)))
	           .thenReturn(user);

	    // Create a list of CartItems
	    List<CartItem> cartItems = new ArrayList<>();
	    cartItems.add(new CartItem(1,1L,new Product(1,"","","",100),5,false));
	    cartItems.add(new CartItem(1,1L,new Product(1,"","","",100),5,false));

	    // Mock the findAllByuseridandInorder method to return the list of CartItems
	    Mockito.when(cartitemdao.findAllByuseridandInorder(Mockito.eq(1L), Mockito.eq(false)))
	           .thenReturn(cartItems);

	    // Call the Oncheckout method with the specified user ID
	    cartitemservice.Oncheckout(1L);
	    
	    Mockito.verify(cartitemdao, Mockito.times(cartItems.size())).save(Mockito.any(CartItem.class));



	    

	}
	
	
	
	@Test
	public void testAddProduct() {
	    

	   

	    // Create a mock User object
	    User user = new User();
	    user.setId(1L);
	    user.setFirstname("John");

	    // Mock the getForObject method to return the User object
	    Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(User.class)))
	           .thenReturn(user);

	    // Create a mock Product object
	    Product product = new Product();
	    product.setId(456);

	    // Create a list of CartItems
	    List<CartItem> cartItems = new ArrayList<>();
	    cartItems.add(new CartItem(1,1L,new Product(1,"","","",100),5,false));
	    cartItems.add(new CartItem(2,1L,new Product(456,"","","",100),5,false));

	    // Mock the findAllByCartProduct method to return the list of CartItems
	    Mockito.when(cartitemdao.findAllByCartProduct(1L, false, product))
	           .thenReturn(cartItems);

	    // Call the addProduct method with the specified Product and user ID
	    cartitemservice.addProduct(product, 1L);

	    Mockito.verify(cartitemdao,times(1)).findAllByCartProduct(1, false, product);
	    Mockito.verify(cartitemdao,times(1)).save(cartItems.get(1));
	    


	   


}
	
	@Test
	public void testnewCartItemAddProduct() {
	    

	   

	    // Create a mock User object
	    User user = new User();
	    user.setId(1L);
	    user.setFirstname("Omm");

	    // Mock the getForObject method to return the User object
	    Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(User.class)))
	           .thenReturn(user);

	    // Create a mock Product object
	    Product product = new Product();
	    product.setId(456);

	   

	    // Mock the findAllByCartProduct method to return the list of CartItems
	    Mockito.when(cartitemdao.findAllByCartProduct(1L, false, product))
	           .thenReturn(new ArrayList<CartItem>());
	    
	    CartItem c=new CartItem();
	    Mockito.when(cartitemdao.save(c))
        .thenReturn(c);

	    // Call the addProduct method with the specified Product and user ID
	    cartitemservice.addProduct(product, 1L);

	    Mockito.verify(cartitemdao,times(1)).findAllByCartProduct(1, false, product);
	    


	   


}
}
