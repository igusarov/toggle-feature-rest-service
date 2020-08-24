package com.ilyagusarov.swisscom.homeasssigment.restservice.restservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureToggleRepository extends JpaRepository<FeatureToggle, Long> {
}
