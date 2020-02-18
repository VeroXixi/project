package com.example.recommendersystem.type;

import java.util.Arrays;

/**
 * 用户类型
 */
public enum UserType {
    new_user(3,"新用户"),
    active_user(2,"活跃用户"),
    vip(3,"老用户");

    UserType(int status, String name) {
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
        UserType[] allValue = values();
        return Arrays.stream(allValue).mapToInt(UserType::getStatus).toArray();
    }

    public static UserType valueOf(int statusCode) {
        for (UserType status : values()) {
            if (status.status == statusCode) {
                return status;
            }
        }
        throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
    }
}
