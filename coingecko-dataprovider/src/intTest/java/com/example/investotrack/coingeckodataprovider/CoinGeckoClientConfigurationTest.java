package com.example.investotrack.coingeckodataprovider;

import com.example.investotrack.coingeckodataprovider.client.CoinGeckoRestClient;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CoinGeckoClientConfigurationTest {

    @Nested
    @SpringBootTest(classes = CoinGeckoClientConfiguration.class, properties = "data-provider.coin-gecko.enabled=true")
    class HappyPath {
        @Autowired
        private ApplicationContext applicationContext;

        @Test
        void coinGeckoClient_enabled_returnsBean() {
            assertNotNull(applicationContext.getBean("coinGeckoClient", CoinGeckoRestClient.class));
        }
    }

    @Nested
    @SpringBootTest(classes = CoinGeckoClientConfiguration.class, properties = "data-provider.coin-gecko.enabled=false")
    class UnhappyPath {
        @Autowired
        private ApplicationContext applicationContext;

        @Test
        void coinGeckoClient_notEnabled_throwsException() {
            assertThrows(NoSuchBeanDefinitionException.class,
                         () -> applicationContext.getBean("coinGeckoClient", CoinGeckoRestClient.class)
                        );
        }
    }
}
