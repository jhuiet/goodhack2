package com.hackerrank.sample.sku;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SkuController {

	@Autowired
	private SkuService skuService;

	
//Create----------------------------------------
	
	@RequestMapping(method = RequestMethod.POST, value = "/item")
		@ResponseStatus(HttpStatus.CREATED)
		public Sku addItem(@RequestBody Sku item){
			return skuService.addSku(item);
		}
	
//read-------------------------------------------------
		
	@RequestMapping(method = RequestMethod.GET, value = "/item")
	@ResponseStatus(HttpStatus.OK)
	public List<Sku> getAllItems(){
		return skuService.getAllSku();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/item/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Sku getItemById(@PathVariable long id){
		return skuService.getSku(id);
	}

//Update--------------------------------------------
	
	@RequestMapping(method = RequestMethod.PUT, value = "/item/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Sku updateItem(@PathVariable long id, @RequestBody Sku item){
		item.setSkuId(id);
		return skuService.updateSku(item);
	}

//Delete--------------------------------------
		
	@RequestMapping(method = RequestMethod.DELETE, value = "/item/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteItem(@PathVariable long id) {
		skuService.deleteSku(id);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/item")
	@ResponseStatus(HttpStatus.OK)
	public void deleteAllItems() {
		skuService.deleteAllSku();
	}

}
