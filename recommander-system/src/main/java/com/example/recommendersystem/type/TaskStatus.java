package com.example.recommendersystem.type;

import java.util.Arrays;

public enum TaskStatus {
    not_started(1,"未开始"),
    doing(2,"进行中"),
    done(3,"已完成"),
    undone(4,"未完成");

    TaskStatus(int status, String name) {
        this.status = status;
        this.name = name;
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
        TaskStatus[] allValue = values();
        return Arrays.stream(allValue).mapToInt(TaskStatus::getStatus).toArray();
    }

    public static TaskStatus valueOf(int statusCode) {
        for (TaskStatus status : values()) {
            if (status.status == statusCode) {
                return status;
            }
        }
        throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
    }
}
