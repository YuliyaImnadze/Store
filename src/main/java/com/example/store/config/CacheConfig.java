package com.example.store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.beans.factory.annotation.Value;

import java.time.Duration;


@Configuration
public class CacheConfig {

    @Value("${cacheTimeLife}")
    private Long cacheTimeLife;


//    @Bean
//    public CacheManager cacheManager(RedisTemplate<String  , Object> redisTemplate) {
//        return RedisCacheManager.builder()
//                .cacheDefaults(cacheConfiguration())
//                .build();
//    }


    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(cacheTimeLife))
                .disableCachingNullValues();
    }
}
