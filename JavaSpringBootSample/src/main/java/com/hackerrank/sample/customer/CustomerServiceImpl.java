package com.hackerrank.sample.customer;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackerrank.sample.exception.NoSuchResourceFoundException;

@Service("CustomerService")
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

//Create--------------------------------------
	
	public Customer addCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

//Read----------------------------------------------	

	public List<Customer> getCustomers(){
		List<Customer> customers = new ArrayList<>();
		customerRepository.findAll()
			.forEach(c -> customers.add(c));
		if(customers.isEmpty()) {
			throw new NoSuchResourceFoundException("No Customers found.");
		}
		return customers;
	}
	
	public Customer getCustomer(long id) {
		Customer customer =	customerRepository.findOne(id);
		if(customer == null)
			throw new NoSuchResourceFoundException("Customer not found");
		return customer;
	}
	
//Update----------------------------------------------

	public Customer updateCustomer(Customer customer) {
		if(customerRepository.exists(customer.getCustomerId())) 
			return customerRepository.save(customer);
		else
			throw new NoSuchResourceFoundException("Customer does not exist!");
	}
	
//Delete-------------------------------------------------
	
	public void deleteCustomer(long id) {
		if(customerRepository.exists(id)) 
			customerRepository.delete(id);
		else 
			throw new NoSuchResourceFoundException("Customer does not exist!");
	}

	public void deleteAllCustomers() {
		customerRepository.deleteAll();
	}
}
