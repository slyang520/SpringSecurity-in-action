package com.example.slyangsecurity;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

//@IfProfileValue(name="test-groups", value="integration")
//gradle clean test -Dtest-groups=integration

//gradle test --tests com.example.slyangsecurity.UnitTest
//https://stackoverflow.com/questions/22505533/how-to-run-only-one-test-class-on-gradle

//生成的测试报告路径  ./build/reports/tests/test/index.html
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractWebUnitTest {

    @Autowired
    WebApplicationContext wac;

    protected MockMvc mockMvc;

    @Before
    public void init(){
        mockMvc= MockMvcBuilders.webAppContextSetup(wac).build();
    }


}
