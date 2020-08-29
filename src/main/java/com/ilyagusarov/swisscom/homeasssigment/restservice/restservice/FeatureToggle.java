package com.ilyagusarov.swisscom.homeasssigment.restservice.restservice;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class FeatureToggle {
    @Id
    @GeneratedValue
    private Long id;
    private String displayName;
    private String technicalName;
    private LocalDateTime expiresOn;
    private String description;
    private Boolean inverted;
    private Boolean archive;

    @ManyToMany
    @JoinTable(
            name = "feature_toggle_customer",
            joinColumns = @JoinColumn(name = "feature_toggle_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id"))
    private List<Customer> customers;

    public FeatureToggle() {
        super();
    }

    public FeatureToggle(
            Long id,
            String displayName,
            String technicalName,
            LocalDateTime expiresOn,
            String description,
            Boolean inverted,
            Boolean archive,
            List<Customer> customers
    ) {
        super();
        this.id = id;
        this.displayName = displayName;
        this.technicalName = technicalName;
        this.expiresOn = expiresOn;
        this.description = description;
        this.inverted = inverted;
        this.archive = archive;
        this.customers = customers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getTechnicalName() {
        return technicalName;
    }

    public void setTechnicalName(String technicalName) {
        this.technicalName = technicalName;
    }

    public LocalDateTime getExpiresOn() {
        return expiresOn;
    }

    public void setExpiresOn(LocalDateTime expiresOn) {
        this.expiresOn = expiresOn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getInverted() {
        return inverted;
    }

    public void setInverted(Boolean inverted) {
        this.inverted = inverted;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public Boolean getArchive() {
        return archive;
    }

    public void setArchive(Boolean archive) {
        this.archive = archive;
    }
}
