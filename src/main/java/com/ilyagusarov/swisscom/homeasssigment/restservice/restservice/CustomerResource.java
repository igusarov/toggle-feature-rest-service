package com.ilyagusarov.swisscom.homeasssigment.restservice.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerResource {

    @Autowired CustomerRepository customerRepository;

    @GetMapping("/customers")
    public List<Customer> retrieveAllCustomers() {
        return customerRepository.findAll();
    }
}
