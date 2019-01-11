package com.example.slyangsecurity.config.quartz;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzConfig implements CommandLineRunner {


    @Override
    public void run(String... args) throws SchedulerException {

//        // 1、创建一个JobDetail实例，指定Quartz
//        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
//                // 任务名，任务组
//                .withIdentity("job1_1", "jGroup1")
//                .build();
//
//        // 2、创建Trigger
//        //  20S执行一次
//        Trigger trigger = TriggerBuilder.newTrigger()
//                .withIdentity("trigger1_1", "tGroup1").startNow()
//                .withSchedule(cronSchedule("0/20 * * * * ?"))
//                .build();
//
//
//        schedulerFactoryBean.getScheduler().scheduleJob(jobDetail,trigger);


    }

}
