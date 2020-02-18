package com.example.recommendersystem.dao;

import com.example.recommendersystem.entity.MessageTemplate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface MessageTemplateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MessageTemplate record);

    MessageTemplate selectByPrimaryKey(Integer id);

    List<MessageTemplate> selectAll();

    int updateByPrimaryKey(MessageTemplate record);

    List<MessageTemplate> getTemplates(@Param("offset") int offset, @Param("pageSize") int pageSize);

    int countTemplate();

    List<String> selectByIds(@Param("ids") List<Integer> mt);
}