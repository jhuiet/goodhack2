package com.hackerrank.sample.sku;

import org.springframework.stereotype.Service;

import java.util.List;


public interface SkuService {

	public List<Sku> getAllSku();
	
	public Sku getSku(long id);
	
	public Sku addSku(Sku sku);
	
	public Sku updateSku(Sku sku);
	
	public void deleteSku(long id);
	
	public void deleteAllSku();
	
}
