package com.hackerrank.sample.vendor;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("vendorRepository")
public interface VendorRepository extends JpaRepository<Vendor, Long>{

	@Transactional
	Long deleteById(Long id);
}
