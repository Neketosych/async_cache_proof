package com.example.async_cache;

public interface PreLoadDataService {

    String loadData(String token);

    String getLoadedData(String token);
}
