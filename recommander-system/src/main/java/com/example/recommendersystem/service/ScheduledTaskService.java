package com.example.recommendersystem.service;

import com.example.recommendersystem.dao.TaskListMapper;
import com.example.recommendersystem.dao.TaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Component
public class ScheduledTaskService {
    @Autowired
    private TaskListMapper taskListMapper;
    @Autowired
    private TaskMapper taskMapper;

    //找到未开始且开始时间小于等于此刻的任务表，把任务表和任务的状态改为进行中
    @Scheduled(cron = "0 40 11 * * ? ")
    @Transactional
    public Object startTasks(){
        Date now = new Date(System.currentTimeMillis());
        //找到未开始且开始时间小于等于此刻的任务表
        List<Integer> ListId = taskListMapper.getUnStartedTaskList(now);
        //把这些任务表的状态改成进行中
        taskListMapper.startList(ListId);
        //把这些任务表中的任务的状态改成进行中
        taskMapper.startTasks(ListId);
        return "ok";
    }

    //找到进行中且结束时间小于此刻的任务表，把任务表和任务的状态改为未完成
    @Scheduled(cron = "0 20 12 * * ? ")
    @Transactional
    public Object endTasks(){
        Date now = new Date(System.currentTimeMillis());
        //找到进行中且结束时间小于此刻的任务集
        List<Integer> ListId = taskListMapper.getUndoneTaskList(now);
        //把这些任务集的状态改成未完成
        taskListMapper.endList(ListId);
        //把这些任务集中进行中的任务状态改成未完成
        taskMapper.endTasks(ListId);
        return "ok";
    }
}
