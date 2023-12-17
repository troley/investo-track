package com.example.investotrack.coingeckodataprovider.client;


import com.example.investotrack.coingeckodataprovider.client.model.CryptoCurrency;

import java.util.Collection;

/**
 * A client capable of obtaining data provided by CoinGecko.
 */
public interface CoinGeckoClient {

    /**
     * Lists all available basic currency data, like id and name of each currency.
     *
     * @return collection of available currencies
     */
    Collection<CryptoCurrency> listAllCurrencies();
}
