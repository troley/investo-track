package com.example.investotrack.webapp.service;


import com.example.investotrack.webapp.viewmodel.CurrencyViewModel;

import java.util.Collection;

/**
 * CoinGecko service providing features provided by {@link com.example.investotrack.coingeckodataprovider.client.CoinGeckoClient}.
 */
public interface CurrencyDataProviderService {

    /**
     * Lists all cryptocurrencies.
     *
     * @return list of cryptocurrencies
     */
    Collection<CurrencyViewModel> listAllCurrencies();
}
