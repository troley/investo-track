package com.example.investotrack.webapp.datastore;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DataCacheTest {

    @Mock
    private CacheManager cacheManager;

    @InjectMocks
    private DataCache dataCache;

    @Test
    void getOrSupply_cacheNotFound_throwsException() {
        // Arrange
        when(cacheManager.getCache(anyString())).thenReturn(null);

        // Act + Assert
        assertThrows(CacheNotFoundException.class, () -> dataCache.getOrSupply("testCache", "testKey", () -> null));
    }

    @Test
    void getOrSupply_cacheKeyResultNull_invokesSupplierCachesDataAndReturnsData() {
        // Arrange
        String cacheName = "testCache";
        String cacheKey = "testKey";
        String cacheData = "Test data";
        Cache cache = new ConcurrentMapCache(cacheName);

        when(cacheManager.getCache(anyString())).thenReturn(cache);

        // Act
        String result = dataCache.getOrSupply(cacheName, cacheKey, () -> cacheData);

        // Assert
        assertEquals(cacheData, cache.get(cacheKey, String.class));
        assertEquals(cacheData, result);
    }

    @Test
    void getOrSupply_cacheKeyResultExists_supplierNotInvokedJustReturnsData() {
        // Arrange
        String cacheName = "testCache";
        String cacheKey = "testKey";
        String cacheData = "Test data";
        Cache cache = new ConcurrentMapCache(cacheName);
        cache.put(cacheKey, cacheData);

        when(cacheManager.getCache(anyString())).thenReturn(cache);

        // Act
        String result = dataCache.getOrSupply(cacheName, cacheKey, () -> {
            throw new RuntimeException("This will not throw, because the supplier is never invoked.");
        });

        // Assert
        assertEquals(cacheData, result);
    }
}
