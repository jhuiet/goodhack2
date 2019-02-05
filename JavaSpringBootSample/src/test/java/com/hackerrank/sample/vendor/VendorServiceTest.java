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
import com.tcs.hack.vendor.Vendor;
import com.tcs.hack.vendor.VendorRepository;
import com.tcs.hack.vendor.VendorService;
import com.tcs.hack.vendor.VendorServiceImpl;

@RunWith(SpringRunner.class)
public class VendorServiceTest {
	
	@TestConfiguration
	static class VendorServiceImplTestContextConfiguration{
		
		@Bean
		public VendorService vendorService() {
			return new VendorServiceImpl();
		}
	}
	
	@Autowired
	private VendorService vendorService;
	
	@MockBean
	private VendorRepository vendorRepository;
	
	Vendor walgreens;
	Vendor joesCrabShack;
	Vendor updatedWalgreens;
	
	@Before
	public void setUp() {
		this.walgreens = new Vendor(1L, "walgreens", 5005005000L, "walgreens@google.com", "wall-g", "west sidddeeee!");
    	this.joesCrabShack = new Vendor("Joes crab shack", 166647725L, "crabbs@gmail.com", "crustyc", "north beach");
    	joesCrabShack.setVendorId(2L);
    	this.updatedWalgreens = new Vendor(1L, "walgreens", 5005005000L, "walgreens@google.com", "wall-g", "now East sidddeeee!");
    	
	    Mockito.when(vendorRepository.findOne(1L))
	      .thenReturn(walgreens);
	    Mockito.when(vendorRepository.findAll())
	    	.thenReturn(Arrays.asList(joesCrabShack, walgreens));
	    Mockito.when(vendorRepository.exists(1L))
	    	.thenReturn(true);
	    Mockito.when(vendorRepository.save(walgreens))
	      .thenReturn(walgreens);
	    Mockito.when(vendorRepository.save(updatedWalgreens))
	    	.thenReturn(updatedWalgreens);
	    Mockito.when(vendorRepository.exists(64L)).thenReturn(true);
	}
	
//Create------------------------------------

	@Test
	public void vendorAddTestPass() {
		assertThat(vendorService.addVendor(walgreens)).isEqualTo(walgreens);
	}
		
//Read---------------------------------------
	
	@Test
	public void vendorReadAllTestPass() {
		assertThat(vendorService.getAllVendors()).containsOnly(walgreens, joesCrabShack);
	}
	
	@Test
	public void vendorReadOneTestPass() {
		assertThat(vendorService.getVendor(1L)).isEqualTo(walgreens);
	}
	
	@Test(expected = NoSuchResourceFoundException.class)
	public void vendorReadAllTestfail() {
		Mockito.when(vendorRepository.findAll()).thenReturn(new ArrayList<Vendor>());
		vendorService.getAllVendors();
	}
	
	@Test(expected = NoSuchResourceFoundException.class)
	public void vendorReadOneTestFail() {
		vendorService.getVendor(6);
	}
	
	
//Update--------------------------------------

	@Test(expected = NoSuchResourceFoundException.class)
	public void vendorUpdateTestFail() {
		Vendor c = new Vendor();
		c.setVendorId(5);
    	vendorService.updateVendor(c);
	}
	
	@Test
	public void vendorUpdateTestPass() {
    	assertThat(vendorService.updateVendor(updatedWalgreens)).isEqualTo(updatedWalgreens);
	}
	

//Delete-------------------------------------
	
	
	@Test
	public void vendorDeleteTest() {
		
//		Vendor c = new Vendor(1L, "walgreens", 5005005000L, "walgreens@google.com", "wall-g", "west sidddeeee!");
		vendorService.addVendor(walgreens);
		vendorService.deleteVendor(1);
	}
	
	
	@Test(expected = NoSuchResourceFoundException.class)
	public void vendorDeleteDNETest() {
		vendorService.deleteVendor(88);
	}
	
	@Test
	public void deleteAllTest() {
//		Vendor c = new Vendor(64, "bob", 1553332222, "grove street", "female");
		Vendor c = new Vendor(1L, "cocacola", 5005005000L, "cocacola@google.com", "coke", "murica!");
		vendorService.addVendor(c);
		vendorService.deleteAllVendors();
	}
	
}
