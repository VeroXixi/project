package com.example.recommendersystem.dao;

import com.example.recommendersystem.entity.Operation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface OperationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Operation record);

    Operation selectByPrimaryKey(Integer id);

    List<Operation> selectAll();

    int updateByPrimaryKey(Operation record);
}