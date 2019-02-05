package com.hackerrank.sample.sku;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Sku {

	@Id
	@GeneratedValue
	private long id;
	
	private String productName;
	private String productLabel;
	private int inventoryOnHand;
	private int minQtyReq;
	private double price;
	
	
	
	public Sku(String productName, String productLabel, int inventoryOnHand, int minQtyReq, double price) {
		super();
		this.productName = productName;
		this.productLabel = productLabel;
		this.inventoryOnHand = inventoryOnHand;
		this.minQtyReq = minQtyReq;
		this.price = price;
	}
	
	
	
	
	public Sku() {}
	
	public long getSkuId() {
		return id;
	}
	public void setSkuId(long skuId) {
		this.id = skuId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductLabel() {
		return productLabel;
	}
	public void setProductLabel(String productLabel) {
		this.productLabel = productLabel;
	}
	public int getInventoryOnHand() {
		return inventoryOnHand;
	}
	public void setInventoryOnHand(int inventoryOnHand) {
		this.inventoryOnHand = inventoryOnHand;
	}
	public int getMinQtyReq() {
		return minQtyReq;
	}
	public void setMinQtyReq(int minQtyReq) {
		this.minQtyReq = minQtyReq;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
}
