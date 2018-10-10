package com.example.async_cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.UUID;

@SpringBootApplication
@EnableCaching
@EnableAsync
@Slf4j
public class AsyncCacheApplication implements CommandLineRunner {

    @Autowired
    PreLoadDataService service;

	public static void main(String[] args) {
		SpringApplication.run(AsyncCacheApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	    String token = UUID.randomUUID().toString();
	    service.loadData(token);
	    String result = null;
	    Long startTime = System.currentTimeMillis();
	    while(result==null) {
	        Thread.sleep(100);
	        result = service.getLoadedData(token);
	        log.info("Time spent: {}, result: {}", (System.currentTimeMillis() - startTime), result);
        }
	}
}