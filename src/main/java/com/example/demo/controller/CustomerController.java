package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Customer;
import com.example.demo.repository.CustomerRepository;

@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	private CustomerRepository repository;

	public CustomerController() {
	}

	public CustomerController(CustomerRepository repository) {
		this.repository = repository;
	}

	// http://localhost:8080/api/customer?name=aom
	@GetMapping("/customer")
	public List<Customer> searchCustomer(@RequestParam("name") String name) {
		return repository.findByName(name);

	}

	@GetMapping("/customer/{id}")
	public Optional<Customer> searchCustomer(@PathVariable("id") Integer id) {
		return repository.findById(id);
	}

	@PostMapping("/customer")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
		try {
			Customer savedCustomer = repository.save(customer);
			return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
