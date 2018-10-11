package com.example.async_cache.pojo.different_entities;

import com.example.async_cache.pojo.AsyncSimulator;
import com.example.async_cache.pojo.Loader;
import com.example.async_cache.pojo.different_entities.data.Book;
import com.example.async_cache.pojo.different_entities.data.BookRepository;
import com.example.async_cache.pojo.different_entities.data.Person;
import com.example.async_cache.pojo.different_entities.data.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("redis-different-entites")
@Service
@Slf4j
public class LoaderImpl implements Loader {

    @Autowired
    AsyncSimulator simulator;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    PersonRepository personRepository;

    @Override
    public void startLoad(String token) {
        log.info("startLoad");
        simulator.simulateLoadBook(token);
        simulator.simulateLoadPerson(token);
    }

    @Override
    public boolean resultIsLoaded(String token) {
        Book book = bookRepository.findOne(token);
        Person person = personRepository.findOne(token);
        return book != null && person != null && book.isLoaded() && person.isLoaded();
    }
}