package com.hackerrank.sample.customer;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

	@Autowired
	private CustomerService customerService;


//Create--------------------------------
	
	@RequestMapping(method = RequestMethod.POST, value = "/customer", consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Customer addCustomer(@RequestBody @Valid Customer customer){
		Customer savedCustomer = customerService.addCustomer(customer);
		return savedCustomer;
	}
	
//Read--------------------------------------
	
	@RequestMapping(method = RequestMethod.GET, value = "/customer")
	@ResponseStatus(HttpStatus.OK)
	public List<Customer> getCustomers(){
		return customerService.getCustomers();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/customer/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Customer getCustomerById(@PathVariable long id){
		return customerService.getCustomer(id);
	}

//update--------------------------------------

	@RequestMapping(method = RequestMethod.PUT, value = "/customer/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Customer updateCustomer(@PathVariable long id, @RequestBody @Valid Customer customer){
		customer.setCustomerId(id);
		return customerService.updateCustomer(customer);
	}

//Delete------------------------------------------
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/customer/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteCustomer(@PathVariable long id) {
		customerService.deleteCustomer(id);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/customer")
	@ResponseStatus(HttpStatus.OK)
	public void deleteAllCustomers() {
		customerService.deleteAllCustomers();
	}
	
	
}
