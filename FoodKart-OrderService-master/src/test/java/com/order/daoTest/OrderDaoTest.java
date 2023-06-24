package com.order.daoTest;

import static org.assertj.core.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.order.dao.OrderDao;
import com.order.entity.Order;
import com.order.utils.OrderDetails;

@SpringBootTest
class OrderDaoTest {

	@Autowired
	OrderDao orderdao;
	

	@Test
	void OneDaySale() {
		
		Date day = new Date(2000, 11, 21);
        
		
		Order order1= new  Order(91l, "jgjkgjkkjgkj", "fhhgfhgfgh", day, 100l,null);
		Order order2=new  Order(92l, "fhfjhggfhgf", "dgdgdghdgfdg", day, 100l,null);
		
       
		Order d=orderdao.save(order1);	
		
       Order e=orderdao.save(order2);
       
       
       List<Order>or=new ArrayList<>();
       or.add(e);
       or.add(d);
        
        
        		
        		List<Order>all=new ArrayList<>();
        orderdao.getAllOrderBydate(day).stream().forEach(o->{
        	all.add(o);
        });
        
        orderdao.delete(d);
        orderdao.delete(e);
       
  
     
        assertEquals(or.size(), all.size());
        
        
       
     

        
	
		
	}
	
	
	@Test
	void BetweenDaySale() {
		
		Date date1 = new Date(2000, 11, 21);
		Date date2 = new Date(2000, 11, 23);
	
        
		
		Order order1= new  Order(91l, "dytdydhdh", "fhghghgfhff", date1, 100l,null);
		Order order2=new  Order(92l, "ghjjhjffhhjf", "dgdghdgdgddghgd", date2, 100l,null);
		
       
		Order d=orderdao.save(order1);	
		
       Order e=orderdao.save(order2);
       
       
       List<Order>or=new ArrayList<>();
       or.add(e);
       or.add(d);
        
        
        		
        		List<Order>all=new ArrayList<>();
        orderdao.getAllOrderBetweendate(date1, date2).stream().forEach(o->{
        	all.add(o);
        });
        
        orderdao.delete(d);
        orderdao.delete(e);
       
  
     
        assertEquals(or.size(), all.size());
        
        
       
     

        
	
		
	}
	

}
