import org.activiti.engine.*;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.spring.SpringAsyncExecutor;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.AbstractProcessEngineAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class ActivitiConfig extends AbstractProcessEngineAutoConfiguration {

    /**
     * 数据源
     */
    @Autowired
    private DataSource dataSource;

    /**
     * 初始化配置，将创建28张表
     * 注入 spring容器，方便操作
     *
     * @return
     */
    @Bean
    public StandaloneProcessEngineConfiguration processEngineConfiguration() {
        StandaloneProcessEngineConfiguration configuration = new StandaloneProcessEngineConfiguration();
        configuration.setDataSource(dataSource);
        configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        configuration.setAsyncExecutorActivate(false);
        return configuration;

    }

    @Bean
    public SpringProcessEngineConfiguration springProcessEngineConfiguration(PlatformTransactionManager transactionManager,
                                                                             SpringAsyncExecutor springAsyncExecutor) throws IOException {
        SpringProcessEngineConfiguration springProcessEngineConfiguration = this.baseSpringProcessEngineConfiguration(dataSource, transactionManager, springAsyncExecutor);
        springProcessEngineConfiguration.setActivityFontName("宋体");
        springProcessEngineConfiguration.setAnnotationFontName("宋体");
        springProcessEngineConfiguration.setLabelFontName("宋体");
        return springProcessEngineConfiguration;
    }

    @Bean
    public ProcessEngine processEngine() {
        return processEngineConfiguration().buildProcessEngine();
    }

    @Bean
    public RepositoryService repositoryService() {
        return processEngine().getRepositoryService();
    }

    @Bean
    public RuntimeService runtimeService() {
        return processEngine().getRuntimeService();
    }

    @Bean
    public TaskService taskService() {
        return processEngine().getTaskService();
    }
}