package com.ilyagusarov.swisscom.homeasssigment.restservice.restservice;

import java.util.HashMap;
import java.util.List;

public class FeatureRequestDto {
    private Long customerId;
    private List<HashMap<String, String>> features;

    public FeatureRequestDto(Long customerId, List<HashMap<String, String>> features) {
        this.customerId = customerId;
        this.features = features;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<HashMap<String, String>> getFeatures() {
        return features;
    }

    public void setFeatures(List<HashMap<String, String>> features) {
        this.features = features;
    }
}
