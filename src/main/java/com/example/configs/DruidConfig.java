//package com.example.configs;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import com.alibaba.druid.support.http.StatViewServlet;
//import com.alibaba.druid.support.http.WebStatFilter;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 阿里数据源配置
// */
//@Configuration
//public class DruidConfig {
//
//    /**
//     * 配置阿里数据源
//     *
//     * @return
//     */
//    @ConfigurationProperties(prefix = "spring.datasource")
//    @Bean
//    public DataSource druid() {
//        return new DruidDataSource();
//    }
//
//    /**
//     * 配置Druid的监控
//     * 1、配置一个管理后台的Servlet
//     *
//     * @return
//     */
//    @Bean
//    public ServletRegistrationBean statViewServlet() {
//        //配置参数参考ResourceServlet类
//        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
//        Map<String, String> initParams = new HashMap<>(10);
//        initParams.put("loginUsername", "admin");
//        initParams.put("loginPassword", "admin");
//        //默认就是允许所有访问
//        initParams.put("allow", "");
//        //拒绝谁
//        initParams.put("deny", "");
//        bean.setInitParameters(initParams);
//        return bean;
//    }
//
//    /**
//     * 2、配置一个web监控的filter
//     *
//     * @return
//     */
//    @Bean
//    public FilterRegistrationBean webStatFilter() {
//        FilterRegistrationBean bean = new FilterRegistrationBean();
//        bean.setFilter(new WebStatFilter());
//        Map<String, String> initParams = new HashMap<>(10);
//        initParams.put("exclusions",  "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*,/swagger-ui.html");
//        bean.setInitParameters(initParams);
//        bean.setUrlPatterns(Arrays.asList("/*"));
//        return bean;
//    }
//}
