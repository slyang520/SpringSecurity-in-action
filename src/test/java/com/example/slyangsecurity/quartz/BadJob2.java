package com.example.slyangsecurity.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@Slf4j
public class BadJob2 implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        try {
            int zero = 0;
            int calculation = 4815 / zero;
        }
        catch (Exception e) {
            log.info("--- Error in job!");
            JobExecutionException e2 =
                    new JobExecutionException(e);
            // Quartz will automatically unschedule
            // all triggers associated with this job
            // so that it does not run again

            // 引发JobExecutionException  并设置为确保Quartz不再运行该作业。
            e2.setUnscheduleAllTriggers(true);
            throw e2;
        }

    }
}
