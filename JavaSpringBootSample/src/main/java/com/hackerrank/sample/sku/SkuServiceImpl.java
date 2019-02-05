package com.hackerrank.sample.sku;

import org.springframework.stereotype.Service;

import com.tcs.hack.Exception.NoSuchResourceFoundException;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Service("SkuService")
public class SkuServiceImpl implements SkuService{

	@Autowired
	private SkuRepository skuRepository;
	
	public List<Sku> getAllSku(){
		List<Sku> inventory = new ArrayList<>();
		skuRepository.findAll()
			.forEach(s -> inventory.add(s));
		if(inventory.isEmpty())
			throw new NoSuchResourceFoundException("No items exist yet in the inventory");
		return inventory;
	}
	
	public Sku getSku(long id) {
		Sku sku = skuRepository.findOne(id);
		if(sku == null)
			throw new NoSuchResourceFoundException("No items exist yet in the inventory");
		return sku;
	}
	
	public Sku addSku(Sku sku) {
		return skuRepository.save(sku);
	}
	
	public Sku updateSku(Sku sku) {
		if(skuRepository.exists(sku.getSkuId())) {
			return skuRepository.save(sku);
		}
		else 
			throw new NoSuchResourceFoundException("Customer does not exist!");
	}
	
	public void deleteSku(long id) {
		if(skuRepository.exists(id)) {
			skuRepository.delete(id);
		}
		else 
			throw new NoSuchResourceFoundException("Customer does not exist!");
	}	
	
	public void deleteAllSku() {
		skuRepository.deleteAll();
	}
	
	
}
