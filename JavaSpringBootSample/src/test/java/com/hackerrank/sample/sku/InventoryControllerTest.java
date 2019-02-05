package com.hackerrank.sample;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.hackerrank.sample.exception.NoSuchResourceFoundException;
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
@WebMvcTest(SkuController.class)
public class InventoryControllerTest  {

	
	
	@Autowired
	private MockMvc mockMvc;
	
    @MockBean
    private SkuService service;

    

    Sku sku1;
    Sku sku2;
    Sku sku1Updated;
    String exampleInput = "{\"productName\":\"hammer\",\"productLabel\":\"really good hammer\",\"inventoryOnHand\":22,\"minQtyReq\":1,\"price\":12.42}";
    
    @Before
    public void setUp() {
    	sku1 = new Sku("hammer", "good hammer", 22, 1, 12.42);
    	sku2 = new Sku("football", "good tossin", 42, 3, 6.99);
    	sku1.setSkuId(1);
    	sku2.setSkuId(2);
    	sku1Updated = new Sku("hammer", "really good hammer", 22, 1, 12.42);
    	sku1Updated.setSkuId(1);
    	
    }
    
 
    
    @Test
    public void getAllSkusTest_success200() throws Exception {
    	List<Sku> allSkus = Arrays.asList(sku2, sku1);
    	
    	when(service.getAllSku()).thenReturn(allSkus);
    	
    	mockMvc.perform(get("/item"))
    		.andExpect(status().isOk())
    		.andExpect(jsonPath("$", hasSize(2)));
//    		.andExpect(jsonPath("$[1].skuId", is(1)))
//    		.andExpect(jsonPath("$[1].skuName", is("sku1")))
//    		.andExpect(jsonPath("$[1].skuContactNo", is(5005005000L)))
//    		.andExpect(jsonPath("$[1].skuEmail", is("sku1@google.com")))
//    		.andExpect(jsonPath("$[1].skuUsername", is("wall-g")))   		
//    		.andExpect(jsonPath("$[1].skuAddress", is( "west sidddeeee!")))
//    		.andExpect(jsonPath("$[0].skuId", is(2)))
//    		.andExpect(jsonPath("$[0].skuName", is("Joes crab shack")))
//    		.andExpect(jsonPath("$[0].skuContactNo", is(166647725)))
//    		.andExpect(jsonPath("$[0].skuEmail", is("crabbs@gmail.com")))
//    		.andExpect(jsonPath("$[0].skuUsername", is("crustyc")))   		
//    		.andExpect(jsonPath("$[0].skuAddress", is("north beach")));
    }
 
    
    @Test
    public void getAllSkusTest2_fail404() throws Exception {
    	when(service.getAllSku()).thenThrow(NoSuchResourceFoundException.class);
    	mockMvc.perform(get("/item"))
    		.andExpect(status().isNotFound());
    }
    
    @Test
    public void getSkuByIdTest_success200() throws Exception {
    	
    	when(service.getSku(1)).thenReturn(sku1);
    	mockMvc.perform(get("/item/1"))
    		.andExpect(status().isOk());
//    		.andExpect(jsonPath("$.skuId", is(1)))
//    		.andExpect(jsonPath("$.skuName", is("sku1")))
//    		.andExpect(jsonPath("$.skuContactNo", is(5005005000L)))
//    		.andExpect(jsonPath("$.skuEmail", is("sku1@google.com")))
//    		.andExpect(jsonPath("$.skuUsername", is("wall-g")))   		
//    		.andExpect(jsonPath("$.skuAddress", is( "west sidddeeee!")));
    }
    
    @Test
    public void getSkuByIdTest_fail404() throws Exception {
    	when(service.getSku(1)).thenThrow(NoSuchResourceFoundException.class);
  
    	mockMvc.perform(get("/item/1", 1L))
    		.andExpect(status().isNotFound());
    	 verify(service, times(1)).getSku(1L);
         verifyNoMoreInteractions(service);
    }
    
    
    @Test
    public void createSkuTest_success201() throws Exception{
       	when(service.addSku(Mockito.any(Sku.class))).thenReturn(sku1);    
       	mockMvc.perform(post("/item")
       			.contentType(MediaType.APPLICATION_JSON)
                .content(exampleInput)
                )
       	.andExpect(status().isCreated())
       	.andExpect(jsonPath("$.skuId", is(1))); 
    }
    
    
    @Test
    public void deleteSkuTest_success200() throws Exception {
    	mockMvc.perform(MockMvcRequestBuilders
    		.delete("/item/{id}", "1")
    		.contentType(MediaType.APPLICATION_JSON))
    		.andExpect(status().isOk()); 	
    }
  
    @Test
    public void deleteSku_doesNotExist404() throws Exception {
    	doThrow(NoSuchResourceFoundException.class).when(service).deleteSku(1);
    	mockMvc.perform(MockMvcRequestBuilders
    		.delete("/item/{id}", "1")
    		.contentType(MediaType.APPLICATION_JSON))
    		.andExpect(status().isNotFound());		
    }
    
    @Test
    public void deleteAllSkusTest_success200() throws Exception {
    	mockMvc.perform(MockMvcRequestBuilders
        		.delete("/item"))
        		.andExpect(status().isOk()); 
    }
    
    @Test 
    public void updateSku_doesNotExist() throws Exception{
    	when(service.updateSku(Mockito.any(Sku.class))).thenThrow(NoSuchResourceFoundException.class);
    	
    	mockMvc.perform(MockMvcRequestBuilders
    		.put("/item/{id}", "1")
    		.contentType(MediaType.APPLICATION_JSON)
    		.content(exampleInput)).andExpect(status().isNotFound());
    }
    
    @Test 
    public void updateSku_success200() throws Exception{
    	when(service.updateSku(Mockito.any(Sku.class))).thenReturn(sku1Updated);
    	mockMvc.perform(MockMvcRequestBuilders
    		.put("/item/{id}", "1")
    		.contentType(MediaType.APPLICATION_JSON)
    		.content(exampleInput)).andExpect(status().isOk());
    }
    
}