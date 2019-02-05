package com.hackerrank.sample.customer;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Customer {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String customerName;
	private long contactNumber;
	private String address;
	private String gender;
	
	
	public Customer(long customerId, String customerName, long contactNumber, String address, String gender) {
		super();
		this.id = customerId;
		this.customerName = customerName;
		this.contactNumber = contactNumber;
		this.address = address;
		this.gender = gender;
	}
	
	
	public Customer(String customerName, long contactNumber, String address, String gender) {
		super();
		this.customerName = customerName;
		this.contactNumber = contactNumber;
		this.address = address;
		this.gender = gender;
	}
	
	public Customer() {}
	
	public long getCustomerId() {
		return id;
	}
	public void setCustomerId(long customerId) {
		this.id = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public long getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
}
