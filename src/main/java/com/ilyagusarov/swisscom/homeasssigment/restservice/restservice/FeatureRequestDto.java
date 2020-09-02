package com.ilyagusarov.swisscom.homeasssigment.restservice.restservice;

import java.util.List;

public class FeatureRequestDto {
    private FeatureRequest featureRequest;

    public FeatureRequest getFeatureRequest() {
        return featureRequest;
    }

    public void setFeatureRequest(FeatureRequest featureRequest) {
        this.featureRequest = featureRequest;
    }

    public static class FeatureRequest {
        private long customerId;
        private List<Feature> features;

        public long getCustomerId() {
            return customerId;
        }

        public void setCustomerId(long customerId) {
            this.customerId = customerId;
        }

        public List<Feature> getFeatures() {
            return features;
        }

        public void setFeatures(List<Feature> features) {
            this.features = features;
        }

        public static class Feature {
           private String name;

           public String getName() {
               return name;
           }

           public void setName(String name) {
               this.name = name;
           }
       }
   }
}
