package com.example.async_cache.pojo.super_entity;

import com.example.async_cache.pojo.AsyncSimulator;
import com.example.async_cache.pojo.Loader;
import com.example.async_cache.pojo.super_entity.data.SuperEntity;
import com.example.async_cache.pojo.super_entity.data.SuperEntityrepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("redis-super-entity")
@Service
@Slf4j
public class LoaderImpl implements Loader {

    @Autowired
    AsyncSimulator simulator;

    @Autowired
    SuperEntityrepository superEntityrepository;

    @Override
    public void startLoad(String token) {
        log.info("startLoad");
        superEntityrepository.save(SuperEntity.builder().id(token).build());
        simulator.simulateLoadBook(token);
        simulator.simulateLoadPerson(token);
    }

    @Override
    public boolean resultIsLoaded(String token) {
        SuperEntity entity = superEntityrepository.findOne(token);
        if (entity != null) {
            log.info("entity: {}", entity);
            return (entity.getBook() != null && entity.getPerson() != null
                    && entity.getBook().isLoaded() && entity.getPerson().isLoaded());
        }
        return false;
    }
}