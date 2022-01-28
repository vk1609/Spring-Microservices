package com.cg.example;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;





@RestController
public class ClientApplication {
	
	@Autowired
	RestTemplate resttemplate;

	 @Bean
	public RestTemplate restTemplate() {
	return new RestTemplate();
	}

	 @Autowired
	private LoadBalancerClient loadBalancer;

	 @RequestMapping("/client2")
	public ResponseEntity<?> client() {
	ServiceInstance serviceInstance = loadBalancer.choose("Eureka-Client");
	System.out.println(serviceInstance.getUri());
	URI uri = serviceInstance.getUri();
	String message = resttemplate.getForObject(uri + "/hello", String.class);
	return new ResponseEntity<String>(message, HttpStatus.OK);

	 }

}

