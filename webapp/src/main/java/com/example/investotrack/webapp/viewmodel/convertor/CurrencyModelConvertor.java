package com.example.investotrack.webapp.viewmodel.convertor;

import com.example.investotrack.dataprovidercore.model.AbstractCurrency;
import com.example.investotrack.webapp.viewmodel.CurrencyViewModel;
import org.springframework.stereotype.Component;

/**
 * A convertor which converts from a currency domain model to a view model. This convertor does NOT do conversions
 * between currencies.
 */
@Component
public class CurrencyModelConvertor {

    public CurrencyViewModel toViewModel(AbstractCurrency currency) {
        return new CurrencyViewModel(currency.getId(), currency.getSymbol(), currency.getName());
    }
}
