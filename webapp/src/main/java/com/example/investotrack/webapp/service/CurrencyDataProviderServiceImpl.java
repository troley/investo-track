package com.example.investotrack.webapp.service;

import com.example.investotrack.dataprovidercore.CurrencyDataProvider;
import com.example.investotrack.webapp.datastore.DataCache;
import com.example.investotrack.webapp.viewmodel.CurrencyViewModel;
import com.example.investotrack.webapp.viewmodel.convertor.CurrencyModelConvertor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Locale;

@Service
class CurrencyDataProviderServiceImpl implements CurrencyDataProviderService {

    private static final String CURRENCIES_CACHE_NAME = "currencies";
    private static final String CURRENCIES_CACHE_KEY = "allCurrencies";

    private final CurrencyDataProvider currencyDataProvider;
    private final CurrencyModelConvertor convertor;
    private final DataCache dataCache;

    public CurrencyDataProviderServiceImpl(CurrencyDataProvider currencyDataProvider,
                                           CurrencyModelConvertor convertor,
                                           DataCache dataCache) {
        this.currencyDataProvider = currencyDataProvider;
        this.convertor = convertor;
        this.dataCache = dataCache;
    }

    @Override
    public Collection<CurrencyViewModel> listAllCurrencies() {
        return dataCache.getOrSupply(CURRENCIES_CACHE_NAME,
                                     CURRENCIES_CACHE_KEY,
                                     () -> currencyDataProvider.listAllCurrencies()
                                                               .stream()
                                                               .map(convertor::toViewModel)
                                                               .toList());
    }

    @Override
    public Collection<CurrencyViewModel> findCaseInsensitiveCurrenciesBy(String query) {
        String ciq = query.toLowerCase(Locale.ROOT);
        return listAllCurrencies().stream()
                                  .filter(c -> lowerCased(c.name()).contains(ciq) ||
                                               lowerCased(c.symbol()).contains(ciq))
                                  .toList();
    }

    private String lowerCased(String str) {
        return str.toLowerCase(Locale.ROOT);
    }
}
