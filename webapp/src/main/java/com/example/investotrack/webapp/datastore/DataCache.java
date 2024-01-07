package com.example.investotrack.webapp.datastore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

/**
 * A cache component that utilizes a cache data store provided by the injected cache manager to store and retrieve data.
 */
@Component
public class DataCache {

    private static final Logger log = LoggerFactory.getLogger(DataCache.class);

    private final CacheManager cacheManager;

    public DataCache(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    /**
     * This method tries to obtain the requested data from the cache by the provided cache name and key. If it fails
     * to do so, then it triggers the provided supply function to obtain the data externally, stores it in the cache and
     * returns it. Respective calls to this method will obtain the data from the cache.
     *
     * @param  cacheName              the name of the cache of the data
     * @param  cacheKey               the key by which the data are stored
     * @param  supplyFunc             the function which is triggered when the cache is empty
     * @return                        the data, retrieved either from the cache or supplied by the supplier function
     * @param  <T>                    the type of the data to retrieve
     * @throws CacheNotFoundException when the cache with the provided cache name doesn't exist
     */
    @SuppressWarnings("unchecked")
    public <T> T getOrSupply(String cacheName, String cacheKey, Supplier<T> supplyFunc) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            Cache.ValueWrapper result = cache.get(cacheKey);
            if (result == null) {
                T data = supplyFunc.get();
                cache.put(cacheKey, data);
                return data;
            }
            return (T) result.get();
        }
        CacheNotFoundException exception = new CacheNotFoundException();
        log.error("Cache with the cache name '" + cacheName + "' was not found.", exception);
        throw exception;
    }
}
