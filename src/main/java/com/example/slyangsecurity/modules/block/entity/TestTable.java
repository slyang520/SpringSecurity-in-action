package com.example.slyangsecurity.modules.block.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author author slyang
 * @since 2018-08-15
 */
@TableName("test_table")
public class TestTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.UUID)
    private String test;

    private String tt;


    public String getTest() {
        return test;
    }

    public TestTable setTest(String test) {
        this.test = test;
        return this;
    }

    public String getTt() {
        return tt;
    }

    public TestTable setTt(String tt) {
        this.tt = tt;
        return this;
    }

    @Override
    public String toString() {
        return "TestTable{" +
        ", test=" + test +
        ", tt=" + tt +
        "}";
    }
}
