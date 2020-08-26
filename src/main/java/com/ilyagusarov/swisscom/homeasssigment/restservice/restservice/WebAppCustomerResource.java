package com.ilyagusarov.swisscom.homeasssigment.restservice.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class WebAppCustomerResource {

    @Autowired CustomerRepository customerRepository;

    @GetMapping("/customers")
    public List<Customer> getCustomers(@RequestParam Optional<String> startsWith) {
        if (startsWith.isPresent()) {
            return customerRepository.findByDisplayNameStartsWith(startsWith.get());
        }
        return customerRepository.findAll();
    }
}
