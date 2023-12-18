package com.example.investotrack.dataprovidercore;

import com.example.investotrack.dataprovidercore.model.AbstractCurrency;

import java.util.Collection;

/**
 * A generic data provider encapsulating functionality required within the investo-track application.
 */
public interface CurrencyDataProvider {

    /**
     * Lists all available basic currency data.
     *
     * @return collection of available currencies
     */
    Collection<? extends AbstractCurrency> listAllCurrencies();
}
