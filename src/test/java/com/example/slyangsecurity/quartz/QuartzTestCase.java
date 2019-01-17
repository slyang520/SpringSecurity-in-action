package com.example.slyangsecurity.quartz;

import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.DateBuilder.evenMinuteDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class QuartzTestCase {

//    https://blog.csdn.net/loveLifeLoveCoding/article/details/80449077

//      QuartzAutoConfiguration
//    https://blog.csdn.net/u010648555/article/details/54863144
//  名词
//      Job  作业。实现接口执行 具体的逻辑
//      Trigger 触发器。指定作业什么时候开始，什么时候结束，运行的间隔 (CronTrigger,SimpleTrigger)
//      Scheduler 调度器
//      JobStore  负责跟踪触发器，作业，调度器的状态。RAMJobStore（内存），JDBCJobStore（数据库存储）
//
//      一个job可以被多个Trigger 绑定，但是一个Trigger只能绑定一个job！

// Trigger 调度状态相关
// https://www.cnblogs.com/youzhibing/p/10024558.html


//    org/quartz/impl/jdbcjobstore/tables_@@platform@@.sql

    //调度标识名 集群中每一个实例都必须使用相同的名称 （区分特定的调度器实例）
//    org.quartz.scheduler.instanceName：DefaultQuartzScheduler
    //ID设置为自动获取 每一个必须不同 （所有调度器实例中是唯一的） 
//    org.quartz.scheduler.instanceId ：AUTO
    //加入集群 true 为集群 false不是集群
//    org.quartz.jobStore.isClustered ： false

    @Test
    public void simpleScheduleTest() throws SchedulerException, InterruptedException {

        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();

        // define the job and tie it to our HelloJob class
        JobDetail job = newJob(HelloJob.class)
                .withIdentity("job1", "group1")
                .build();

        // compute a time that is on the next round minute
        Date runTime = evenMinuteDate(new Date());

        // Trigger the job to run on the next round minute
        Trigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .startAt(runTime)
                .build();

        // Tell quartz to schedule the job using our trigger
        sched.scheduleJob(job, trigger);

        // At this point, the job has been schedule to run when its trigger fires.
        // However, the scheduler is not yet running. So, we must tell the scheduler to start up!
        sched.start();


        Thread.sleep(90L * 1000L);

        sched.shutdown(true);
    }

//    2019-01-11 09:27:00.016            : HelloJob says: group1.job1 executing at Fri Jan 11 09:27:00 CST 2019
//    2019-01-11 09:27:20.006            : HelloJob says: group1.job1 executing at Fri Jan 11 09:27:20 CST 2019
//    2019-01-11 09:27:40.005            : HelloJob says: group1.job1 executing at Fri Jan 11 09:27:40 CST 2019
//    2019-01-11 09:28:00.008            : HelloJob says: group1.job1 executing at Fri Jan 11 09:28:00 CST 2019
    @Test
    public void cronScheduleTest() throws SchedulerException, InterruptedException {

        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();

        // define the job and tie it to our HelloJob class
        JobDetail job = newJob(HelloJob.class)
                .withIdentity("job1", "group1")
                .build();


        // run every 20 seconds
        Trigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .withSchedule(cronSchedule("0/20 * * * * ?"))
                .build();

        // Tell quartz to schedule the job using our trigger
        sched.scheduleJob(job, trigger);

        sched.start();

        Thread.sleep(90L * 1000L);

        sched.shutdown(true);
    }

    
    // 传递JOB 参数
    @Test
    public void jobParmsTest() throws SchedulerException, InterruptedException {

        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();

        JobDetail job1 = newJob(ColorJob.class)
                .withIdentity("job1", "group1")
                .build();

        job1.getJobDataMap().put(ColorJob.FAVORITE_COLOR, "Green");
        job1.getJobDataMap().put(ColorJob.EXECUTION_COUNT, 1);

        SimpleTrigger trigger1 = newTrigger()
                .withIdentity("trigger1", "group1")
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(10)
                        .withRepeatCount(4))
                .build();

        sched.scheduleJob(job1, trigger1);

        sched.start();

        Thread.sleep(90L * 1000L);

        sched.shutdown(true);
    }


    // 处理作业异常
    @Test
    public void jobException() throws SchedulerException, InterruptedException {

        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler sched = sf.getScheduler();

        //  run every 6 seconds indefinitely
        JobDetail job = newJob(BadJob1.class)
                .withIdentity("badJob1", "group1")
                .build();

        SimpleTrigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(6)
                        .repeatForever())
                .build();

        Date ft = sched.scheduleJob(job, trigger);


        //  run every 3 seconds indefinitely.
        job = newJob(BadJob2.class)
                .withIdentity("badJob2", "group1")
                .build();

        trigger = newTrigger()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(3)
                        .repeatForever())
                .build();

        ft = sched.scheduleJob(job, trigger);

        sched.start();
        Thread.sleep(60L * 1000L);
        sched.shutdown(true);
    }


}
