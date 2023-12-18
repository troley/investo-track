package com.example.investotrack.webapp.service;

import com.example.investotrack.dataprovidercore.CurrencyDataProvider;
import com.example.investotrack.dataprovidercore.model.AbstractCurrency;
import com.example.investotrack.webapp.viewmodel.CurrencyViewModel;
import com.example.investotrack.webapp.viewmodel.convertor.CurrencyModelConvertor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrencyDataProviderServiceImplTest {

    @Mock
    private CurrencyDataProvider currencyDataProvider;

    @Mock
    private CurrencyModelConvertor convertor;

    @InjectMocks
    private CurrencyDataProviderServiceImpl currencyDataProviderService;

    @Test
    void listAllCurrencies_returnsCurrency() {
        // Arrange
        AbstractCurrency btc = new AbstractCurrency("btc", "btc", "Bitcoin") {};
        CurrencyViewModel viewModel = new CurrencyViewModel(btc.getId(), btc.getSymbol(), btc.getName());

        when(currencyDataProvider.listAllCurrencies()).thenAnswer((invocationOnMock -> List.of(btc)));
        when(convertor.toViewModel(any(AbstractCurrency.class))).thenReturn(viewModel);

        // Act
        Collection<CurrencyViewModel> result = currencyDataProviderService.listAllCurrencies();

        // Assert
        assertThat(result).contains(viewModel);
    }

    @Test
    void listAllCurrencies_returnsTwoCurrencies() {
        // Arrange
        AbstractCurrency btc = new AbstractCurrency("btc", "btc", "Bitcoin") {};
        AbstractCurrency eth = new AbstractCurrency("eth", "eth", "Ethereum") {};
        CurrencyViewModel btcViewModel = new CurrencyViewModel(btc.getId(), btc.getSymbol(), btc.getName());
        CurrencyViewModel ethViewModel = new CurrencyViewModel(eth.getId(), eth.getSymbol(), eth.getName());

        when(currencyDataProvider.listAllCurrencies()).thenAnswer((invocationOnMock -> List.of(btc, eth)));
        when(convertor.toViewModel(any(AbstractCurrency.class))).thenReturn(btcViewModel, ethViewModel);

        // Act
        Collection<CurrencyViewModel> result = currencyDataProviderService.listAllCurrencies();

        // Assert
        assertThat(result).containsSequence(btcViewModel, ethViewModel);
    }

    @Test
    void listAllCurrencies_noData_returnsEmptyList() {
        // Arrange
        when(currencyDataProvider.listAllCurrencies()).thenAnswer((invocationOnMock -> List.of()));

        // Act
        Collection<CurrencyViewModel> result = currencyDataProviderService.listAllCurrencies();

        // Assert
        assertTrue(result.isEmpty());
    }
}
