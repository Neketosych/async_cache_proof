package com.example.async_cache.pojo;

public interface Loader {

    void startLoad(String token);

    boolean resultIsLoaded(String token);
}