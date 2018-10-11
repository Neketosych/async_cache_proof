package com.example.async_cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.UUID;

@SpringBootApplication
@EnableCaching
@EnableAsync
@EnableRedisRepositories
@Slf4j
public class AsyncCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsyncCacheApplication.class, args);
	}
}