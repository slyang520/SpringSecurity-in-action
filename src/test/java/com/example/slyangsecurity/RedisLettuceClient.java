package com.example.slyangsecurity;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisLettuceClient {

    // RedisAutoConfiguration

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    @Test
    public void stringRedisTemplate() {
          log.info("fff");
    }


}
