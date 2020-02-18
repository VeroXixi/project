package com.example.recommendersystem.service;

import com.example.recommendersystem.dao.*;
import com.example.recommendersystem.entity.*;
import com.example.recommendersystem.utils.PageResult;
import com.example.recommendersystem.vo.TaskVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private TaskListMapper taskListMapper;

    @Autowired
    private UsercMapper usercMapper;
    @Autowired
    private PhoneTemplateMapper phoneTemplateMapper;
    @Autowired
    private MessageTemplateMapper messageTemplateMapper;

    public PageResult getTasks(Integer page, Integer pageSize, Integer taskListId, Integer operationId) {
        PageResult pageResult = new PageResult();
        pageResult.setPageIndex(page);
        pageResult.setPageSize(pageSize);
        List<Task> tasks = taskMapper.getTasks(taskListId, operationId, (page - 1) * pageSize, pageSize);
        int taskCount = taskMapper.countTask(taskListId, operationId);
        pageResult.setCount(taskCount);
        pageResult.setResultList(tasks);
        pageResult.setMaxRow(taskCount % pageSize > 0 ? taskCount / pageSize + 1 : taskCount / pageSize);
        return pageResult;
    }

    public PageResult showDetail(String id) {
        PageResult pageResult = new PageResult();
        int count = 0;
        Task task = taskMapper.selectByPrimaryKey(id);
        if (task != null) {
            count = 1;
        }
        //用户信息
        String userId = task.getUserId();
        Userc user = usercMapper.selectByPrimaryKey(userId);
        //模板信息
        String phoneTemplates = task.getPhoneTemplateIds();
        String messageTemplates = task.getMessageTemplateIds();
        String[] pt1 = phoneTemplates.split(",");
        String[] mt1 = messageTemplates.split(",");
        List<Integer> pt = new ArrayList<>();
        List<Integer> mt = new ArrayList<>();
        for (String item : pt1) {
            Integer a = Integer.parseInt(item);
            pt.add(a);
        }
        for (String item : mt1) {
            Integer a = Integer.parseInt(item);
            mt.add(a);
        }
        List<String> phoneTemplates2 = phoneTemplateMapper.selectByIds(pt);
        List<String> messageTemplates2 = messageTemplateMapper.selectByIds(mt);
        TaskVo vo = new TaskVo();
        BeanUtils.copyProperties(task, vo);
        vo.setUser(user);
        vo.setMessages(messageTemplates2);
        vo.setPhones(phoneTemplates2);
        pageResult.setResultList(vo);
        pageResult.setCount(count);
        return pageResult;
    }

    @Transactional
    public void done(String taskId) throws Exception {
        taskMapper.done(taskId);
        int listId = taskMapper.getListId(taskId);
        TaskList taskList = taskListMapper.selectByPrimaryKey(listId);
        //乐观锁
        int result = taskListMapper.increase(listId, taskList.getDoneQuantity());

        if (result != 1) {
            throw new Exception("增加数量失败");
        }

        //看修改后的DoneQuantity是否等于TaskQuantity
        taskList = taskListMapper.selectByPrimaryKey(listId);
        int doneQuantity = taskList.getDoneQuantity();
        int quantity = taskList.getTaskQuantity();
        if (doneQuantity==quantity){
            taskListMapper.accomplishList(listId);
        }
    }
}
