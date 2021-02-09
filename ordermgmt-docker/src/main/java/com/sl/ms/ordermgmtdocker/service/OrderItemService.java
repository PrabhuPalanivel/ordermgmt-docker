package com.sl.ms.ordermgmtdocker.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.sl.ms.ordermgmtdocker.model.Order;
import com.sl.ms.ordermgmtdocker.model.OrderItems;
import com.sl.ms.ordermgmtdocker.repository.OrderItemsRepository1;
@Service
@Transactional
@ComponentScan("com.sl.ms.ordermanagement1.repository")

public class OrderItemService {
	
	@Autowired
	private OrderItemsRepository1 orderitemsrepo;

		
	  public List<OrderItems> listAllOrderItems(Integer orderid) {
	  //List<OrderItems> OrderItems = new ArrayList<>(); 
	   return  orderitemsrepo.findByOrderid(orderid);
	  
	  }
	 
	
	
	
	public List<OrderItems> listAll() {
		return orderitemsrepo.findAll();
	}

	public void save(OrderItems orderitems) {
		orderitemsrepo.save(orderitems);
	}


    public OrderItems get(Integer id) {
        return orderitemsrepo.findById(id).get();
    }

	public void delete(Integer id) {
		orderitemsrepo.deleteById(id);
	}
}
