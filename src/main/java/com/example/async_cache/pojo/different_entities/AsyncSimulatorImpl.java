package com.example.async_cache.pojo.different_entities;

import com.example.async_cache.pojo.AsyncSimulator;
import com.example.async_cache.pojo.different_entities.data.Book;
import com.example.async_cache.pojo.different_entities.data.BookRepository;
import com.example.async_cache.pojo.different_entities.data.Person;
import com.example.async_cache.pojo.different_entities.data.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Profile("redis-different-entites")
@Service
@Slf4j
public class AsyncSimulatorImpl implements AsyncSimulator {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    PersonRepository personRepository;

    private int tries = 20;

    @Async
    @Override
    public void simulateLoadBook(String token) {
        for (int i=0; i < tries; i++) {
            log.info("simulateLoadBook");
            bookRepository.save(
                    Book.builder()
                            .id(token)
                            .title("any")
                            .build()
            );
        }
        bookRepository.save(
                Book.builder()
                        .id(token)
                        .title("any")
                        .isLoaded(true)
                        .build()
        );
    }

    @Async
    @Override
    public void simulateLoadPerson(String token) {
        for (int i=0; i < tries; i++) {
            log.info("simulateLoadPerson");
            personRepository.save(
                    Person.builder()
                            .id(token)
                            .name("any")
                            .build()
            );
        }
        personRepository.save(
                Person.builder()
                        .id(token)
                        .name("any")
                        .isLoaded(true)
                        .build()
        );
    }
}