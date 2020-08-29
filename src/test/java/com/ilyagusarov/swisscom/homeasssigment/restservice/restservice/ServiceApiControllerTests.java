package com.ilyagusarov.swisscom.homeasssigment.restservice.restservice;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.time.LocalDateTime;
import java.util.*;

public class ServiceApiControllerTests {
    private FeatureToggle createFeatureToggle() {
        return new FeatureToggle(
                (long) 1,
                null,
                "feature-a",
                null,
                null,
                false,
                false,
                new ArrayList<>()
        );
    }

    private List<Customer> createCustomers() {
        Customer ilya = new Customer((long)1, "ilya");
        return Arrays.asList(ilya);
    }

    @Test
    public void convertToDto_ShouldReturnActiveAsTrue_WhenCustomerInListAndNotInvertedAndNotExpiredAndNotInArchive() {
        FeatureToggle featureToggle = this.createFeatureToggle();
        List<Customer> customers = this.createCustomers();
        featureToggle.setCustomers(customers);

        ServiceApiFeatureDto result1 = ServiceApiController.convertToDto(
                featureToggle,
                customers.get(0).getId(),
                LocalDateTime.of(2020, 1, 1, 0, 0)
        );

        // customer is not in list
        ServiceApiFeatureDto result2 = ServiceApiController.convertToDto(
                featureToggle,
                (long)12312,
                LocalDateTime.of(2020, 1, 1, 0, 0)
        );

        // inverted
        featureToggle.setInverted(true);
        ServiceApiFeatureDto result3 = ServiceApiController.convertToDto(
                featureToggle,
                customers.get(0).getId(),
                LocalDateTime.of(2020, 1, 1, 0, 0)
        );
        featureToggle.setInverted(false);

        // expired
        featureToggle.setExpiresOn(LocalDateTime.of(2019, 1, 1, 0, 0));
        ServiceApiFeatureDto result4 = ServiceApiController.convertToDto(
                featureToggle,
                customers.get(0).getId(),
                LocalDateTime.of(2020, 1, 1, 0, 0)
        );
        featureToggle.setExpiresOn(null);

        // archive
        featureToggle.setArchive(true);
        ServiceApiFeatureDto result5 = ServiceApiController.convertToDto(
                featureToggle,
                customers.get(0).getId(),
                LocalDateTime.of(2020, 1, 1, 0, 0)
        );
        featureToggle.setArchive(false);

        assertTrue(result1.getActive());
        assertFalse(result2.getActive());
        assertFalse(result3.getActive());
        assertFalse(result4.getActive());
        assertFalse(result5.getActive());
    }

    @Test
    public void convertToDto_ShouldReturnActiveAsTrue_WhenCustomerNotInListAndExpiredAndNotArchive() {
        FeatureToggle featureToggle = this.createFeatureToggle();
        List<Customer> customers = this.createCustomers();

        featureToggle.setExpiresOn(LocalDateTime.of(2019, 1,1,0,0));

        ServiceApiFeatureDto result1 = ServiceApiController.convertToDto(
                featureToggle,
                (long)1,
                LocalDateTime.of(2020, 1, 1, 0, 0)
        );

        // customer in list
        featureToggle.setCustomers(customers);
        ServiceApiFeatureDto result2 = ServiceApiController.convertToDto(
                featureToggle,
                customers.get(0).getId(),
                LocalDateTime.of(2020, 1, 1, 0, 0)
        );
        featureToggle.setCustomers(new ArrayList<>());

        // not expired
        featureToggle.setExpiresOn(LocalDateTime.of(2021,1,1,0,0));
        ServiceApiFeatureDto result3 = ServiceApiController.convertToDto(
                featureToggle,
                (long)1,
                LocalDateTime.of(2020, 1, 1, 0, 0)
        );
        featureToggle.setExpiresOn(LocalDateTime.of(2019, 1,1,0,0));

        // archive
        featureToggle.setArchive(true);
        ServiceApiFeatureDto result4 = ServiceApiController.convertToDto(
                featureToggle,
                (long)1,
                LocalDateTime.of(2020, 1, 1, 0, 0)
        );
        featureToggle.setArchive(false);


        assertTrue(result1.getActive());
        assertFalse(result2.getActive());
        assertFalse(result3.getActive());
        assertFalse(result4.getActive());
    }
}
