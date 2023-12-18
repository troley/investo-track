package com.example.investotrack.coingeckodataprovider.client.model;

import com.example.investotrack.dataprovidercore.model.AbstractCurrency;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Represents a cryptocurrency data model as provided by the CoinGecko API.
 */
public class CryptoCurrency extends AbstractCurrency implements Serializable {

    @JsonCreator
    public CryptoCurrency(@JsonProperty("id") String id,
                          @JsonProperty("symbol") String symbol,
                          @JsonProperty("name") String name) {
        super(id, symbol, name);
    }
}
