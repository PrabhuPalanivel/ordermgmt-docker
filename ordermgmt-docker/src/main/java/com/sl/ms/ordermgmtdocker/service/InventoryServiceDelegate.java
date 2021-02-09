package com.sl.ms.ordermgmtdocker.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sl.ms.ordermgmtdocker.model.Prodt;

@Service
public class InventoryServiceDelegate {

	
	ObjectMapper objectMapper = new ObjectMapper();
RestTemplate restTemplate;
 
@SuppressWarnings("unchecked")
@HystrixCommand(fallbackMethod = "callInventoryServiceAndGetData_Fallback")
    public String callInventoryServiceAndGetData() 	 {
	 List<Prodt> prodt;
	
	 prodt= (List<Prodt>) restTemplate.getForObject("http://localhost:8888/Dev/supported-products",Prodt.class);
	 System.out.println("Result:"+prodt.toString());
	 return "Able to execute the service and result is displayed";
}
	public String callInventoryServiceAndGetData_Fallback() {
	
	
		System.out.println("Inventory Service is down!!! fallback route enabled...");
        return "Looks like service unavailable. Please try later.";

                   
		
	}

}
