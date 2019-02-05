package com.hackerrank.sample;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.hackerrank.sample.exception.NoSuchResourceFoundException;
import com.hackerrank.sample.vendor.Vendor;
import com.hackerrank.sample.vendor.VendorController;
import com.hackerrank.sample.vendor.VendorService;

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
@WebMvcTest(VendorController.class)
public class VendorControllerTest  {

	
	
	@Autowired
	private MockMvc mockMvc;
	
    @MockBean
    private VendorService service;

    

    //String exampleInput = "{\"vendorName\":\"bob\",\"contactNumber\":1553332222,\"address\":\"grove street new york\",\"gender\":\"male\"}";
    String exampleInput = "{\"vendorId\":0,\"vendorName\":\"Joes crab shack\",\"vendorContactNo\":166647725,\"vendorEmail\":\"crabbs@gmail.com\",\"vendorUsername\":\"crustyc\",\"vendorAddress\":\"north beach\"}";
    
    Vendor walgreens;
	Vendor joesCrabShack;
	Vendor updatedWalgreens;
    
    @Before
    public void setUp() {
    	this.walgreens = new Vendor(1L, "walgreens", 5005005000L, "walgreens@google.com", "wall-g", "west sidddeeee!");
    	this.joesCrabShack = new Vendor("Joes crab shack", 166647725L, "crabbs@gmail.com", "crustyc", "north beach");
    	joesCrabShack.setVendorId(2L);
    	this.updatedWalgreens = new Vendor(1L, "walgreens", 5005005000L, "walgreens@google.com", "wall-g", "now East sidddeeee!");
    	
    }
    
    
private long vendorId;
	
	private String vendorName;
	private Long vendorContactNo;
	private String vendorEmail;
	private String vendorUsername;
	private String vendorAddress;
	
    
    @Test
    public void getAllVendorsTest_success200() throws Exception {
    	List<Vendor> allVendors = Arrays.asList(joesCrabShack, walgreens);
    	
    	when(service.getAllVendors()).thenReturn(allVendors);
    	
    	mockMvc.perform(get("/vendor"))
    		.andExpect(status().isOk())
    		.andExpect(jsonPath("$", hasSize(2)))
    		.andExpect(jsonPath("$[1].vendorId", is(1)))
    		.andExpect(jsonPath("$[1].vendorName", is("walgreens")))
    		.andExpect(jsonPath("$[1].vendorContactNo", is(5005005000L)))
    		.andExpect(jsonPath("$[1].vendorEmail", is("walgreens@google.com")))
    		.andExpect(jsonPath("$[1].vendorUsername", is("wall-g")))   		
    		.andExpect(jsonPath("$[1].vendorAddress", is( "west sidddeeee!")))
    		.andExpect(jsonPath("$[0].vendorId", is(2)))
    		.andExpect(jsonPath("$[0].vendorName", is("Joes crab shack")))
    		.andExpect(jsonPath("$[0].vendorContactNo", is(166647725)))
    		.andExpect(jsonPath("$[0].vendorEmail", is("crabbs@gmail.com")))
    		.andExpect(jsonPath("$[0].vendorUsername", is("crustyc")))   		
    		.andExpect(jsonPath("$[0].vendorAddress", is("north beach")));
    }
 
    
    @Test
    public void getAllVendorsTest2_fail404() throws Exception {
    	when(service.getAllVendors()).thenThrow(NoSuchResourceFoundException.class);
    	mockMvc.perform(get("/vendor"))
    		.andExpect(status().isNotFound());
    }
    
    @Test
    public void getVendorByIdTest_success200() throws Exception {
    	
    	when(service.getVendor(1)).thenReturn(walgreens);
    	mockMvc.perform(get("/vendor/1"))
    		.andExpect(status().isOk())
    		.andExpect(jsonPath("$.vendorId", is(1)))
    		.andExpect(jsonPath("$.vendorName", is("walgreens")))
    		.andExpect(jsonPath("$.vendorContactNo", is(5005005000L)))
    		.andExpect(jsonPath("$.vendorEmail", is("walgreens@google.com")))
    		.andExpect(jsonPath("$.vendorUsername", is("wall-g")))   		
    		.andExpect(jsonPath("$.vendorAddress", is( "west sidddeeee!")));
    }
    
    @Test
    public void getVendorByIdTest_fail404() throws Exception {
    	when(service.getVendor(1)).thenThrow(NoSuchResourceFoundException.class);
  
    	mockMvc.perform(get("/vendor/1", 1L))
    		.andExpect(status().isNotFound());
    	 verify(service, times(1)).getVendor(1L);
         verifyNoMoreInteractions(service);
    }
    
    
    @Test
    public void createVendorTest_success201() throws Exception{
       	when(service.addVendor(Mockito.any(Vendor.class))).thenReturn(walgreens);    
       	mockMvc.perform(post("/vendor")
       			.contentType(MediaType.APPLICATION_JSON)
                .content(exampleInput)
                )
       	.andExpect(status().isCreated())
       	.andExpect(jsonPath("$.vendorId", is(1))); 
    }
    
    
    @Test
    public void deleteVendorTest_success200() throws Exception {
    	mockMvc.perform(MockMvcRequestBuilders
    		.delete("/vendor/{id}", "1")
    		.contentType(MediaType.APPLICATION_JSON))
    		.andExpect(status().isOk()); 	
    }
  
    @Test
    public void deleteVendor_doesNotExist404() throws Exception {
    	doThrow(NoSuchResourceFoundException.class).when(service).deleteVendor(1);
    	mockMvc.perform(MockMvcRequestBuilders
    		.delete("/vendor/{id}", "1")
    		.contentType(MediaType.APPLICATION_JSON))
    		.andExpect(status().isNotFound());		
    }
    
    @Test
    public void deleteAllVendorsTest_success200() throws Exception {
    	mockMvc.perform(MockMvcRequestBuilders
        		.delete("/vendor"))
        		.andExpect(status().isOk()); 
    }
    
    @Test 
    public void updateVendor_doesNotExist() throws Exception{
    	when(service.updateVendor(Mockito.any(Vendor.class))).thenThrow(NoSuchResourceFoundException.class);
    	
    	mockMvc.perform(MockMvcRequestBuilders
    		.put("/vendor/{id}", "1")
    		.contentType(MediaType.APPLICATION_JSON)
    		.content(exampleInput)).andExpect(status().isNotFound());
    }
    
    @Test 
    public void updateVendor_success200() throws Exception{
    	when(service.updateVendor(Mockito.any(Vendor.class))).thenReturn(updatedWalgreens);
    	mockMvc.perform(MockMvcRequestBuilders
    		.put("/vendor/{id}", "1")
    		.contentType(MediaType.APPLICATION_JSON)
    		.content(exampleInput)).andExpect(status().isOk());
    }
    
}