package com.hackerrank.sample;


import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.hackerrank.sample.exception.NoSuchResourceFoundException;
import com.hackerrank.sample.customer.Customer;
import com.hackerrank.sample.customer.CustomerRepository;
import com.hackerrank.sample.customer.CustomerServiceImpl;
import com.hackerrank.sample.customer.CustomerService;

@RunWith(SpringRunner.class)
public class CustomerServiceTest {
	
	@TestConfiguration
	static class CustomerServiceImplTestContextConfiguration{
		
		@Bean
		public CustomerService customerService() {
			return new CustomerServiceImpl();
		}
	}
	
	@Autowired
	private CustomerService customerService;
	
	@MockBean
	private CustomerRepository customerRepository;
	
	Customer bobby;
	Customer joe;
	Customer updatedBobby;
	
	@Before
	public void setUp() {
		this.bobby = new Customer(1, "bob", 1553332222, "grove street", "male");
    	this.joe = new Customer(2, "joe", 166647725, "wallace woods", "male");
    	this.updatedBobby = new Customer(1, "bob", 1553332222, "grove street new york", "male");
    	
	    Mockito.when(customerRepository.findOne(1L))
	      .thenReturn(bobby);
	    Mockito.when(customerRepository.findAll())
	    	.thenReturn(Arrays.asList(joe, bobby));
	    Mockito.when(customerRepository.exists(1L))
	    	.thenReturn(true);
	    Mockito.when(customerRepository.save(bobby))
	      .thenReturn(bobby);
	    Mockito.when(customerRepository.save(updatedBobby))
	    	.thenReturn(updatedBobby);
	    Mockito.when(customerRepository.exists(64L)).thenReturn(true);
	}
	
//Create------------------------------------

	@Test
	public void customerAddTestPass() {
		assertThat(customerService.addCustomer(bobby)).isEqualTo(bobby);
	}
		
//Read---------------------------------------
	
	@Test
	public void customerReadAllTestPass() {
		assertThat(customerService.getCustomers()).containsOnly(bobby, joe);
	}
	
	@Test
	public void customerReadOneTestPass() {
		assertThat(customerService.getCustomer(1L)).isEqualTo(bobby);
	}
	
	@Test(expected = NoSuchResourceFoundException.class)
	public void customerReadAllTestfail() {
		Mockito.when(customerRepository.findAll()).thenReturn(new ArrayList<Customer>());
		customerService.getCustomers();
	}
	
	@Test(expected = NoSuchResourceFoundException.class)
	public void customerReadOneTestFail() {
		customerService.getCustomer(6);
	}
	
	
//Update--------------------------------------

	@Test(expected = NoSuchResourceFoundException.class)
	public void customerUpdateTestFail() {
		Customer c = new Customer();
		c.setCustomerId(5);
    	customerService.updateCustomer(c);
	}
	
	@Test
	public void customerUpdateTestPass() {
    	assertThat(customerService.updateCustomer(updatedBobby)).isEqualTo(updatedBobby);
	}
	

//Delete-------------------------------------
	
	
	@Test
	public void customerDeleteTest() {
		
		Customer c = new Customer(64, "bob", 1553332222, "grove street", "female");
		customerService.addCustomer(c);
		customerService.deleteCustomer(64);
	}
	
	
	@Test(expected = NoSuchResourceFoundException.class)
	public void customerDeleteDNETest() {
		customerService.deleteCustomer(88);
	}
	
	@Test
	public void deleteAllTest() {
		Customer c = new Customer(64, "bob", 1553332222, "grove street", "female");
		customerService.addCustomer(c);
		customerService.deleteAllCustomers();
	}
	
}
