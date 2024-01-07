package com.example.investotrack.webapp.service;

import com.example.investotrack.dataprovidercore.CurrencyDataProvider;
import com.example.investotrack.webapp.datastore.DataCache;
import com.example.investotrack.webapp.viewmodel.CurrencyViewModel;
import com.example.investotrack.webapp.viewmodel.convertor.CurrencyModelConvertor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrencyDataProviderServiceImplTest {

    // Unused but required by CurrencyDataProviderServiceImpl
    @Mock
    private CurrencyDataProvider currencyDataProvider;

    // Unused but required by CurrencyDataProviderServiceImpl
    @Mock
    private CurrencyModelConvertor convertor;

    @Mock
    private DataCache dataCache;

    @InjectMocks
    private CurrencyDataProviderServiceImpl currencyDataProviderService;

    @Test
    void listAllCurrencies_returnsCurrency() {
        // Arrange
        CurrencyViewModel viewModel = new CurrencyViewModel("btc", "btc", "Bitcoin");

        when(dataCache.getOrSupply(anyString(),
                                   anyString(),
                                   ArgumentMatchers.<Supplier<Collection<CurrencyViewModel>>>any())).thenReturn(List.of(
                viewModel));

        // Act
        Collection<CurrencyViewModel> result = currencyDataProviderService.listAllCurrencies();

        // Assert
        assertThat(result).contains(viewModel);
    }

    @Test
    void listAllCurrencies_returnsTwoCurrencies() {
        // Arrange
        CurrencyViewModel btcViewModel = new CurrencyViewModel("btc", "btc", "Bitcoin");
        CurrencyViewModel ethViewModel = new CurrencyViewModel("eth", "eth", "Ethereum");

        when(dataCache.getOrSupply(anyString(),
                                   anyString(),
                                   ArgumentMatchers.<Supplier<Collection<CurrencyViewModel>>>any())).thenReturn(List.of(
                btcViewModel,
                ethViewModel));

        // Act
        Collection<CurrencyViewModel> result = currencyDataProviderService.listAllCurrencies();

        // Assert
        assertThat(result).containsSequence(btcViewModel, ethViewModel);
    }

    @Test
    void listAllCurrencies_noData_returnsEmptyList() {
        // Arrange
        when(dataCache.getOrSupply(anyString(),
                                   anyString(),
                                   ArgumentMatchers.<Supplier<Collection<CurrencyViewModel>>>any())).thenReturn(List.of());

        // Act
        Collection<CurrencyViewModel> result = currencyDataProviderService.listAllCurrencies();

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void findCaseInsensitiveCurrenciesBy_randomCaseNoMatch_returnsEmptyList() {
        // Arrange
        var btcViewModel = new CurrencyViewModel("btc", "btc", "Bitcoin");
        var btcLiteViewModel = new CurrencyViewModel("btcLite", "btclite", "Bitcoin Lite");
        var ethViewModel = new CurrencyViewModel("eth", "eth", "Ethereum");

        when(dataCache.getOrSupply(anyString(),
                                   anyString(),
                                   ArgumentMatchers.<Supplier<Collection<CurrencyViewModel>>>any())).thenReturn(List.of(
                btcViewModel,
                btcLiteViewModel,
                ethViewModel));

        // Act
        Collection<CurrencyViewModel> result = currencyDataProviderService.findCaseInsensitiveCurrenciesBy("dOgE");

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void findCaseInsensitiveCurrenciesBy_randomCaseMatch_returnsMatchingCoins() {
        // Arrange
        var btcViewModel = new CurrencyViewModel("btc", "btc", "Bitcoin");
        var btcLiteViewModel = new CurrencyViewModel("btcLite", "btclite", "Bitcoin Lite");
        var ethViewModel = new CurrencyViewModel("eth", "eth", "Ethereum");

        when(dataCache.getOrSupply(anyString(),
                                   anyString(),
                                   ArgumentMatchers.<Supplier<Collection<CurrencyViewModel>>>any())).thenReturn(List.of(
                btcViewModel,
                btcLiteViewModel,
                ethViewModel));

        // Act
        Collection<CurrencyViewModel> result = currencyDataProviderService.findCaseInsensitiveCurrenciesBy("bTc");

        // Assert
        assertThat(result).containsExactly(btcViewModel, btcLiteViewModel);
    }
}
