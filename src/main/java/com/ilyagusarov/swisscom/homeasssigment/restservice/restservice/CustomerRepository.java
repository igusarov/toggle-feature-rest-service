package com.ilyagusarov.swisscom.homeasssigment.restservice.restservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
