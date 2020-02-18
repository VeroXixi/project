package com.example.recommendersystem.service;

import com.example.recommendersystem.dao.TaskMapper;
import com.example.recommendersystem.dao.UsercMapper;
import com.example.recommendersystem.entity.UserList;
import com.example.recommendersystem.entity.UserUserlist;
import com.example.recommendersystem.entity.Userc;
import com.example.recommendersystem.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UsercMapper usercMapper;
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private TaskService taskService;

    @Transactional
    public boolean addUser(Userc user){
        usercMapper.insert(user);
        return true;
    }

    public PageResult getUserList(String name,String phone,
                                   List<Integer> serviceTypes, List<Integer> userTypes,
                                   int page, int pageSize){
        PageResult pageResult=new PageResult();
        pageResult.setPageIndex(page);
        pageResult.setPageSize(pageSize);
        List<Userc> usercList = usercMapper.getUsercList(name, phone, serviceTypes, userTypes, (page-1)*pageSize, pageSize);
        int userCount = usercMapper.countUsercList(name, phone, serviceTypes, userTypes);

        pageResult.setResultList(usercList);
        pageResult.setMaxRow(userCount%pageSize>0?userCount/pageSize+1:userCount/pageSize);
        pageResult.setCount(userCount);

        return pageResult;
    }

    @Transactional
    public boolean changeStatus(String phone,int userStatus) throws Exception{
        //根据手机号找到用户id
        String userId = usercMapper.getUserByPhone(phone);
        //更改用户的状态
        usercMapper.changeStatusById(userId,userStatus);
        //找到进行中的任务中有没有包括这个用户
        String taskId = taskMapper.getTaskByUser(userId);
        if (taskId!=null){//如果进行中的任务有这用户，看用户状态是否到目标，到了任务设为完成
            int target = taskMapper.getTargetById(taskId);
            if (userStatus>=target){//将任务状态改为完成
                taskService.done(taskId);
            }
        }
        return true;
    }








}
