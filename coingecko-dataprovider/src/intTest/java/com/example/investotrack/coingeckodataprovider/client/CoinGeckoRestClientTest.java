package com.example.investotrack.coingeckodataprovider.client;

import com.example.investotrack.coingeckodataprovider.CoinGeckoProperties;
import com.example.investotrack.coingeckodataprovider.client.model.CryptoCurrency;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class CoinGeckoRestClientTest {

    private static MockWebServer mockBackEnd;

    private CoinGeckoRestClient coinGeckoClient;

    @BeforeAll
    static void setup() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
    }

    @BeforeEach
    void eachSetup() {
        CoinGeckoProperties coinGeckoProperties = new CoinGeckoProperties();
        coinGeckoProperties.setApiBaseUrl(String.format("http://localhost:%s/", mockBackEnd.getPort()));

        coinGeckoClient = new CoinGeckoRestClient(coinGeckoProperties);
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @Test
    void listAllCurrencies_returnsTwoCoins() throws Exception {
        // Arrange
        CryptoCurrency btc = new CryptoCurrency("bitcoin", "btc", "Bitcoin");
        CryptoCurrency eth = new CryptoCurrency("ethereum", "eth", "Ethereum");

        ObjectMapper objectMapper = new ObjectMapper();

        mockBackEnd.enqueue(new MockResponse().setBody(objectMapper.writeValueAsString(List.of(btc, eth)))
                                              .addHeader("Content-Type", "application/json"));

        // Act
        Collection<CryptoCurrency> currencies = coinGeckoClient.listAllCurrencies();

        // Assert
        assertThat(currencies).containsSequence(btc, eth);
    }

    @Test
    void listAllCurrencies_returnsEmptyList() throws Exception {
        // Arrange
        ObjectMapper objectMapper = new ObjectMapper();

        mockBackEnd.enqueue(new MockResponse().setBody(objectMapper.writeValueAsString(List.of()))
                                              .addHeader("Content-Type", "application/json"));

        // Act
        Collection<CryptoCurrency> result = coinGeckoClient.listAllCurrencies();

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void listAllCurrencies_over256KBdata_returnsDataSuccessfully() throws Exception {
        // Arrange
        int numberOfCurrencies = 10_000;
        List<CryptoCurrency> cryptoCurrencies = generateUniqueCryptoCurrencies(numberOfCurrencies);

        ObjectMapper objectMapper = new ObjectMapper();

        mockBackEnd.enqueue(new MockResponse().setBody(objectMapper.writeValueAsString(cryptoCurrencies))
                                              .addHeader("Content-Type", "application/json"));

        // Act
        Collection<CryptoCurrency> result = coinGeckoClient.listAllCurrencies();

        // Assert
        assertEquals(numberOfCurrencies, result.size());
    }

    private List<CryptoCurrency> generateUniqueCryptoCurrencies(int howMany) {
        List<CryptoCurrency> cryptoCurrencies = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            String coin = "coin" + i;
            cryptoCurrencies.add(new CryptoCurrency(coin, coin, coin));
        }
        return cryptoCurrencies;
    }
}
