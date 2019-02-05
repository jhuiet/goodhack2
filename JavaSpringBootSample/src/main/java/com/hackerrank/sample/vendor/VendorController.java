package com.hackerrank.sample.vendor;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VendorController {

	@Autowired
	private VendorService vendorService;

//Create--------------------------------

	@RequestMapping(method = RequestMethod.POST, value = "/vendor", consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Vendor addVendor(@RequestBody @Valid Vendor vendor) {
		Vendor savedVendor = vendorService.addVendor(vendor);
		return savedVendor;
	}

//Read--------------------------------------

	@RequestMapping(method = RequestMethod.GET, value = "/vendor")
	@ResponseStatus(HttpStatus.OK)
	public List<Vendor> getVendors() {
		return vendorService.getAllVendors();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/vendor/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Vendor getVendorById(@PathVariable long id) {
//		return new Vendor("Joes crab shack", 166647725L, "crabbs@gmail.com", "crustyc", "north beach");
		return vendorService.getVendor(id);
	}

//update--------------------------------------

	@RequestMapping(method = RequestMethod.PUT, value = "/vendor/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Vendor updateVendor(@PathVariable long id, @RequestBody @Valid Vendor vendor) {
		vendor.setVendorId(id);
		return vendorService.updateVendor(vendor);
	}

//Delete------------------------------------------

	@RequestMapping(method = RequestMethod.DELETE, value = "/vendor/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteVendor(@PathVariable long id) {
		vendorService.deleteVendor(id);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/vendor")
	@ResponseStatus(HttpStatus.OK)
	public void deleteAllVendors() {
		vendorService.deleteAllVendors();
	}

}
