package com.example.async_cache.pojo.super_entity.data;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "super_entity", timeToLive = 60)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class SuperEntity {

    @Id String id;
    Book book;
    Person person;
}