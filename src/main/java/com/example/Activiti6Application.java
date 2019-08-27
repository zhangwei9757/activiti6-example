package com.example;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 *
 RepositoryService: Activiti 中每一个不同版本的业务流程的定义都需要使用一些定义文件，部署文件和支持数据 ( 例如 BPMN2.0 XML 文件，表单定义文件，流程定义图像文件等 )，这些文件都存储在 Activiti 内建的 Repository 中。Repository Service 提供了对 repository 的存取服务。
 RuntimeService: 在 Activiti 中，每当一个流程定义被启动一次之后，都会生成一个相应的流程对象实例。Runtime Service 提供了启动流程、查询流程实例、设置获取流程实例变量等功能。此外它还提供了对流程部署，流程定义和流程实例的存取服务。
 TaskService: 在 Activiti 中业务流程定义中的每一个执行节点被称为一个 Task，对流程中的数据存取，状态变更等操作均需要在 Task 中完成。Task Service 提供了对用户 Task 和 Form 相关的操作。它提供了运行时任务查询、领取、完成、删除以及变量设置等功能。
 IdentityService: Activiti 中内置了用户以及组管理的功能，必须使用这些用户和组的信息才能获取到相应的 Task。Identity Service 提供了对 Activiti 系统中的用户和组的管理功能。
 ManagementService: 提供了对 Activiti 流程引擎的管理和维护功能，这些功能不在工作流驱动的应用程序中使用，主要用于 Activiti 系统的日常维护。
 HistoryService: 用于获取正在运行或已经完成的流程实例的信息，与 Runtime Service 中获取的流程信息不同，历史信息包含已经持久化存储的永久信息，并已经被针对查询优化。
 FormService: Activiti 中的流程和状态 Task 均可以关联业务相关的数据。通过使用 Form Service 可以存取启动和完成任务所需的表单数据并且根据需要来渲染表单

     事件日志数据	ACT_EVT_LOG	事件日志
     一般数据	ACT_GE_BYTEARRAY	通用的流程定义和流程资源
                ACT_GE_PROPERTY	系统相关属性
     流程历史记录	ACT_HI_ACTINST	历史的流程实例
                 ACT_HI_ATTACHMENT	历史的流程附件
                 ACT_HI_COMMENT	历史的说明性信息
                 ACT_HI_DETAIL	历史的流程运行中的细节信息
                 ACT_HI_IDENTITYLINK	历史的流程运行过程中用户关系
                 ACT_HI_PROCINST	历史的流程实例
                 ACT_HI_TASKINST	历史的任务实例
                 ACT_HI_VARINST	历史的流程运行中的变量信息
     用户用户组表	ACT_ID_GROUP	身份信息-组信息
                 ACT_ID_INFO	身份信息-组信息
                 ACT_ID_MEMBERSHIP	身份信息-用户和组关系的中间表
                 ACT_ID_USER	身份信息-用户信息
     流程定义更新信息表	ACT_PROCDEF_INFO	流程定义更新信息
     流程定义表	ACT_RE_DEPLOYMENT	部署单元信息
                 ACT_RE_MODEL	模型信息
                 ACT_RE_PROCDEF	已部署的流程定义
     运行实例表	ACT_RU_EVENT_SUBSCR	运行时事件
                 ACT_RU_EXECUTION	运行时流程执行实例
                 ACT_RU_IDENTITYLINK	运行时用户关系信息
                 ACT_RU_JOB	运行时作业
                 ACT_RU_DEADLETTER_JOB	无效的作业
                 ACT_RU_SUSPENDED_JOB	暂停的作业
                 ACT_RU_TIMER_JOB	定时的作业
                 ACT_RU_TASK	运行时任务
                 ACT_RU_VARIABLE	运行时变量表
 *
 * 1. 使用redis,开启缓存
 * 2. 此版本整合的数据源，不用配置druid配置
 * @author jwt
 * @date 2019年8月20日
 */
@EnableCaching
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class})
@EnableSwagger2
//@EnableWebSecurity
public class Activiti6Application {

    public static void main(String[] args) {
        SpringApplication.run(Activiti6Application.class, args);
    }

}
