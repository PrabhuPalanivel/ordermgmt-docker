package com.sl.ms.ordermgmtdocker.controller;


import java.util.*;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;



import com.sl.ms.ordermgmtdocker.model.Order;
import com.sl.ms.ordermgmtdocker.model.OrderItems;
import com.sl.ms.ordermgmtdocker.model.Prodt;
import com.sl.ms.ordermgmtdocker.service.InventoryServiceDelegate;
import com.sl.ms.ordermgmtdocker.service.OrderItemService;
import com.sl.ms.ordermgmtdocker.service.OrderService;

import brave.sampler.Sampler;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin()
public class OrderController {

	
	
	@Autowired
	private OrderService service;

	@Autowired
	private OrderItemService itemservice;
	
	@Autowired
	private InventoryServiceDelegate inventoryServiceDelegate;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping(value="/rest/hello/client")
	public String hello()
	{
		
		String response = restTemplate.getForObject("http://localhost:8833/rest/hello/server", String.class);
		System.out.println("response"+response);
		logger.info("response from http://localhost:8833/rest/hello/server");
		return response;
		
	}

 
    @RequestMapping(value = "/getProductInv", method = RequestMethod.GET)
    public  String getProductInv() {
        System.out.println("Going to call inventory service to get data!");
        return inventoryServiceDelegate.callInventoryServiceAndGetData();
    }

	
	
	// RESTful API methods for Retrieval operations
	@GetMapping("/orders")
	public List<Order> list() {
		logger.info("executing getmapping /orders");
		return service.listAll();
	}

	/*
	 * @GetMapping("/orders/{id}") public List<OrderItems> get(@PathVariable Integer
	 * id) {
	 * 
	 * return itemservice.listAllOrderItems(id);
	 * 
	 * 
	 * }
	 */

	@GetMapping("/orders/{id}")
	public Optional<Order> get(@PathVariable Integer id) {
		logger.info("executing getmapping /orders/{id}");
		return service.listAllOrderItems(id);

	}

	/*
	 * @GetMapping("/orders/{id}") public ResponseEntity<OrderItems>
	 * get(@PathVariable Integer id) { try { List<OrderItems> orderItems =
	 * itemservice.listAllOrderItems(id);
	 * 
	 * return new ResponseEntity<OrderItems>((OrderItems) orderItems,
	 * HttpStatus.OK); } catch (NoSuchElementException e) { return new
	 * ResponseEntity<OrderItems>(HttpStatus.NOT_FOUND); } }
	 */

	// RESTful API method for Create operation

	@PostMapping("/orders")
	public void add(@RequestBody Order order)  {
	
			service.save(order);
	}

	// RESTful API method for Update operation

	@PutMapping("/orders/{id}")
	public ResponseEntity<?> update(@RequestBody Order order, @PathVariable Integer id) {
		try {
			logger.info("executing putmapping /orders/{id}");
			Order existOrder = service.get(id);
			service.save(order);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// RESTful API method for Delete operation
	@DeleteMapping("/orders/{id}")
	public void delete(@PathVariable Integer id) {
		logger.info("executing deletemapping /orders/{id}");
		service.delete(id);
	}

}
