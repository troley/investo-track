package com.example.investotrack.coingeckodataprovider.client;

import com.example.investotrack.coingeckodataprovider.CoinGeckoProperties;
import com.example.investotrack.coingeckodataprovider.client.model.CryptoCurrency;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Collection;

/**
 * A REST API client implementation of the CoinGecko client
 */
public class CoinGeckoRestClient implements CoinGeckoClient {

    // Represents 1MB in bytes.
    // The API-request for coins results in ~11k coin data which exceeds the WebFlux 256K default codec size.
    private static final int MAX_IN_MEMORY_SIZE = 1_024_000;

    private final WebClient coinGeckoWebClient;

    public CoinGeckoRestClient(CoinGeckoProperties coinGeckoProperties) {
        coinGeckoWebClient = WebClient.builder()
                .baseUrl(coinGeckoProperties.getApiBaseUrl())
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(MAX_IN_MEMORY_SIZE))
                .build();
    }

    @Override
    public Collection<CryptoCurrency> listAllCurrencies() {
        return coinGeckoWebClient.get()
                                 .uri("api/v3/coins/list")
                                 .retrieve()
                                 .bodyToMono(new ParameterizedTypeReference<Collection<CryptoCurrency>>() {})
                                 .block();
    }
}
