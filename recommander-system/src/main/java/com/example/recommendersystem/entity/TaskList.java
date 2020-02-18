package com.example.recommendersystem.entity;

import java.io.Serializable;
import java.util.Date;

public class TaskList implements Serializable {
    private Integer id;

    private String taskListName;

    private Integer userListId;

    private Date createTime;

    private Date beginTime;

    private Date endTime;

    private String operationIds;

    private Integer taskType;

    private Integer taskQuantity;

    private Integer doneQuantity;

    private Integer taskStatus;

    private String messageTemplateIds;

    private String phoneTemplateIds;

    private Integer target;

    private static final long serialVersionUID = 1L;

    public Integer getTarget(){return target;}

    public void setTarget(Integer target){this.target = target;}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTaskListName() {
        return taskListName;
    }

    public void setTaskListName(String taskListName) {
        this.taskListName = taskListName == null ? null : taskListName.trim();
    }

    public Integer getUserListId() {
        return userListId;
    }

    public void setUserListId(Integer userlistId) {
        this.userListId = userlistId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getOperationIds() {
        return operationIds;
    }

    public void setOperationIds(String operationId) {
        this.operationIds = operationId;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public Integer getTaskQuantity() {
        return taskQuantity;
    }

    public void setTaskQuantity(Integer taskQuantity) {
        this.taskQuantity = taskQuantity;
    }

    public Integer getDoneQuantity() {
        return doneQuantity;
    }

    public void setDoneQuantity(Integer doneQuantity) {
        this.doneQuantity = doneQuantity;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getMessageTemplateIds(){return messageTemplateIds;}
    public void setMessageTemplateIds(String messageTemplateIds){
        this.messageTemplateIds = messageTemplateIds;}

    public String getPhoneTemplateIds(){return phoneTemplateIds;}
    public void setPhoneTemplateIds(String phoneTemplateIds){
        this.phoneTemplateIds = phoneTemplateIds;}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", taskListName=").append(taskListName);
        sb.append(", userListId=").append(userListId);
        sb.append(", createTime=").append(createTime);
        sb.append(", beginTime=").append(beginTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", operationId=").append(operationIds);
        sb.append(", taskType=").append(taskType);
        sb.append(", taskQuantity=").append(taskQuantity);
        sb.append(", doneQuantity=").append(doneQuantity);
        sb.append(", taskStatus=").append(taskStatus);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}