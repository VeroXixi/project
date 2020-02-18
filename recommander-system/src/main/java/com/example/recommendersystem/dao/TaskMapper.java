package com.example.recommendersystem.dao;

import com.example.recommendersystem.entity.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
@Mapper
public interface TaskMapper {
    int deleteByPrimaryKey(String id);

    int insert(Task record);

    Task selectByPrimaryKey(String id);

    List<Task> selectAll();

    int updateByPrimaryKey(Task record);

    int createTasks(@Param("userIds") List<String>userIds, @Param("taskType") int taskType,
                   @Param("operationId") int operationId, @Param("taskStatus") int taskStatus,
                   @Param("taskListId") int taskListId,
                   @Param("startDate") Date startDate, @Param("endDate") Date endDate,
                    @Param("phoneTempIds")String phoneTempIds, @Param("messTempIds") String messTempIds,
                    @Param("target")Integer target);

    int createSingleTask(@Param("userId") String userId,@Param("taskType") int taskType,
                         @Param("operationId") Integer operationId,@Param("taskStatus") Integer taskStatus,
                         @Param("taskListId") Integer taskListId,
                         @Param("startDate") Date startDate, @Param("endDate") Date endDate,
                         @Param("phoneTempIds")String phoneTempIds, @Param("messTempIds") String messTempIds,
                         @Param("target")Integer target);

    List<Task> getTasks(@Param("taskListId") Integer taskListId, @Param("operationId") Integer operationId,
                        @Param("offset") int offset, @Param("pageSize") int pageSize);

    int countTask(@Param("taskListId") Integer taskListId,@Param("operationId") Integer operationId);

    int done(@Param("taskId") String taskId);

    int getListId(@Param("taskId") String taskId);

    int startTasks(@Param("listId") List<Integer> ListId);

    int endTasks(@Param("listId") List<Integer> ListId);

    String getTaskByUser(@Param("userId") String userId);

    int getTargetById(@Param("taskId") String Id);
}