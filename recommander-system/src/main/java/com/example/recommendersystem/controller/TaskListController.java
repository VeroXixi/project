package com.example.recommendersystem.controller;

import com.example.recommendersystem.service.TaskListService;
import com.example.recommendersystem.type.TaskType;
import com.example.recommendersystem.type.UserType;
import com.example.recommendersystem.utils.JsonResult;
import com.example.recommendersystem.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/task")
public class TaskListController {

    @Autowired
    private TaskListService taskListService;

    //创建任务集，并平均分配给运营
    @PostMapping("/createTaskList")
    public Object createTaskList(@RequestBody Map params) throws Exception{
        Integer userListId = (Integer) params.get("userListId");
        String listName = (String) params.get("listName");
        String operationIds = (String) params.get("opIds");
        Integer taskType = (Integer) params.get("taskType");
        String startTime = (String) params.get("startTime");
        String endTime = (String) params.get("endTime");
        Date createDate = new Date(System.currentTimeMillis());
        Integer target = (Integer) params.get("target");

        //电话模板id 短信模板id
        String phoneTempIds = (String) params.get("phoneTempIds");
        String messTempIds = (String) params.get("messTempIds");
        try{
            TaskType.valueOf(taskType);
        } catch (Exception e){
            e.printStackTrace();
            return JsonResult.fail("不存在的任务类型");
        }

        try{
            UserType.valueOf(target);
        } catch (Exception e){
            e.printStackTrace();
            return JsonResult.fail("不存在的目标类型");
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate= formatter.parse(startTime);
        Date endDate = formatter.parse(endTime);
        if (startDate.compareTo(endDate) >= 0) {
            return JsonResult.fail("开始时间必须小于结束时间");
        }
        //根据开始、结束时间判断任务状态
        int taskStatus = 0;
        Date now = new Date(System.currentTimeMillis());
        if (startDate.compareTo(now) > 0){
            taskStatus = 1;
        }
        else if (startDate.compareTo(now) <= 0 && now.compareTo(endDate)<=0){
            taskStatus = 2;
        }
        else {
            taskStatus =4;
        }

        //创建任务集表，并把任务平均分配给运营
        taskListService.createTaskList(userListId,listName,operationIds,taskType, phoneTempIds,
                messTempIds, taskStatus,startDate,endDate,createDate, target);
        return JsonResult.ok("成功创建任务集，并分配任务");
    }

    //根据筛选条件分页展示任务集
    @GetMapping("/taskList")
    public Object findTaskList(@RequestParam("page") int page,
                               @RequestParam("pageSize") int pageSize,
                               @RequestParam(value = "listName",required = false) String listName,
                               @RequestParam(value = "createTime1",required = false)String createTime1,
                               @RequestParam(value = "createTime2",required = false)String createTime2,
                               @RequestParam(value = "startTime1",required = false)String startTime1,
                               @RequestParam(value = "startTime2",required = false)String startTime2)throws Exception{

        if (page < 1 || pageSize < 1) {
            return JsonResult.fail("传参错误");
        }

        Date createDate1=null;
        Date createDate2=null;
        Date startDate1=null;
        Date startDate2=null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        if (createTime1!=null){
            createDate1 = formatter.parse(createTime1);
        }

        if (createTime2!=null){
            createDate2 = formatter.parse(createTime2);
        }

        if (startTime1!=null){
            startDate1 = formatter.parse(startTime1);
        }

        if (startTime2!=null){
            startDate2 = formatter.parse(startTime2);
        }

        if (createDate1!=null && createDate2!=null){
            if (createDate1.compareTo(createDate2) > 0) {
                return JsonResult.fail("起始时间必须小于截至时间");
            }
        }

        if (startDate1!=null && startDate2!=null){
            if (startDate1.compareTo(startDate2) > 0) {
                return JsonResult.fail("起始时间必须小于截至时间");
            }
        }

        PageResult pageResult = taskListService.showTaskList(page,pageSize,listName,createDate1,createDate2,
                startDate1,startDate2);
        return JsonResult.ok(pageResult);

    }

}
