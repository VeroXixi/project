package com.example.recommendersystem.controller;
import com.example.recommendersystem.service.UserListService;
import com.example.recommendersystem.type.ServiceType;
import com.example.recommendersystem.type.UserType;
import com.example.recommendersystem.utils.JsonResult;
import com.example.recommendersystem.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/userList")
public class UserListController {
    @Autowired
    private UserListService userListService;

    //根据所传id建立用户集
    @PostMapping("/createUserList")
    public Object createUserList(@RequestBody Map params) {
        String listName = (String) params.get("listName");
        Date date = new Date(System.currentTimeMillis());
        List<String> ids = (List<String>) params.get("ids");
        //判断id是否为空
        if (CollectionUtils.isEmpty(ids)){
            return JsonResult.fail("id集不能为空");
        }
        //需要验证用户表中是否存在id吗？？？
        userListService.addUserList(listName, date, ids);
        return JsonResult.ok("根据用户id建立用户集成功");
    }


    //通过过滤条件筛选用户及数量建立用户集
    @PostMapping("/createUserList2")
    public Object createUserListByName(@RequestParam(value = "name", required = false) String name,
                                       @RequestParam(value = "phone", required = false) String phone,
                                       @RequestParam(value = "serviceTypes", required = false) List<Integer> serviceTypes,
                                       @RequestParam(value = "userTypes", required = false) List<Integer> userTypes,
                                       @RequestParam(value = "listName") String listName) {
        Date date = new Date(System.currentTimeMillis());

        //验证服务类型是否为枚举类的实例
        if (!CollectionUtils.isEmpty(serviceTypes)) {
            for (Integer serviceType : serviceTypes) {
                try {
                    ServiceType.valueOf(serviceType);
                } catch (Exception e) {
                    e.printStackTrace();
                    return JsonResult.fail("不存在相关服务类型");
                }
            }
        }

        //验证用户类型是否为枚举类的实例
        if (!CollectionUtils.isEmpty(userTypes)) {
            for (Integer userType : userTypes) {
                try {
                    UserType.valueOf(userType);
                } catch (Exception e) {
                    e.printStackTrace();
                    return JsonResult.fail("不存在相关用户类型");
                }
            }
        }

        userListService.createListByInfo(name, phone, serviceTypes, userTypes, listName, date);
        return JsonResult.ok("创建用户集成功");
    }

    //通过过滤条件展示用户集列表
    @GetMapping("/findUserList")
    public Object findList(@RequestParam("page") int page,
                           @RequestParam("pageSize") int pageSize,
                           @RequestParam(value = "listName", required = false) String listName,
                           @RequestParam(value = "time1", required = false) String time1,
                           @RequestParam(value = "time2",required = false) String time2
    ) throws Exception{

        if (page < 1 || pageSize < 1) {
            return JsonResult.fail("传参错误");
        }

        Date date1=null;
        Date date2=null;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");

        if (time1!=null){
            date1 = formatter.parse(time1);
        }
        if (time2!=null){
            date2 = formatter.parse(time2);
        }

        if (time1!=null && time2!=null){
            if (date1.compareTo(date2) > 0) {
                return JsonResult.fail("起始时间必须小于截至时间");
            }
        }

        PageResult pageResult = userListService.getUserList(listName,date1,date2,page,pageSize);
        return JsonResult.ok(pageResult);
    }
}

