package com.example.investotrack.webapp.service;


import com.example.investotrack.webapp.viewmodel.CurrencyViewModel;
import org.springframework.lang.Nullable;

import java.util.Collection;

/**
 * Service providing features of a currency data provider.
 */
public interface CurrencyDataProviderService {

    /**
     * Lists all cryptocurrencies.
     *
     * @return list of cryptocurrencies
     */
    Collection<CurrencyViewModel> listAllCurrencies();

    /**
     * Case-insensitively finds specific currencies by the provided query. If no query has been provided, then tries to
     * find all currencies.
     *
     * @param query the case-insensitive string to look for in the currency data, might be null
     * @return      a list of currencies or an empty collection if none were found
     */
    Collection<CurrencyViewModel> findCaseInsensitiveCurrenciesBy(@Nullable String query);
}
