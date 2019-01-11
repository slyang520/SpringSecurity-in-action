package com.example.slyangsecurity.config.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.quartz.QuartzAutoConfiguration;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
public class HelloJob2 extends QuartzJobBean {

    private Logger logger = LoggerFactory.getLogger(HelloJob2.class);

    QuartzAutoConfiguration quartzAutoConfiguration;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        logger.info("i am excute   hehheheh ");
    }
}
