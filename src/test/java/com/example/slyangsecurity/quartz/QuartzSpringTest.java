package com.example.slyangsecurity.quartz;

import com.example.slyangsecurity.config.quartz.QuartzService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class QuartzSpringTest {

    @Autowired
    QuartzService quartzService;

    @Test
    public void addDynamicJob() throws SchedulerException {

        quartzService.addDynamicJob("123","345");
    
    }

}
