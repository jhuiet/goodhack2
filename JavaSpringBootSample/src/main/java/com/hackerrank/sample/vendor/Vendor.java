package com.hackerrank.sample.vendor;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Vendor {
	
	@Id
	private long id;
	
	private String vendorName;
	private Long vendorContactNo;
	private String vendorEmail;
	private String vendorUsername;
	private String vendorAddress;
	
	
		
	public Vendor() {
		super();
	}
	
	public Vendor(String vendorName, Long vendorContactNo, String vendorEmail, String vendorUsername,
			String vendorAddress) {
		super();
		this.vendorName = vendorName;
		this.vendorContactNo = vendorContactNo;
		this.vendorEmail = vendorEmail;
		this.vendorUsername = vendorUsername;
		this.vendorAddress = vendorAddress;
	}
	
	public Vendor(long vendorId, String vendorName, Long vendorContactNo, String vendorEmail, String vendorUsername,
			String vendorAddress) {
		super();
		this.id = vendorId;
		this.vendorName = vendorName;
		this.vendorContactNo = vendorContactNo;
		this.vendorEmail = vendorEmail;
		this.vendorUsername = vendorUsername;
		this.vendorAddress = vendorAddress;
	}
	
	public long getVendorId() {
		return id;
	}
	public void setVendorId(long vendorId) {
		this.id = vendorId;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public Long getVendorContactNo() {
		return vendorContactNo;
	}
	public void setVendorContactNo(Long vendorContactNo) {
		this.vendorContactNo = vendorContactNo;
	}
	public String getVendorEmail() {
		return vendorEmail;
	}
	public void setVendorEmail(String vendorEmail) {
		this.vendorEmail = vendorEmail;
	}
	public String getVendorUsername() {
		return vendorUsername;
	}
	public void setVendorUsername(String vendorUsername) {
		this.vendorUsername = vendorUsername;
	}
	public String getVendorAddress() {
		return vendorAddress;
	}
	public void setVendorAddress(String vendorAddress) {
		this.vendorAddress = vendorAddress;
	}
	
	
}
