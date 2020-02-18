package com.example.recommendersystem.service;

import com.example.recommendersystem.dao.MessageTemplateMapper;
import com.example.recommendersystem.entity.MessageTemplate;
import com.example.recommendersystem.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MessageTemplateService {

    @Autowired
    private MessageTemplateMapper messageTemplateMapper;

    @Transactional
    public void addTemplate(MessageTemplate messageTemplate){
        messageTemplateMapper.insert(messageTemplate);
    }

    public PageResult getTemplates(int page, int pageSize){
        PageResult pageResult = new PageResult();
        pageResult.setPageIndex(page);
        pageResult.setPageSize(pageSize);
        List<MessageTemplate> templates = messageTemplateMapper.getTemplates((page-1)*pageSize,pageSize);
        int countTemplate = messageTemplateMapper.countTemplate();
        pageResult.setCount(countTemplate);
        pageResult.setResultList(templates);
        pageResult.setMaxRow(countTemplate%pageSize>0?countTemplate/pageSize+1:countTemplate/pageSize);
        return pageResult;
    }
}
