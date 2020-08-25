package com.ilyagusarov.swisscom.homeasssigment.restservice.restservice;

public class ServiceApiFeatureDto {
    private String name;
    private Boolean active;
    private Boolean inverted;
    private Boolean expired;

    public ServiceApiFeatureDto(String name, Boolean active, Boolean inverted, Boolean expired) {
        this.name = name;
        this.active = active;
        this.inverted = inverted;
        this.expired = expired;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getInverted() {
        return inverted;
    }

    public void setInverted(Boolean inverted) {
        this.inverted = inverted;
    }

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }
}
