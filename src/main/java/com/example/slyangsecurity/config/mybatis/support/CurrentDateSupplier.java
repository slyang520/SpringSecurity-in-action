package com.example.slyangsecurity.config.mybatis.support;

import java.util.Date;
import java.util.function.Supplier;

public class CurrentDateSupplier implements Supplier<Date> {
    @Override
    public Date get() {
        return new Date();
    }
}
