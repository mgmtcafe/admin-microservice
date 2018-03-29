package com.ctscafe.admin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ctscafe.admin.validator.VendorContact;
import com.ctscafe.admin.validator.VendorEmail;
import com.ctscafe.admin.validator.VendorName;

@Entity
@Table(name = "vendor")
public class Vendor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "vendor_id")
	private int vendorId;

	@VendorName(message = "Invalid Name")
	@Column(name = "vendor_name")
	private String vendorName;

	@VendorEmail(message = "Invalid Email")
	@Column(name = "vendor_email")
	private String vendorEmail;

	@VendorContact(message = "Invalid Contact")
	@Column(name = "vendor_contact")
	private String vendorContact;

	@Column(name = "vendor_address")
	private String vendorAddress;

	@Column(name = "vendor_location_id")
	private int vendorLocationId;

	public Vendor() {
	}

	public Vendor(int vendorId, String vendorName, String vendorEmail, String vendorContact, String vendorAddress,
			int vendorLocationId) {
		this.vendorId = vendorId;
		this.vendorName = vendorName;
		this.vendorEmail = vendorEmail;
		this.vendorContact = vendorContact;
		this.vendorAddress = vendorAddress;
		this.vendorLocationId = vendorLocationId;
	}

	public int getVendorId() {
		return vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public String getVendorEmail() {
		return vendorEmail;
	}

	public String getVendorContact() {
		return vendorContact;
	}

	public String getVendorAddress() {
		return vendorAddress;
	}

	public int getVendorLocationId() {
		return vendorLocationId;
	}

}
