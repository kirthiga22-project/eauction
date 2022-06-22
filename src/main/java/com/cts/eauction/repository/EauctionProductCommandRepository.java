package com.cts.eauction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.eauction.model.Product;

@Repository
public interface EauctionProductCommandRepository extends JpaRepository<Product, Integer>{
	

}
