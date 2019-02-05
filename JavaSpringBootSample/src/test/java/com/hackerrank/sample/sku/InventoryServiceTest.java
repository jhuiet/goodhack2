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

import com.tcs.hack.Exception.NoSuchResourceFoundException;

@RunWith(SpringRunner.class)
public class InventoryServiceTest {
	
	@TestConfiguration
	static class SkuServiceImplTestContextConfiguration{
		
		@Bean
		public SkuService skuService() {
			return new SkuServiceImpl();
		}
	}
	
	@Autowired
	private SkuService skuService;
	
	@MockBean
	private SkuRepository skuRepository;
	
	Sku sku1;
    Sku sku2;
    Sku sku1Updated;
    
    
	@Before
	public void setUp() {
		sku1 = new Sku("hammer", "good hammer", 22, 1, 12.42);
    	sku2 = new Sku("football", "good tossin", 42, 3, 6.99);
    	sku1.setSkuId(1);
    	sku2.setSkuId(2);
    	sku1Updated = new Sku("hammer", "really good hammer", 22, 1, 12.42);
    	sku1Updated.setSkuId(1);
    	
	    Mockito.when(skuRepository.findOne(1L))
	      .thenReturn(sku1);
	    Mockito.when(skuRepository.findAll())
	    	.thenReturn(Arrays.asList(sku2, sku1));
	    Mockito.when(skuRepository.exists(1L))
	    	.thenReturn(true);
	    Mockito.when(skuRepository.save(sku1))
	      .thenReturn(sku1);
	    Mockito.when(skuRepository.save(sku1Updated))
	    	.thenReturn(sku1Updated);
	    Mockito.when(skuRepository.exists(64L)).thenReturn(true);
	}
	
//Create------------------------------------

	@Test
	public void skuAddTestPass() {
		assertThat(skuService.addSku(sku1)).isEqualTo(sku1);
	}
		
//Read---------------------------------------
	
	@Test
	public void skuReadAllTestPass() {
		assertThat(skuService.getAllSku()).containsOnly(sku1, sku2);
	}
	
	@Test
	public void skuReadOneTestPass() {
		assertThat(skuService.getSku(1L)).isEqualTo(sku1);
	}
	
	@Test(expected = NoSuchResourceFoundException.class)
	public void skuReadAllTestfail() {
		Mockito.when(skuRepository.findAll()).thenReturn(new ArrayList<Sku>());
		skuService.getAllSku();
	}
	
	@Test(expected = NoSuchResourceFoundException.class)
	public void skuReadOneTestFail() {
		skuService.getSku(6);
	}
	
	
//Update--------------------------------------

	@Test(expected = NoSuchResourceFoundException.class)
	public void skuUpdateTestFail() {
		Sku c = new Sku();
		c.setSkuId(5);
    	skuService.updateSku(c);
	}
	
	@Test
	public void skuUpdateTestPass() {
    	assertThat(skuService.updateSku(sku1Updated)).isEqualTo(sku1Updated);
	}
	

//Delete-------------------------------------
	
	
	@Test
	public void skuDeleteTest() {
		
//		Sku c = new Sku(1L, "sku1", 5005005000L, "sku1@google.com", "wall-g", "west sidddeeee!");
		skuService.addSku(sku1);
		skuService.deleteSku(1);
	}
	
	
	@Test(expected = NoSuchResourceFoundException.class)
	public void skuDeleteDNETest() {
		skuService.deleteSku(88);
	}
	
	@Test
	public void deleteAllTest() {
//		Sku c = new Sku("hammer", "good hammer", 22, 1, 12.42);
//		Sku c = new Sku(1L, "cocacola", 5005005000L, "cocacola@google.com", "coke", "murica!");
		skuService.addSku(sku1);
		skuService.deleteAllSku();
	}
	
}
