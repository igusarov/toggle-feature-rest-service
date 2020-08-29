package com.ilyagusarov.swisscom.homeasssigment.restservice.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RequestMapping("/api/v1")
@RestController
public class ServiceApiController {

    @Autowired
    private FeatureToggleRepository featureToggleRepository;

    @PostMapping("/features")
    public List<ServiceApiFeatureDto> retrieveFeatures(@RequestBody HashMap<String, FeatureRequestDto> requestBody) {
        FeatureRequestDto featureRequestDao = requestBody.get("featureRequest");
        Long customerId = featureRequestDao.getCustomerId();
        Set<String> names = featureRequestDao.getFeatures().stream()
                .map((item) -> item.get("name"))
                .collect(Collectors.toSet());
        List<FeatureToggle> foundFeatureToggles = featureToggleRepository.findByTechnicalNameIn(names);
        List<ServiceApiFeatureDto> result = new LinkedList<>();
        foundFeatureToggles.forEach((item) -> result.add(convertToDto(item, customerId, LocalDateTime.now())));
        return result;
    }

    public static ServiceApiFeatureDto convertToDto(FeatureToggle featureToggle, Long userId, LocalDateTime currentTime) {
        boolean isCustomerInList = featureToggle.getCustomers().stream()
                .map(Customer::getId)
                .collect(Collectors.toSet())
                .contains(userId);

        LocalDateTime expiresOn = featureToggle.getExpiresOn();
        boolean isExpired =  expiresOn != null && expiresOn.isBefore(currentTime);

        return new ServiceApiFeatureDto(
                featureToggle.getTechnicalName(),
                // true when:
                // - customer is in the list AND is not inverted AND is not in archive AND is not expired
                // - customer is not in the list AND is expired AND is not in archive
                !featureToggle.getArchive() &&
                        ((isCustomerInList && !featureToggle.getInverted() && !isExpired) || (!isCustomerInList && isExpired)),
                featureToggle.getInverted(),
                isExpired
        );
    }
}
