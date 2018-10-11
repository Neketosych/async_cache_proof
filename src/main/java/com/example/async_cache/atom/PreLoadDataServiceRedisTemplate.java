package com.example.async_cache.atom;

import com.example.async_cache.PreLoadDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;

@Service
@Profile("redis-template")
@Slf4j
public class PreLoadDataServiceRedisTemplate implements PreLoadDataService {

    @Resource(name="redisTemplate")
    private ValueOperations<String, Object> valOps;

    @Override
    @Async
    public String loadData(String token) {
        try {
            Thread.sleep(500 + new Random().nextInt(500));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("data is loaded");
        valOps.append(token, "data");
        return "data";
    }

    @Override
    public String getLoadedData(String token) {
        return (String) valOps.get(token);
    }
}