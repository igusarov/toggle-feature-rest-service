package com.ilyagusarov.swisscom.homeasssigment.restservice.restservice;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface FeatureToggleRepository extends JpaRepository<FeatureToggle, Long> {
    List<FeatureToggle> findByTechnicalNameIn(Set<String> technicalName);
}
