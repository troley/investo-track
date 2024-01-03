package com.example.investotrack.webapp.service;

import com.example.investotrack.webapp.viewmodel.AttributionViewModel;

import java.util.Optional;

/**
 * Service providing attribution data of a data provider.
 */
public interface AttributionDataProviderService {

    /**
     * Retrieves the attribution data, if any.
     *
     * @return the attribution data like the brand and url to the company website, if any
     */
    Optional<AttributionViewModel> getAttribution();
}
