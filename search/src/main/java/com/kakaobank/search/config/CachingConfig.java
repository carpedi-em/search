package com.kakaobank.search.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableCaching
@EnableScheduling
public class CachingConfig {

    public static final String POPULAR = "POPULAR";

    @Bean
    public CacheManager cacheManager() {
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager(POPULAR);
        return cacheManager;
    }

    @CacheEvict(allEntries = true, value = {POPULAR})
    @Scheduled(fixedRate = 1000)
    public void removeCacheEvict() {
    }

}
