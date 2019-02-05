package com.hackerrank.sample.vendor;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackerrank.sample.exception.NoSuchResourceFoundException;

@Service("VendorService")
public class VendorServiceImpl implements VendorService{

	@Autowired
	private VendorRepository vendorRepository;
	
//Create----------------------------
	
	@Override
	public Vendor addVendor(Vendor vendor) {
		return vendorRepository.save(vendor);
	}
	
//Read-------------------------------
	
	@Override
	public Vendor getVendor(long id) {
		Vendor vendor = vendorRepository.findOne(id);
		if(vendor == null)
			throw new NoSuchResourceFoundException("Get failed: Vendor does not exist!");
		return vendor;
	}
	
	@Override
	public List<Vendor> getAllVendors() {
		List<Vendor> vendors = new ArrayList<>();
		vendorRepository.findAll()
			.forEach(v -> vendors.add(v));
		if(vendors.isEmpty())
			throw new NoSuchResourceFoundException("Get all failed: Vendor does not exist!");
		return vendors;
	}

//Update------------------------------
	
	@Override
	public Vendor updateVendor(Vendor vendor) {
		if(vendorRepository.exists(vendor.getVendorId())) 
			return vendorRepository.save(vendor);
		else
			throw new NoSuchResourceFoundException("Update failed: Vendor does not exist!");
	}

//Delete
	
	@Override
	public void deleteVendor(long id) {
		if(vendorRepository.exists(id)) 
			vendorRepository.deleteById(id);
		else
			throw new NoSuchResourceFoundException("Delete failed: Vendor does not exist!");
	}

	@Override
	public void deleteAllVendors() {
		vendorRepository.deleteAll();
		
	}

	

}
