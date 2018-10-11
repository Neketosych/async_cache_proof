package com.example.async_cache.pojo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Profile({"redis-different-entites", "redis-super-entity"})
@Component
@Slf4j
public class PojoRunner implements CommandLineRunner {

    @Autowired
    Loader loader;

    @Override
    public void run(String... args) throws Exception {
        String token = UUID.randomUUID().toString();
        loader.startLoad(token);
        boolean isLoaded = false;
        while (!isLoaded) {
            log.info("wait for result");
            isLoaded = loader.resultIsLoaded(token);
        }
    }
}
