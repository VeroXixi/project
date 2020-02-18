package com.example.recommendersystem.type;

import java.util.Arrays;

/**
 * 任务类型
 */

public enum TaskType {
    message(1,"短信")
    ,phone(2,"电话"),
    both(3,"短信和电话");

    TaskType(int status,String name){
        this.name = name;
        this.status = status;
    }

    private int status;
    private String name;

    public int getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public int[] getAllOptions() {
        TaskType[] allValue = values();
        return Arrays.stream(allValue).mapToInt(TaskType::getStatus).toArray();
    }

    public static TaskType valueOf(int statusCode) {
        for (TaskType status : values()) {
            if (status.status == statusCode) {
                return status;
            }
        }
        throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
    }
}
