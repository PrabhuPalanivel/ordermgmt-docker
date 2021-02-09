package com.sl.ms.ordermgmtdocker.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.sl.ms.ordermgmtdocker.model.Order;


@Repository
public interface OrderRepository1 extends JpaRepository<Order, Integer> {
	public Optional<Order> findById(Integer id);
}