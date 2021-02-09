package com.sl.ms.ordermgmtdocker.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.sl.ms.ordermgmtdocker.model.Order;
import com.sl.ms.ordermgmtdocker.model.OrderItems;
import com.sl.ms.ordermgmtdocker.repository.OrderRepository1;

@Service
@Transactional
@ComponentScan("com.sl.ms.ordermanagement1.repository")
public class OrderService {

	@Autowired
	private OrderRepository1 repo;

	public Optional<Order> listAllOrderItems(Integer orderid) {

		return repo.findById(orderid);

	}

	public List<Order> listAll() {
		return  repo.findAll();
	}

	public void save(Order order) {
		repo.save(order);
	}

	public Order get(Integer id) {
		return repo.findById(id).get();
	}

	public void delete(Integer id) {
		repo.deleteById(id);
	}

}
