package com.example.recommendersystem.type;

import java.util.Arrays;

/**
 * 服务类型
 */
public enum ServiceType {
    A(1,"A"),
    B(2,"B"),
    C(3,"C"),
    nothing(4,"NOTHING");

    ServiceType(int status, String name) {
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
        ServiceType[] allValue = values();
        return Arrays.stream(allValue).mapToInt(ServiceType::getStatus).toArray();
    }

    public static ServiceType valueOf(int statusCode) {
        for (ServiceType status : values()) {
            if (status.status == statusCode) {
                return status;
            }
        }
        //如果枚举类里没有该实例，抛出异常
        throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
    }
}
