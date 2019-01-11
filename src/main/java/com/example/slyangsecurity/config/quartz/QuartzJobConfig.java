package com.example.slyangsecurity.config.quartz;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzJobConfig {

    @Bean
    public JobDetail sampleJobDetail() {
        return JobBuilder
                .newJob(HelloJob2.class)
                .withIdentity("sampleJob","hellogroup")
                .usingJobData("name", "World")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger sampleJobTrigger() {

        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(20).repeatForever();

        return TriggerBuilder.newTrigger().forJob(sampleJobDetail())
                .withIdentity("sampleTrigger")
                .withSchedule(scheduleBuilder).build();
    }

}
