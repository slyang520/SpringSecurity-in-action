package com.example.slyangsecurity.config.quartz;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class QuartzService {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;
    private  Scheduler scheduler;

    @PostConstruct
    public void init(){
        scheduler=schedulerFactoryBean.getScheduler();
    }


    /**
     * 修改作业相关作业  todo
     */
//    public void updataJob(String jobName,String jobGroup) throws SchedulerException {
//        JobKey key = new JobKey(jobName,jobGroup);
//        scheduler.triggerJob(key);
//    }

    /**
     * 触发作业
     */
    public void triggerJob(String jobName,String jobGroup) throws SchedulerException {
        JobKey key = new JobKey(jobName,jobGroup);
        scheduler.triggerJob(key);
    }

    /**
     * 暂停作业
     */
    public void pauseJob(String jobName,String jobGroup) throws SchedulerException {
        JobKey key = new JobKey(jobName,jobGroup);
        scheduler.pauseJob(key);
    }

    /**
     * 恢复作业
     */
    public void resumeJob(String jobName,String jobGroup) throws SchedulerException {
        JobKey key = new JobKey(jobName,jobGroup);
        scheduler.resumeJob(key);
    }

    /**
     * 移除作业
     */
    public void removeJob(String jobName,String jobGroup) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(jobName, jobGroup);
        // 停止触发器
        scheduler.pauseTrigger(triggerKey);
        // 移除触发器
        scheduler.unscheduleJob(triggerKey);
        // 删除任务
        scheduler.deleteJob(JobKey.jobKey(jobName,jobGroup));
    }

    
}
