package com.example.recommendersystem.controller;

import com.example.recommendersystem.entity.Userc;
import com.example.recommendersystem.service.UserService;
import com.example.recommendersystem.type.ServiceType;
import com.example.recommendersystem.type.UserType;
import com.example.recommendersystem.utils.JsonResult;
import com.example.recommendersystem.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tuijian")
public class UserController {
    @Autowired
    private UserService userService;

    //用户信息接受接口
    @PostMapping("/add")
    public Object addUserc(@RequestBody Map params) {
        String name = (String) params.get("name");
        String phone = (String) params.get("phone");
        int serviceType = (int) params.get("serviceType");
        int userType = (int) params.get("userType");
        Userc userc = new Userc();
        userc.setName(name);
        userc.setPhone(phone);
        userc.setServiceType(serviceType);
        userc.setUserType(userType);
        userService.addUser(userc);
        return "ok";
    }

    //根据筛选条件分页返回用户信息
    //controller层主要做信息的验证
    @GetMapping("/user/list")
    public Object showUsers(@RequestParam("page") int page,
                            @RequestParam("pageSize") int pageSize,
                            @RequestParam(value = "name",required = false) String name,
                            @RequestParam(value = "phone",required = false) String phone,
                            @RequestParam(value = "serviceTypes",required = false) List<Integer> serviceTypes,
                            @RequestParam(value = "userTypes",required = false) List<Integer> userTypes) {
        if (page < 1 || pageSize < 1) {
            return JsonResult.fail("传参错误");
        }
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
        PageResult pageResult = userService.getUserList(name, phone, serviceTypes, userTypes, page, pageSize);
        return JsonResult.ok(pageResult);
    }

    //更改用户状态并判断任务是否完成
    @PostMapping("/changeStatus")
    public Object changeUserStatus(@RequestBody Map params)throws Exception{
        String phone = (String) params.get("phone");
        int userStatus = (int) params.get("userStatus");
        userService.changeStatus(phone,userStatus);
        return "ok";
    }

}
