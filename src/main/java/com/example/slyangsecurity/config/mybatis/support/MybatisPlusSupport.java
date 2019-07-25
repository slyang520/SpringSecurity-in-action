package com.example.slyangsecurity.config.mybatis.support;


import com.baomidou.mybatisplus.annotation.FieldFill;

import java.lang.annotation.*;
import java.util.function.Supplier;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(MybatisPlusSupportS.class)
public @interface MybatisPlusSupport {

    Class<? extends Supplier> getFiledValue();

    String filed();

    FieldFill fill() default FieldFill.INSERT_UPDATE;

}
