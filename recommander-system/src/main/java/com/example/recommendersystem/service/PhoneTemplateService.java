package com.example.recommendersystem.service;

import com.example.recommendersystem.dao.PhoneTemplateMapper;
import com.example.recommendersystem.entity.PhoneTemplate;
import com.example.recommendersystem.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PhoneTemplateService {
    @Autowired
    private PhoneTemplateMapper phoneTemplateMapper;

    @Transactional
    public void addTemplate(PhoneTemplate phoneTemplate){
        phoneTemplateMapper.addTemplate(phoneTemplate);
    }

    public PageResult getTemplates(int page, int pageSize){
        PageResult pageResult = new PageResult();
        pageResult.setPageIndex(page);
        pageResult.setPageSize(pageSize);
        List<PhoneTemplate> templates = phoneTemplateMapper.getTemplates((page-1)*pageSize,pageSize);
        int templateCount = phoneTemplateMapper.countTemplates();
        pageResult.setCount(templateCount);
        pageResult.setResultList(templates);
        pageResult.setMaxRow(templateCount%pageSize>0?templateCount/pageSize+1:templateCount/pageSize);
        return pageResult;
    }
}
