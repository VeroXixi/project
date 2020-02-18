package com.example.recommendersystem.dao;

import com.example.recommendersystem.entity.UserList;
import com.example.recommendersystem.entity.UserUserlist;
import com.example.recommendersystem.entity.Userc;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface UsercMapper {
    int deleteByPrimaryKey(String id);

    int insert(Userc record);

    Userc selectByPrimaryKey(String id);

    List<Userc> selectAll();

    int updateByPrimaryKey(Userc record);

    List<Userc> getUsercList(@Param("name")String name,@Param("phone")String phone,
                             @Param("serviceTypes")List<Integer> serviceTypes,@Param("userTypes") List<Integer>userTypes,
                             @Param("offset") Integer offset,@Param("pagesize")Integer pagesize);

    int countUsercList(@Param("name")String name,@Param("phone")String phone,
                       @Param("serviceTypes")List<Integer> serviceTypes,@Param("userTypes") List<Integer>userTypes);

    String getUserByPhone(@Param("phone") String phone);

    int changeStatusById(@Param("id") String id,@Param("userStatus") int userStatus);
}