package com.example.slyangsecurity;

import com.alibaba.fastjson.JSON;
import com.example.slyangsecurity.config.redis.RedisUtil;
import com.example.slyangsecurity.modules.block.entity.BcChaincode;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisLettuceClient {

// https://www.jianshu.com/p/7bf5dc61ca06

// Redis 可以存储键与5种不同数据结构类型之间的映射，
// 这5种数据结构类型分别为
//  String（字符串）、
//  List（列表）、
//  Set（集合）、
//  Hash（散列) 、
//  Zset（有序集合）。


    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate<String, Object> objectRedisTemplate;


    @Autowired
    ValueOperations<String, Object> valueOperations; //String（字符串）
    @Autowired
    ListOperations<String, Object> listOperations; // List（列表）
    @Autowired
    SetOperations<String, Object> setOperations;   // Set（集合)
    @Autowired
    HashOperations<String, String, Object> hashOperations; // Hash（散列)
    @Autowired
    ZSetOperations<String, Object> zSetOperations;  // Zset（有序集合）

    @Autowired
    RedisUtil redisUtil;

//    set key
//    get key
//    del key
//    incr key   //自增
//    expire key time //设置过期时间
//    ttl key  //查看过期时间  -2不存在  -1永不会过期  >0剩余过期时间s

    @Test
    public void stringDetails() {
        // set 字符串
        valueOperations.set("slyang_test_set","slyang_test_set_value",60 , TimeUnit.SECONDS);
        String hello = redisUtil.getCacheObject("slyang_test_set");

        valueOperations.set("slyang_test_set_int",123,60 , TimeUnit.SECONDS);
        Integer hello_int = redisUtil.getCacheObject("slyang_test_set_int");


        log.warn(hello);

        // set 对象
        BcChaincode bcChaincode = new BcChaincode().setVersion("123").setName("345");
        valueOperations.set("slyang_test_set_object",bcChaincode,60 , TimeUnit.SECONDS);
        BcChaincode hello_object = redisUtil.getCacheObject("slyang_test_set_object");
        log.warn(JSON.toJSONString(hello_object));

        // set 一个值KEY不存在就设置，存在就不设置
        Boolean flag_test = valueOperations.setIfAbsent("slyang_test_set","slyang_test_set_value_new");
        log.warn(flag_test.booleanValue()+"");  // 已存在故为false

        // multiSet 多个键分别设置它们的值
        Map<String,Object> mapsMulti = new HashMap<>();
        mapsMulti.put("multi:1","multi1");
        mapsMulti.put("multi:2",bcChaincode);
        valueOperations.multiSet(mapsMulti);

        // multiGet
        List<Object> listmultiGet= valueOperations.multiGet(Arrays.asList("multi:1","multi:2"));
        log.warn(JSON.toJSONString(listmultiGet));

        // getAndSet  设置键的字符串值  并返回其旧值
        bcChaincode.setVersion("12345");
        BcChaincode bcChaincode_getAndSet = (BcChaincode) valueOperations.getAndSet("slyang_test_set_object",bcChaincode);
        log.warn(JSON.toJSONString(bcChaincode_getAndSet));

        // increment  增加一个数组 返回加后的结果
        valueOperations.set("slyang_test_increment",1,60 , TimeUnit.SECONDS);
        Long incrementValue = valueOperations.increment("slyang_test_increment",5);
        log.warn(incrementValue+"");

        // append 追加字符串 到 末尾

        // size 返回key所对应的value值得长度

        // setBit 位运算
    }

    
//    list
//		RPUSH, LPUSH, LLEN, LRANGE, LPOP, RPOP.
//
//		lrange key 0 -1//取出list的 所有
//		lrange key 0 0 //取出list的 第0个
//		lrange key 0 1//取出list的  第0个>>第一个
//
//		llen key  //取出list的数量
//		lpop key  //移除list的第一个
//		rpop key  //移除list的最后一个
    

    @Test
    public void listDetails(){

        //  列表的右边添加元素  返回当前长度
        Long rightPushLong = listOperations.rightPush("list:push:01","00002");
        // pivot 把值放到这个值的右边
        //listOperations.rightPush(,,)
        log.warn(rightPushLong+"");

        //  列表的左边添加元素  返回当前长度
        Long leftPushLong = listOperations.leftPush("list:push:01","00008");
        log.warn(leftPushLong+"");

        //  依次向左加  结果是是00020  00019 00018 不会影响之前的值
        Long leftPushAllLong = listOperations.leftPushAll("list:push:01",Arrays.asList("00018","00019","00020"));
        log.warn(leftPushAllLong+"");

        // 向又取出一个值
        String stringrightPo= (String) listOperations.rightPop("list:push:01");
        log.warn(stringrightPo);

    }


}
