package com.example.recommendersystem.dao;

import com.example.recommendersystem.entity.UserUserlist;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface UserUserlistMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserUserlist record);

    UserUserlist selectByPrimaryKey(Integer id);

    List<UserUserlist> selectAll();

    int updateByPrimaryKey(UserUserlist record);

    int addUserUserList(@Param("listId") Integer listId, @Param("ids") List<String> ids);

    int getuserquantity(@Param("userListId") Integer userListId);

    List<String> findbyUserListId(@Param("userListId") Integer userListId);
}