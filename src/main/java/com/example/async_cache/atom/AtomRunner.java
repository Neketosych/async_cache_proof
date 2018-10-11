package com.example.async_cache.atom;

import com.example.async_cache.PreLoadDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@Profile({"redis-cache","redis-template"})
public class AtomRunner implements CommandLineRunner {

    @Autowired
    PreLoadDataService service;

    public void run(String... args) throws Exception {
        String token = UUID.randomUUID().toString();
        service.loadData(token);
        String result = null;
        Long startTime = System.currentTimeMillis();
        while (result == null) {
            Thread.sleep(100);
            result = service.getLoadedData(token);
            log.info("Time spent: {}, result: {}", (System.currentTimeMillis() - startTime), result);
        }
        log.info("exit");
    }
}