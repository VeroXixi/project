package com.example.recommendersystem.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TaskListVo {
    private Integer id;

    private String taskListName;

    private Integer userListId;

    private BigDecimal percent;

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

    public void setPercent(BigDecimal percent){
        this.percent = percent;
    }
}
