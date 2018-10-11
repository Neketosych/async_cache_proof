package com.example.async_cache.pojo.super_entity.data;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Builder
@ToString
public class Book {

    String title;
    boolean isLoaded;
}