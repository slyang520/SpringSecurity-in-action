package com.example.slyangsecurity.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

@Slf4j
public class BadJob1 implements Job {
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
            // this job will refire immediately
            // 立即重新安装
            e2.refireImmediately();
            throw e2;
        }

    }
}
