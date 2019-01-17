package com.example.slyangsecurity.config.quartz;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class QuartzService {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;
    private  Scheduler scheduler;

    @Autowired
    DynamicJob dynamicJob;

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
     * 动态添加作业
     */
    public void addDynamicJob(String jobName, String jobGroup) throws SchedulerException {

        JobDetail job = JobBuilder
                        .newJob(DynamicJob.class)
                        .withIdentity(jobName, jobGroup)
                        .usingJobData("hello","hello_value")
                        .usingJobData("hello2","hello_value")
                    .build();

        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(40).repeatForever();

            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroup)
                    .withSchedule(scheduleBuilder).build();

            scheduler.scheduleJob(job, trigger);
    }

    public  void modifyJobTime(String triggerName, String triggerGroupName, String cron) {
        TriggerKey triggerKey = TriggerKey.triggerKey(triggerName, triggerGroupName);
        try {

            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (trigger == null) {
                return;
            }
            String oldTime = trigger.getCronExpression();
            if (!oldTime.equalsIgnoreCase(cron)) {
                // trigger已存在，则更新相应的定时设置
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
                // 按新的cronExpression表达式重新构建trigger
                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
                // 按新的trigger重新设置job执行

                scheduler.resumeTrigger(triggerKey);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

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
