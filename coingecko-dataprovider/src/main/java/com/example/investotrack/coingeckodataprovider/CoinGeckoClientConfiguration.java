package com.example.investotrack.coingeckodataprovider;

import com.example.investotrack.coingeckodataprovider.client.CoinGeckoAttributionClient;
import com.example.investotrack.coingeckodataprovider.client.CoinGeckoClient;
import com.example.investotrack.coingeckodataprovider.client.CoinGeckoRestClient;
import com.example.investotrack.dataprovidercore.AttributionDataProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The autoconfiguration class for the CoinGecko client.
 */
@Configuration
@EnableConfigurationProperties(CoinGeckoProperties.class)
@ConditionalOnProperty(prefix = "data-provider.coin-gecko", name = "enabled", havingValue = "true")
public class CoinGeckoClientConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public CoinGeckoClient coinGeckoClient(CoinGeckoProperties coinGeckoProperties) {
        return new CoinGeckoRestClient(coinGeckoProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    public AttributionDataProvider coinGeckoAttributionClient(CoinGeckoProperties coinGeckoProperties) {
        return new CoinGeckoAttributionClient(coinGeckoProperties);
    }
}
