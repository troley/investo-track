package com.example.investotrack.webapp.viewmodel.convertor;

import com.example.investotrack.dataprovidercore.model.AbstractCurrency;
import com.example.investotrack.webapp.viewmodel.CurrencyViewModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CurrencyModelConvertorTest {

    private final CurrencyModelConvertor convertor = new CurrencyModelConvertor();

    @Test
    void toViewModel_convertsSuccessfully() {
        AbstractCurrency anonymousCurrency =
                new AbstractCurrency("testid", "testsymbol", "testcurrency") {
                };

        CurrencyViewModel viewModel = convertor.toViewModel(anonymousCurrency);

        assertEquals(anonymousCurrency.getId(), viewModel.id());
        assertEquals(anonymousCurrency.getSymbol(), viewModel.symbol());
        assertEquals(anonymousCurrency.getName(), viewModel.name());
    }
}
