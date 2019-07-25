package com.example.slyangsecurity.mybatis;


import com.example.slyangsecurity.modules.block.dao.BcChaincodeMapper;
import com.example.slyangsecurity.modules.block.entity.BcChaincode;
import com.example.slyangsecurity.modules.block.service.BcChaincodeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class MybatisCheck {

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Autowired
    BcChaincodeService bcChaincodeService;

    @Autowired
    BcChaincodeMapper bcChaincodeMapper;

    @BeforeClass
    public static void setUpBeforeClass() {
        // 全局执行一次
        System.out.println("Set up before class");
    }
    @Before
    public void before() {
        // 每个单元测试执行前 运行一次
    }

    @After
    public void after() {
    }

    @AfterClass
    public static void tearDownAfterClass() {
        System.out.println("Tear down After class");
    }

    
    @Test
    public void featuresConfigCheck() {
        // 1.cache check
        //localCacheScope=STATEMENT
        //cacheEnabled=false
        Assert.assertEquals("请关闭一级缓存","STATEMENT",sqlSessionFactory.getConfiguration().getLocalCacheScope().name());
        Assert.assertEquals("请关闭二级缓存",false,sqlSessionFactory.getConfiguration().isCacheEnabled());
        Assert.assertEquals("请开启驼峰<mapUnderscoreToCamelCase>",true,sqlSessionFactory.getConfiguration().isMapUnderscoreToCamelCase());
    }

    @Test
    public void featuresResultMapUnderscoreToCamel() {
       Map<String,Object> value = bcChaincodeMapper.seletMapTest();
       Assert.assertNotNull("请开启resultType=map 返回对象属性自动驼峰",value.get("testType"));


        List<Map<String, Object>> bcChaincodeList = bcChaincodeMapper.selectMaps(null);
        Assert.assertNotNull(bcChaincodeList.toArray());
    }


    @Test
    @SqlGroup({
        @Sql("/sql/sql_logic_delete.sql")
    })
    public void featuresLogicDelete() {
        //删除
        bcChaincodeService.removeById(20190101);
        //查询 带删除标记条件
        BcChaincode bcChaincode =bcChaincodeService.getById(20190101);
        Assert.assertNull(bcChaincode);

        //TODO 查询已经删除的数据
        //Assert.assertNotNull(bcChaincode);
        //Assert.assertEquals("删除标记应该设置为1",Integer.valueOf(1),bcChaincode.getDeleted());
    }

    @Test
    @SqlGroup({
            @Sql("/sql/sql_optimistic_lock.sql")
    })
    public void featuresOptimisticLock() {
        

    }


}
