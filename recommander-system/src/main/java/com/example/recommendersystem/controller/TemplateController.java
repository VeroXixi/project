package com.example.recommendersystem.controller;


import com.example.recommendersystem.entity.MessageTemplate;
import com.example.recommendersystem.entity.PhoneTemplate;
import com.example.recommendersystem.service.MessageTemplateService;
import com.example.recommendersystem.service.PhoneTemplateService;
import com.example.recommendersystem.utils.JsonResult;
import com.example.recommendersystem.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/Template")
public class TemplateController {
    @Autowired
    private MessageTemplateService messageTemplateService;
    @Autowired
    private PhoneTemplateService phoneTemplateService;

    //创建电话模板
    @PostMapping("/createPhone")
    public Object phone(@RequestBody Map params) {
        String content = (String) params.get("content");
        PhoneTemplate phoneTemplate = new PhoneTemplate();
        phoneTemplate.setTemplate(content);
        phoneTemplateService.addTemplate(phoneTemplate);
        return "ok";
    }
    //创建短信模板
    @PostMapping("/createMessage")
    public Object message(@RequestBody Map params) {
        String content = (String) params.get("content");
        MessageTemplate messageTemplate = new MessageTemplate();
        messageTemplate.setTemplate(content);
        messageTemplateService.addTemplate(messageTemplate);
        return "ok";
    }

    //分页展示电话模板
    @GetMapping("/showPhone")
    public Object getPhoneTemplate(@RequestParam("page")int page,@RequestParam("pageSize")int pageSize){
        //验证参数
        if (page < 1 || pageSize < 1) {
            return JsonResult.fail("传参错误");
        }
        PageResult pageResult = phoneTemplateService.getTemplates(page,pageSize);
        return pageResult;
    }

    //分页展示短信模板
    @GetMapping("/showMessage")
    public Object getMessageTemplate(@RequestParam("page")int page,@RequestParam("pageSize")int pageSize){
        //验证参数
        if (page < 1 || pageSize < 1) {
            return JsonResult.fail("传参错误");
        }
        PageResult pageResult = messageTemplateService.getTemplates(page,pageSize);
        return pageResult;
    }
}
