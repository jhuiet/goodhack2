package com.hackerrank.sample.customer;

import java.util.List;

public interface CustomerService {

	public List<Customer> getCustomers();
	
	public Customer getCustomer(long id);
	
	public Customer addCustomer(Customer customer);
	
	public Customer updateCustomer(Customer customer);
	
	public void deleteCustomer(long id);
	
	public void deleteAllCustomers();
}
