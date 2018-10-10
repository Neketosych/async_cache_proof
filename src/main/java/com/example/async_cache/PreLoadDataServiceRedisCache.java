package com.example.async_cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.*;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
@CacheConfig(cacheNames = "redis_cache")
@Profile("redis-cache")
public class PreLoadDataServiceRedisCache implements PreLoadDataService {

    @Override
    @Async
    @CachePut(key="#token")
    public String loadData(String token) {
        try {
            Thread.sleep(500 + new Random().nextInt(500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("data is loaded");
        return "data";
    }

    @Override
    @Caching(
            cacheable = @Cacheable(key="#token"),
            evict = @CacheEvict(key="#token")
    )
    public String getLoadedData(String token) {
        return null;
    }
}