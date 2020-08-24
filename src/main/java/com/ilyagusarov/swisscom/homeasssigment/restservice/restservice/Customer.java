package com.ilyagusarov.swisscom.homeasssigment.restservice.restservice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Customer {
    @Id
    @GeneratedValue
    private Long id;
    private String displayName;

    public Customer() {
        super();
    }

    public Customer(
            Long id,
            String displayName
    ) {
        super();
        this.id = id;
        this.displayName = displayName;
    }

    public Long getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }
}
