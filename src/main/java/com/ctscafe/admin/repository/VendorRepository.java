package com.ctscafe.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ctscafe.admin.model.Vendor;

public interface VendorRepository extends JpaRepository<Vendor, Integer> {
	// Duplicate email
	@Query("SELECT v.vendorId FROM Vendor v where v.vendorEmail = :email")
	Integer findVendorByEmail(@Param("email") String email);

	// Duplicate contact
	@Query("SELECT v.vendorId FROM Vendor v where v.vendorContact = :contact")
	Integer findVendorByContact(@Param("contact") String contact);
}
