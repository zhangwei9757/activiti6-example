package top.zhangwei9757.com;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
/**
 * quartz中另一个常用的注解为@DisallowConcurrentExecution，该注解可以同一个时刻，同一个任务只能执行一次，
 * 不能并行执行两个或多个同一任务。但需要注意的是，多个不同的任务是可以同时执行的。
 */
public class HelloWorldJob implements Job {

    private static Logger logger = LoggerFactory.getLogger(HelloWorldJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();

        logger.info("Hello World, " + jobDataMap.get("___message"));
        int count = jobDataMap.getInt("___count");
        logger.info("___count, " + count++);
        jobDataMap.put("___count", count);


        JobDetail jobDetail = context.getJobDetail();
        logger.info("Name and Group: " + jobDetail.toString());
        logger.info("job class: " + jobDetail.getJobClass());

        Scheduler scheduler = context.getScheduler();
        logger.info("Scheduler name: " + scheduler.toString());

        // 任务执行的时间
        logger.info("Job fired at " + context.getFireTime());
        // 任务下一次执行的时间
        logger.info("Job nexe fire time: " + context.getNextFireTime());
    }
}