package com.example.slyangsecurity.config.mybatis;


import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {

        log.info("insertFill");

    }

    @Override
    public void updateFill(MetaObject metaObject) {

        log.info("insertFill");

    }
}
