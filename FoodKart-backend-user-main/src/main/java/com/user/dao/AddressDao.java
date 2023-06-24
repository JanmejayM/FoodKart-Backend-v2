package com.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.entity.Address;

@Repository
public interface AddressDao extends JpaRepository<Address,Long>{

}
