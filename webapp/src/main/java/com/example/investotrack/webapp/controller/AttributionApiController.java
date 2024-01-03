package com.example.investotrack.webapp.controller;

import com.example.investotrack.webapp.service.AttributionDataProviderService;
import com.example.investotrack.webapp.validation.ValidApiVersion;
import com.example.investotrack.webapp.viewmodel.AttributionViewModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v{version}/attribution")
public class AttributionApiController {

    private final AttributionDataProviderService attributionDataProviderService;

    public AttributionApiController(AttributionDataProviderService attributionDataProviderService) {
        this.attributionDataProviderService = attributionDataProviderService;
    }

    @GetMapping
    public ResponseEntity<AttributionViewModel> getCurrencyDataProviderAttribution(@PathVariable
                                                                                   @ValidApiVersion String version) {
        return ResponseEntity.of(attributionDataProviderService.getAttribution());
    }
}
