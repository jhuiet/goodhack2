package com.hackerrank.sample;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.tcs.hack.Exception.NoSuchResourceFoundException;
import com.tcs.hack.customer.Customer;
import com.tcs.hack.customer.CustomerController;
import com.tcs.hack.customer.CustomerService;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

	
	
	@Autowired
	private MockMvc mockMvc;
//	mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    @MockBean
    private CustomerService service;

    

    String exampleInput = "{\"customerName\":\"bob\",\"contactNumber\":1553332222,\"address\":\"grove street new york\",\"gender\":\"male\"}";
    
    
    Customer bobby;
    Customer joe;
    Customer bobbyUpdated;
    
    @Before
    public void setUp() {
    	bobby = new Customer(1, "bob", 1553332222, "grove street", "male");
    	joe = new Customer("joe", 166647725, "wallace woods", "male");
    	bobbyUpdated =  new Customer(1, "bob", 1553332222, "grove street new york", "male");
    	joe.setCustomerId(2L);
    }
    
    
    @Test
    public void getAllCustomersTest_success200() throws Exception {
    	List<Customer> allCustomers = Arrays.asList(joe, bobby);
    	
    	when(service.getCustomers()).thenReturn(allCustomers);
    	
    	mockMvc.perform(get("/customer"))
    		.andExpect(status().isOk())
    		.andExpect(jsonPath("$", hasSize(2)))
    		.andExpect(jsonPath("$[1].customerId", is(1)))
    		.andExpect(jsonPath("$[1].customerName", is("bob")))
    		.andExpect(jsonPath("$[1].contactNumber", is(1553332222)))
    		.andExpect(jsonPath("$[1].address", is("grove street")))
    		.andExpect(jsonPath("$[1].gender", is("male")))
    		.andExpect(jsonPath("$[0].customerId", is(2)))
    		.andExpect(jsonPath("$[0].customerName", is("joe")))
    		.andExpect(jsonPath("$[0].contactNumber", is(166647725)))
    		.andExpect(jsonPath("$[0].address", is("wallace woods")))
    		.andExpect(jsonPath("$[0].gender", is("male")));
    }
    
    @Test
    public void getAllCustomersTest2_fail404() throws Exception {
    	when(service.getCustomers()).thenThrow(NoSuchResourceFoundException.class);
    	mockMvc.perform(get("/customer"))
    		.andExpect(status().isNotFound());
    }
    
    @Test
    public void getCustomerByIdTest_success200() throws Exception {
    	Customer bobby = new Customer(1, "bob", 1553332222, "grove street", "male");
    	
    	when(service.getCustomer(1)).thenReturn(bobby);
    	mockMvc.perform(get("/customer/1"))
    		.andExpect(status().isOk())
    		.andExpect(jsonPath("$.customerId", is(1)))
    		.andExpect(jsonPath("$.customerName", is("bob")))
    		.andExpect(jsonPath("$.contactNumber", is(1553332222)))
    		.andExpect(jsonPath("$.address", is("grove street")))
    		.andExpect(jsonPath("$.gender", is("male")));
    }
    
    @Test
    public void getCustomerByIdTest_fail404() throws Exception {
    	when(service.getCustomer(1)).thenThrow(NoSuchResourceFoundException.class);
  
    	mockMvc.perform(get("/customer/1", 1L))
    		.andExpect(status().isNotFound());
    	 verify(service, times(1)).getCustomer(1L);
         verifyNoMoreInteractions(service);
    }
    
    
    @Test
    public void createCustomerTest_success201() throws Exception{
    	Customer bobby = new Customer(1,"bob", 1553332222, "grove street", "male");
       	when(service.addCustomer(Mockito.any(Customer.class))).thenReturn(bobby);
        
       	mockMvc.perform(post("/customer")
       			.contentType(MediaType.APPLICATION_JSON)
                .content(exampleInput)
                )
       	.andExpect(status().isCreated())
       	.andExpect(jsonPath("$.customerId", is(1))); 
    }
    
    
    @Test
    public void deleteCustomerTest_success200() throws Exception {
//    	when(service.deleteCustomer(Mockito.any(Long.class)));
    	mockMvc.perform(MockMvcRequestBuilders
    		.delete("/customer/{id}", "1")
    		.contentType(MediaType.APPLICATION_JSON))
    		.andExpect(status().isOk()); 	
    }
  
    @Test
    public void deleteCustomer_doesNotExist404() throws Exception {
    	doThrow(NoSuchResourceFoundException.class).when(service).deleteCustomer(1);
    	mockMvc.perform(MockMvcRequestBuilders
    		.delete("/customer/{id}", "1")
    		.contentType(MediaType.APPLICATION_JSON))
    		.andExpect(status().isNotFound());		
    }
    
    @Test
    public void deleteAllCustomersTest_success200() throws Exception {
    	mockMvc.perform(MockMvcRequestBuilders
        		.delete("/customer"))
        		.andExpect(status().isOk()); 
    }
    
    @Test 
    public void updateCustomer_doesNotExist() throws Exception{
    	when(service.updateCustomer(Mockito.any(Customer.class))).thenThrow(NoSuchResourceFoundException.class);
    	
    	mockMvc.perform(MockMvcRequestBuilders
    		.put("/customer/{id}", "1")
    		.contentType(MediaType.APPLICATION_JSON)
    		.content(exampleInput)).andExpect(status().isNotFound());
    }
    
    @Test 
    public void updateCustomer_success200() throws Exception{
    	when(service.updateCustomer(Mockito.any(Customer.class))).thenReturn(bobbyUpdated);
    	//todo do these tests work or are they nullified by the service stubbing? 
    	//should I test anything else in the controller?
    	mockMvc.perform(MockMvcRequestBuilders
    		.put("/customer/{id}", "1")
    		.contentType(MediaType.APPLICATION_JSON)
    		.content(exampleInput)).andExpect(status().isOk());
    }
    
}