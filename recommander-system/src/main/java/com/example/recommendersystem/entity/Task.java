package com.example.recommendersystem.entity;

import java.io.Serializable;
import java.util.Date;

public class Task implements Serializable {
    private String id;

    private String userId;

    private Integer taskType;

    private Date beginTime;

    private Date endTime;

    private Integer operationId;

    private Integer taskStatus;

    private String messageTemplateIds;

    private String phoneTemplateIds;

    private Integer taskListId;

    private Integer target;

    private static final long serialVersionUID = 1L;

    public Integer getTarget(){return target;}

    public void setTarget(Integer target){this.target = target;}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
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

    public Integer getOperationId() {
        return operationId;
    }

    public void setOperationId(Integer operationId) {
        this.operationId = operationId;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getMessageTemplateIds() {
        return messageTemplateIds;
    }

    public void setMessageTemplateIds(String messageTemplateId) {
        this.messageTemplateIds = messageTemplateId;
    }

    public String getPhoneTemplateIds() {
        return phoneTemplateIds;
    }

    public void setPhoneTemplateIds(String phoneTemplateId) {
        this.phoneTemplateIds = phoneTemplateId;
    }

    public Integer getTaskListId() {
        return taskListId;
    }

    public void setTaskListId(Integer taskListId) {
        this.taskListId = taskListId;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", taskType=").append(taskType);
        sb.append(", beginTime=").append(beginTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", operationId=").append(operationId);
        sb.append(", taskStatus=").append(taskStatus);
        sb.append(", messageTemplateId=").append(messageTemplateIds);
        sb.append(", phoneTemplateId=").append(phoneTemplateIds);
        sb.append(", taskListId=").append(taskListId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}