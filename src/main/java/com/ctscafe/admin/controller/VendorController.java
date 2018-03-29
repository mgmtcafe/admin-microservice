package com.ctscafe.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ctscafe.admin.error.ValidationErrors;
import com.ctscafe.admin.model.Vendor;
import com.ctscafe.admin.repository.VendorRepository;
import com.ctscafe.admin.utilities.JsonResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RestController
public class VendorController {
	private final VendorRepository vendorRepository;
	private RestTemplate restTemplate;

	@Autowired
	public VendorController(VendorRepository vendorRepository, RestTemplate restTemplate) {
		this.vendorRepository = vendorRepository;
		this.restTemplate = restTemplate;
	}

	@GetMapping(path = "/vendor/all")
	public List<Vendor> getLocation() {
		return vendorRepository.findAll();
	}

	@GetMapping(path = "/vendor/getById/{id}")
	public Object getVendorById(@PathVariable("id") String id) {
		return vendorRepository.findOne(Integer.parseInt(id));
	}
	
	
	@GetMapping(path= "/vendor/location")
	public Object checkLocation() {
		String url = "http://locationmicro.cfapps.io/location/getLocationById/1";
		String message = restTemplate.getForObject(url, String.class);
		return message;
	}
	
	
	@PostMapping(path = "/vendor/create")
	public Object createVendor(@RequestBody Map<String, String> map) {

		Vendor vendor = null;
		JsonResponse obj = new JsonResponse();
		try {
			vendor = new Vendor(0, map.get("name"), map.get("email"), map.get("contact"), map.get("address"),
					Integer.parseInt(map.get("location")));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			obj.setStatus("error");
			obj.setMessage(e.getMessage());
			return obj;
		}
		// Validate Fields
		JsonResponse jsonResponse = new ValidationErrors(vendor).getErrors();
		if (jsonResponse.getStatus().equals("error")) {
			return jsonResponse;
		} else {

			String url = "http://locationmicro.cfapps.io/location/getLocationById/" + map.get("location");
			String message = restTemplate.getForObject(url, String.class);
			Map<String, Object> retMap = new Gson().fromJson(message, new TypeToken<HashMap<String, Object>>() {
			}.getType());

			// check if location is valid
			String returned = (String) retMap.get("status");
			if (returned.equalsIgnoreCase("error")) {
				obj.setMessage("location is not valid");
				obj.setStatus("error");
				return obj;
			}

			// check if email already exists
			Integer vid = this.vendorRepository.findVendorByEmail(map.get("email"));
			if (vid != null) {

				obj.setStatus("error");
				obj.setMessage("email address already taken");
				return obj;
			}

			// check if contact already exists
			vid = this.vendorRepository.findVendorByContact(map.get("contact"));
			if (vid != null) {

				obj.setStatus("error");
				obj.setMessage("contact number already taken");
				return obj;
			}
			Vendor created = null;
			try {
				created = this.vendorRepository.save(vendor);
			} catch (HibernateException e) {
				obj.setStatus("error");
				obj.setMessage("something went wrong");
				return obj;
			}
			obj.setStatus("success");
			obj.setMessage(created);
			return obj;
		}
	}
}
