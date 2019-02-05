package com.hackerrank.sample.sku;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("skuRepository")
public interface SkuRepository extends JpaRepository<Sku, Long>{

	@Transactional
	Long deleteById(Long id);
}
