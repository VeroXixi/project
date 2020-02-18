package com.example.recommendersystem.vo;

import com.example.recommendersystem.entity.Userc;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TaskVo {

    private String id;

    private String userId;

    private Integer taskType;

    private Date beginTime;

    private Date endTime;

    private Integer operationId;

    private Integer mtaskStatus;

    private List<String> messageTeplates;

    private List<String> phoneTemplates;

    private Integer taskListId;

    private Userc user;

    private List<String> messages;

    private List<String> phones;
    public void setUser(Userc user){
        this.user = user;
    }

    public void setMessages(List<String> messageTemplates){
        this.messageTeplates = messageTemplates;
    }

    public void setPhones(List<String> phoneTemplates){
        this.phoneTemplates = phoneTemplates;
    }
}
