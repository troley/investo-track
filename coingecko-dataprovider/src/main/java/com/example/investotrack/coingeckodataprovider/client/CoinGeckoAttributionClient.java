package com.example.investotrack.coingeckodataprovider.client;

import com.example.investotrack.coingeckodataprovider.CoinGeckoProperties;
import com.example.investotrack.dataprovidercore.AttributionDataProvider;
import com.example.investotrack.dataprovidercore.model.Attribution;

public class CoinGeckoAttributionClient implements AttributionDataProvider {

    private final CoinGeckoProperties coinGeckoProperties;

    public CoinGeckoAttributionClient(CoinGeckoProperties coinGeckoProperties) {
        this.coinGeckoProperties = coinGeckoProperties;
    }

    @Override
    public Attribution getAttribution() {
        return new Attribution(this.coinGeckoProperties.getBrand(),
                               this.coinGeckoProperties.getBaseUrl());
    }
}
