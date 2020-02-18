package com.example.recommendersystem.dao;

import com.example.recommendersystem.entity.UserList;
import com.example.recommendersystem.entity.UserUserlist;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
@Mapper
public interface UserListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserList record);

    UserList selectByPrimaryKey(Integer id);

    List<UserList> selectAll();

    int updateByPrimaryKey(UserList record);

    int addUserList(UserList userList);

    List<UserList> findUserLists(@Param("listName")String listName, @Param("date1") Date date1,
                                 @Param("date2") Date date2,
                                 @Param("offset") int offset, @Param("pageSize") int pageSize);

    int countList(@Param("listName")String listName, @Param("date1") Date date1, @Param("date2") Date date2);



}