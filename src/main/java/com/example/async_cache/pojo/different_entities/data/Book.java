package com.example.async_cache.pojo.different_entities.data;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "books", timeToLive = 60)
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
public class Book {

    @Id
    String id;
    String title;
    boolean isLoaded;
}