package com.example.slyangsecurity.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

import java.util.Date;

@PersistJobDataAfterExecution  // 执行后的持久作业数据
@DisallowConcurrentExecution       // 不允许并发执行
@Slf4j
public class ColorJob implements Job {

    public static String  FAVORITE_COLOR = "FAVORITE_COLOR";
    public static String  EXECUTION_COUNT = "EXECUTION_COUNT";

    private int _counter = 1;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        JobDataMap data = jobExecutionContext.getJobDetail().getJobDataMap();
        String favoriteColor = data.getString(FAVORITE_COLOR);
        int count = data.getInt(EXECUTION_COUNT);

        count++;
        data.put(EXECUTION_COUNT, count);

        log.info("ColorJob: " + " executing at " + new Date() + "\n" +
                "  favorite color is " + favoriteColor + "\n" +
                "  execution count (from job map) is " + count + "\n" +
                "  execution count (from job member variable) is " + _counter);
        
    }
}
