package com.example.async_cache.pojo.super_entity;

import com.example.async_cache.pojo.AsyncSimulator;
import com.example.async_cache.pojo.super_entity.data.Book;
import com.example.async_cache.pojo.super_entity.data.Person;
import com.example.async_cache.pojo.super_entity.data.SuperEntity;
import com.example.async_cache.pojo.super_entity.data.SuperEntityrepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.core.PartialUpdate;
import org.springframework.data.redis.core.RedisKeyValueTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Profile("redis-super-entity")
@Service
@Slf4j
public class AsyncSimulatorImpl implements AsyncSimulator {

    @Autowired
    SuperEntityrepository repository;

    @Autowired
    private RedisKeyValueTemplate redisKVTemplate;


    private int tries = 20;

    @Async
    @Override
    public void simulateLoadBook(String token) {
        for (int i=0; i < tries; i++) {
            log.info("simulateLoadBook");
            PartialUpdate<SuperEntity> update = new PartialUpdate<>(token, SuperEntity.class)
                    .set("book", Book.builder().title("any").build());
            redisKVTemplate.update(update);
        }
        PartialUpdate<SuperEntity> update = new PartialUpdate<>(token, SuperEntity.class)
                .set("book", Book.builder().title("any").isLoaded(true).build());
        redisKVTemplate.update(update);
    }

    @Async
    @Override
    public void simulateLoadPerson(String token) {
        for (int i=0; i < tries; i++) {
            log.info("simulateLoadPerson");
            PartialUpdate<SuperEntity> update = new PartialUpdate<>(token, SuperEntity.class)
                    .set("person", Person.builder().name("any").build());
            redisKVTemplate.update(update);
        }
        PartialUpdate<SuperEntity> update = new PartialUpdate<>(token, SuperEntity.class)
                .set("person", Person.builder().name("any").isLoaded(true).build());
        redisKVTemplate.update(update);
    }
}