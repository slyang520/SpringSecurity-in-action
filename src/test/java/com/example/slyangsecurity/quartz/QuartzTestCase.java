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


}
