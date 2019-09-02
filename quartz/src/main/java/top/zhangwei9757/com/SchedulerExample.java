package top.zhangwei9757.com;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class SchedulerExample {

    private static Logger logger = LoggerFactory.getLogger(SchedulerExample.class);

    public static void main(String[] args) {
        // 创建工厂实例
        StdSchedulerFactory factory = new StdSchedulerFactory();

        // 创建配置工厂的属性对象
        Properties props = new Properties();
        props.put(StdSchedulerFactory.PROP_THREAD_POOL_CLASS, "org.quartz.simpl.SimpleThreadPool"); // 线程池定义
        props.put("org.quartz.threadPool.threadCount", "10"); // 默认Scheduler的线程数

        try {
            // 使用定义的属性初始化工厂
            factory.initialize(props);

            Scheduler scheduler = factory.getScheduler();

            scheduler.start();
            logger.info("scheudler started, metadata: " + scheduler.getMetaData());
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}