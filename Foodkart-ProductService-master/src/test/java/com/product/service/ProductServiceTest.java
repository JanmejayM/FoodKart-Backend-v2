package com.product.service;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.product.dao.ProductDao;
import com.product.entity.Product;
import com.product.exception.ProductNotFoundException;



@RunWith(SpringRunner.class)
@SpringBootTest
class ProductServiceTest {
	
	
	@Autowired
	private ProductService productservice;
	
	
	@MockBean
	private ProductDao productdao;
	
	@Mock
	private RestTemplate restTemplate;

	@Test
	public void addProduct() {
		Product product=new Product();
		
		
		when(productdao.save(product)).thenReturn(product);
		//System.out.println(user.getFirstname());

		assertEquals(new ResponseEntity("Product Added",HttpStatus.OK),productservice.addProduct(product));
	}
	
	
		
	
	@Test
	public void fetchAllProducts() {
	
		
					Product product=new Product();
					product.setId(1);
					product.setName("Chowmein");
					product.setImage("fyfty");
					product.setPrice(100);
					product.setDescription("hffhhf");
					
					
					Product product1=new Product();
					
					product1.setId(1);
					product1.setName("in");
					product1.setImage("fty");
					product1.setPrice(100);
					product1.setDescription("hhf");

					
					when(productdao.findAll()).thenReturn(Stream.of(product,product1).collect(Collectors.toList()));
					
					assertEquals(2,productservice.fetch().size());
					

		    		
		    		
	}
	
	@Test
	public void fetchProductById() {
		Product product=new Product();
		
		
		when(productdao.save(product)).thenReturn(product);
		//System.out.println(user.getFirstname());
		
		Optional<Product> pro=Optional.of(new Product());
		
				


		Mockito.<Optional<Product>>when(productdao.findById(pro.get().getId())).thenReturn(pro);
		
		assertEquals(pro.get(),productservice.fetchById(pro.get().getId()));
		
		verify(productdao,times(1)).findById(pro.get().getId());

	}
	
	
	@Test
	public void fetchInvalidProductById() {
		Product product=new Product();
		
		
		when(productdao.save(product)).thenReturn(product);
		//System.out.println(user.getFirstname());
		
		

		when(productdao.findById(product.getId())).thenReturn(Optional.empty());
		
		Assertions.assertThrows(ProductNotFoundException.class, () -> {
			
			productservice.fetchById(product.getId());

        });
		
		verify(productdao,times(1)).findById(product.getId());
		
		
				




	}
	
	
	@Test
	public void updateProductSuccess()
	{

		

		
		Product product=new Product();
		product.setId(1);
		product.setName("Chowmein");
		product.setImage("fyfty");
		product.setPrice(100);
		product.setDescription("hffhhf");
		
		
		
		
		when(productdao.findById(product.getId())).thenReturn(Optional.of(product));
		
		
		productservice.updateProduct(product);
		
		verify(productdao,times(1)).save(product);
		verify(productdao,times(1)).findById(product.getId());
		
		
		
		
	}
	
	
	@Test
	public void updateProductFail()
	{

		

		
		Product product=new Product();
		product.setId(1);
		product.setName("Chowmein");
		product.setImage("fyfty");
		product.setPrice(100);
		product.setDescription("hffhhf");
		
		
		
		
		when(productdao.findById(product.getId())).thenReturn(Optional.empty());
		
		Assertions.assertThrows(ProductNotFoundException.class, () -> {
			
			productservice.updateProduct(product);

        });
		
		verify(productdao,times(0)).save(product);
		verify(productdao,times(1)).findById(product.getId());
		
		
		
		
	}
	
	@Test
	public void deleteInvalidProductById() {
		Product product=new Product();
		
		
		when(productdao.findById(product.getId())).thenReturn(Optional.empty());
		//System.out.println(user.getFirstname());
		
		
Assertions.assertThrows(ProductNotFoundException.class, () -> {
			
    productservice.deleteProduct(product.getId());

        });
		
				

		
		
		verify(productdao,times(1)).findById(product.getId());
		verify(productdao,times(0)).deleteById(product.getId());

	}
	
	
	@Test
	public void deleteValidProductById() {
		Product product=new Product();
		
		
		when(productdao.findById(product.getId())).thenReturn(Optional.of(product));
		//System.out.println(user.getFirstname());
		
		
			
    productservice.deleteProduct(product.getId());

		
				

		
		
		verify(productdao,times(1)).findById(product.getId());
		verify(productdao,times(1)).deleteById(product.getId());

	}
	

	
	
	
   
	
}
