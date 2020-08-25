package com.ilyagusarov.swisscom.homeasssigment.restservice.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class ServiceApiResource {

    @Autowired
    private FeatureToggleRepository featureToggleRepository;

    @PostMapping("/api/v1/features")
    public List<ServiceApiFeatureDto> retrieveFeatures(@RequestBody HashMap<String, FeatureRequestDto> requestBody) {
        FeatureRequestDto featureRequestDao = requestBody.get("featureRequest");
        Long customerId = featureRequestDao.getCustomerId();
        Set<String> names = featureRequestDao.getFeatures().stream()
                .map((item) -> item.get("name"))
                .collect(Collectors.toSet());
        List<FeatureToggle> foundFeatureToggles = featureToggleRepository.findByTechnicalNameIn(names);
        List<ServiceApiFeatureDto> result = new LinkedList<>();
        foundFeatureToggles.forEach((item) -> {
            result.add(convertToDto(item, customerId, LocalDateTime.now()));
        });
        return result;
    }

    private ServiceApiFeatureDto convertToDto(FeatureToggle featureToggle, Long userId, LocalDateTime currentTime) {
        Boolean isCustomerInList = featureToggle.getCustomers().stream()
                .map((item) -> item.getId())
                .collect(Collectors.toSet())
                .contains(userId);

        LocalDateTime expiresOn = featureToggle.getExpiresOn();
        Boolean isExpired =  expiresOn != null && expiresOn.isBefore(currentTime);

        ServiceApiFeatureDto featureDto = new ServiceApiFeatureDto(
                featureToggle.getTechnicalName(),
                // true when:
                // - customer is in the list of the feature toggle and feature toggle is not inverted and is not in archive
                // - customer is NOT in the list of the feature, but feature toggle expired and feature toggle is not in archive
                !featureToggle.getArchive() &&
                        ((isCustomerInList && !featureToggle.getInverted()) || (!isCustomerInList && isExpired)),
                featureToggle.getInverted(),
                isExpired
        );

        return featureDto;
    }
}
