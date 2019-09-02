package top.zhangwei9757.com;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleQuartzExample {

    private static Logger logger = LoggerFactory.getLogger(SimpleQuartzExample.class);

    public static void main(String[] args) throws SchedulerException, InterruptedException {

        SimpleQuartzExample exam = new SimpleQuartzExample();

        logger.info("init scheduler componets");

        // 创建任务
        JobDetail jobDetail = exam.createJobDetail();
        // 创建触发器
        Trigger trigger = exam.createTrigger();
        // 创建调度器
        Scheduler scheduler = exam.createScheduler();
        // 构建调度任务
        scheduler.scheduleJob(jobDetail, trigger);

        logger.info("execute scheduler");
        // 开启调度器
        scheduler.start();
        // 一分钟后关闭调度器
        Thread.sleep(10000);
        scheduler.shutdown();

        logger.info("shut down scheduler");
    }

    protected Scheduler createScheduler() throws SchedulerException {
        return StdSchedulerFactory.getDefaultScheduler();
    }

    protected JobDetail createJobDetail() {
        return JobBuilder.newJob(HelloWorldJob.class) // 待执行的任务
                .withIdentity("HelloWorld_Job", "HelloWorld_Group") // 名称与组名组成Scheduler中任务的唯一标识
                .usingJobData("___message", "___welcom to study quartz") // 存储Job的状态信息
                .usingJobData("___count", 0) // 将count初始化为0
                .build(); // 构建
    }

    protected Trigger createTrigger() {
        return TriggerBuilder.newTrigger()
                .withIdentity("HelloWorld_Trigger", "HelloWorld_Group") // 名称与组名组成Scheduler中触发器的唯一标识
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule() // 创建SimpleTrigger
                                .withIntervalInSeconds(2) // 10秒间隔
                                .repeatForever() // 重复循环
                ).build(); // 构建
    }
}