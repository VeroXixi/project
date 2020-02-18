package com.example.recommendersystem.dao;

import com.example.recommendersystem.entity.PhoneTemplate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface PhoneTemplateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PhoneTemplate record);

    PhoneTemplate selectByPrimaryKey(Integer id);

    List<PhoneTemplate> selectAll();

    int updateByPrimaryKey(PhoneTemplate record);

    void addTemplate(PhoneTemplate phoneTemplate);

    List<PhoneTemplate> getTemplates(@Param("offset") int offset, @Param("pageSize") int pageSize);

    int countTemplates ();

    List<String> selectByIds(@Param("ids") List<Integer> ids);
}