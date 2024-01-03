package com.example.investotrack.dataprovidercore;

import com.example.investotrack.dataprovidercore.model.Attribution;

/**
 * Provides attribution data of a specific data provider that requires attribution.
 */
public interface AttributionDataProvider {

    /**
     * Retrieves brand attribution data.
     *
     * @return the attribution data like the brand and url to the company website
     */
    Attribution getAttribution();
}
