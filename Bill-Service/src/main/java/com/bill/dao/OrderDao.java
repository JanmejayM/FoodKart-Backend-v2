package com.bill.dao;

import java.util.Calendar;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bill.entity.Order;

@Repository
public interface OrderDao extends JpaRepository<Order,Long>{
 
	
	

}
