package com.ilyagusarov.swisscom.homeasssigment.restservice.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class WebAppFeatureToggleResource {

    @Autowired
    private FeatureToggleRepository featureToggleRepository;

    @GetMapping("/feature-toggles")
    public List<FeatureToggle> retrieveAllFeatureToggles() {
        return featureToggleRepository.findAll();
    }

    @GetMapping("/feature-toggles/{id}")
    public FeatureToggle retrieveFeatureToggle(@PathVariable Long id) {
        Optional<FeatureToggle> featureToggleOptional = featureToggleRepository.findById(id);
        if (!featureToggleOptional.isPresent()) {
            throw new ResourceNotFoundException("Not found by id: " + id);
        }
        return featureToggleOptional.get();
    }

    @PostMapping("/feature-toggles")
    public ResponseEntity<Object> createFeatureToggle(@RequestBody FeatureToggle featureToggle) {
        FeatureToggle savedFeatureToggle = featureToggleRepository.save(featureToggle);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedFeatureToggle.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/feature-toggles/{id}")
    public ResponseEntity<Object> updateFeatureToggle(@RequestBody FeatureToggle featureToggle, @PathVariable Long id) {
        Optional<FeatureToggle> featureToggleOptional = featureToggleRepository.findById(id);
        if (!featureToggleOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        featureToggle.setId(id);
        featureToggleRepository.save(featureToggle);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/feature-toggles/{id}")
    public void deleteFeatureToggle(@PathVariable long id) {
        featureToggleRepository.deleteById(id);
    }
}
