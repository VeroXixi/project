package com.example.recommendersystem.dao;

import com.example.recommendersystem.entity.TaskList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
@Mapper
public interface TaskListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TaskList record);

    TaskList selectByPrimaryKey(Integer id);

    List<TaskList> selectAll();

    int updateByPrimaryKey(TaskList record);

    List<TaskList> getTaskLists(@Param("listName") String listName,
                                @Param("createTime1") Date createTime1, @Param("createTime2") Date createTime2,
                                @Param("startTime1") Date startTime1, @Param("startTime2") Date startTime2,
                                @Param("offset")Integer offset, @Param("pageSize")Integer pageSize);

    int countTaskLists(@Param("listName") String listName,
                       @Param("createTime1") Date createTime1, @Param("createTime2") Date createTime2,
                       @Param("startTime1") Date startTime1, @Param("startTime2") Date startTime2);

    int increase(@Param("listId")Integer listId, @Param("origin")int num);

    int accomplishList(@Param("listId") Integer listId);

    List<Integer> getUnStartedTaskList(@Param("now")Date now);

    int startList(@Param("listId") List<Integer> listId);

    List<Integer> getUndoneTaskList(@Param("now") Date now);

    int endList(@Param("listId") List<Integer> ListId);
}