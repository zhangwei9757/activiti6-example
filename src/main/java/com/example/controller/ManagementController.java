package com.example.controller;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jwt on 2019-8-12
 * <p>
 */
@Controller
@Slf4j
public class ManagementController {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private HistoryService historyService;

    /**
     * 发起流程
     *
     * @return
     */
    @PostMapping("/initiationProcess")
    @ResponseBody
    public Object initiationProcess() {
        log.info("method startActivityDemo begin....");

        log.info("调用流程存储服务，已部署流程数量：" + repositoryService.createDeploymentQuery().count());

        //流程启动
        ProcessInstance pi = runtimeService.startProcessInstanceByKey("leave");
        log.info("启动流程成功！");

        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        log.info("任务ID: " + task.getId());
        log.info("任务的办理人: " + task.getAssignee());
        log.info("任务名称: " + task.getName());
        log.info("任务的创建时间: " + task.getCreateTime());
        log.info("流程实例ID: " + task.getProcessInstanceId());


        // 开启后，环节会走到发起请假请求，要完成这个环节，才能到下一个审核环节
//        taskService.complete(task.getId());

        log.info("method startActivityDemo end....");
        return pi.toString();
    }

    /**
     * 查询我的任务
     *
     * @return
     */
    @GetMapping("/findTask/{name}")
    @ResponseBody
    public List findTask(@PathVariable("name") String name) {
        List<Task> zw = taskService.createTaskQuery().taskAssignee(name).list();
        ArrayList<String> list = Lists.newArrayList();
        zw.stream().forEach(System.out::println);
        zw.stream().forEach(f -> list.add(f.getId()));
        return list;
    }

    @PutMapping("/exec/{taskId}")
    @ResponseBody
    public String exec(@PathVariable("taskId") String taskId) {
        taskService.complete(taskId);
        return "success";
    }

    @GetMapping("/historyTask")
    @ResponseBody
    public List historyTask() {
        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery().list();

        ArrayList<String> resultList = Lists.newArrayList();
        list.stream().forEach(System.out::println);
        list.stream().forEach(f -> resultList.add(f.getAssignee()));
        return resultList;
    }
}
