package com.example.recommendersystem.controller;

import com.example.recommendersystem.service.TaskListService;
import com.example.recommendersystem.service.TaskService;
import com.example.recommendersystem.utils.JsonResult;
import com.example.recommendersystem.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskListService taskListService;

    @GetMapping("list")
    //根据任务集id 分配人员 返回任务 默认只展示进行中的任务
    public Object getTasks(@RequestParam("page")Integer page, @RequestParam("pageSize") Integer pageSize,
                           @RequestParam(value = "taskListId",required = false)Integer taskListId,
                           @RequestParam(value = "operationId",required = false)Integer operationId){

        //验证参数
        if (page < 1 || pageSize < 1) {
            return JsonResult.fail("传参错误");
        }

        PageResult pageResult = taskService.getTasks(page,pageSize,taskListId,operationId);
        return JsonResult.ok(pageResult);
    }

    @GetMapping("/taskDetail")
    //展示任务详情页
    public Object showTask(@RequestParam("taskId") String id){
        PageResult pageResult = taskService.showDetail(id);
        return JsonResult.ok(pageResult);
    }

    @PostMapping("/changeStatus")
    //用post对吗？
    //改变任务状态，任务集完成数量+1
    public Object changeStatus(@RequestBody Map params)throws Exception{
        String taskId = (String) params.get("taskId");
            taskService.done(taskId);
        return "ok";
    }
}
