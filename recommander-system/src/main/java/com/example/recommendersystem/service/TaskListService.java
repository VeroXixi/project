package com.example.recommendersystem.service;

import com.example.recommendersystem.dao.*;
import com.example.recommendersystem.entity.TaskList;
import com.example.recommendersystem.utils.PageResult;
import com.example.recommendersystem.vo.TaskListVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class TaskListService {
    @Autowired
    private TaskListMapper taskListMapper;
    @Autowired
    private UserUserlistMapper userUserlistMapper;
    @Autowired
    private TaskMapper taskMapper;

    @Transactional
    public void createTaskList(Integer userListId, String listName, String operationIds,
                   Integer taskType, String phoneTempIds, String messTempIds, Integer taskStatus, Date startDate, Date endDate, Date createDate, Integer target) {
        TaskList taskList = new TaskList();
        taskList.setTaskListName(listName);
        taskList.setUserListId(userListId);
        taskList.setBeginTime(startDate);
        taskList.setEndTime(endDate);
        taskList.setCreateTime(createDate);
        taskList.setTaskType(taskType);
        taskList.setMessageTemplateIds(messTempIds);
        taskList.setPhoneTemplateIds(phoneTempIds);
        taskList.setOperationIds(operationIds);
        taskList.setTaskStatus(taskStatus);
        taskList.setTarget(target);
        int quantity = userUserlistMapper.getuserquantity(userListId);
        taskList.setTaskQuantity(quantity);
        taskList.setDoneQuantity(0);
        //创建任务集
        taskListMapper.insert(taskList);
        //任务集id
        int taskListId = taskList.getId();
        //取出用户集对应的userId集合
        List<String> userIds = userUserlistMapper.findbyUserListId(userListId);

        //把任务平均分配给运营，创建任务表
        String[] opIds = operationIds.split(",");
        List<String> opId = Arrays.asList(opIds);
        int number = opId.size();
        List<Integer> ids = new ArrayList<>();
        for (String item:opId){
            Integer a = Integer.parseInt(item);
            ids.add(a);
        }

        int taskPerPerson = quantity / number;
        for (int i = 0; i < number; i++) {
            //获取对应用户id集合，批量加入任务表
            List<String> newList = userIds.subList(i * taskPerPerson, (i + 1) * taskPerPerson);
            int operationId = ids.get(i);
            //批量创建任务
            taskMapper.createTasks(newList, taskType, operationId, taskStatus, taskListId, startDate, endDate, phoneTempIds, messTempIds, target);
        }

        //如果不能平均分配
        if (quantity % number > 0) {
            int rest = quantity % number;
            int number2 = number;
            for (int i = 0; i < rest; i++) {
                String userId = userIds.get(taskPerPerson * number + i);
                int opIndex = (int) Math.random() * number2;
                int operationId = ids.get(opIndex);
                ids.remove(opIndex);
                number2--;
                //创建单个任务
                taskMapper.createSingleTask(userId, taskType, operationId, taskStatus, taskListId, startDate, endDate, phoneTempIds, messTempIds,target);
            }
        }
    }

        public PageResult showTaskList(Integer page, int pageSize,String listName,
                Date createTime1, Date createTime2,Date startTime1,Date startTime2){
            PageResult pageResult = new PageResult();
            pageResult.setPageIndex(page);
            pageResult.setPageSize(pageSize);
            List<TaskList> taskLists = taskListMapper.getTaskLists(listName,createTime1,createTime2,startTime1,startTime2,(page-1)*pageSize, pageSize);
            int taskListCount = taskListMapper.countTaskLists(listName,createTime1,createTime2,startTime1,startTime2);
            pageResult.setCount(taskListCount);
            List<TaskListVo> voList=new ArrayList<>();
            for (TaskList taskList:taskLists){
                TaskListVo vo=new TaskListVo();
                BigDecimal percent = new BigDecimal((float)(taskList.getDoneQuantity()/taskList.getTaskQuantity()));
                BeanUtils.copyProperties(taskList,vo);
                vo.setPercent(percent);
                voList.add(vo);
            }
            pageResult.setResultList(voList);
            pageResult.setMaxRow(taskListCount%pageSize>0?taskListCount/pageSize+1:taskListCount/pageSize);
            return pageResult;
        }
    }