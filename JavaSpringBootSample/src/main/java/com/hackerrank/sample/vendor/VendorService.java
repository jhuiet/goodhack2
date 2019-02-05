package com.hackerrank.sample.vendor;


import java.util.List;
import org.springframework.stereotype.Service;


public interface VendorService {

	public List<Vendor> getAllVendors();
	
	public Vendor getVendor(long id);
	
	public Vendor addVendor(Vendor vendor);
	
	public Vendor updateVendor(Vendor vendor);
	
	public void deleteVendor(long id);
	
	public void deleteAllVendors();
}
