package com.goshine.web.enums;

public enum UserStatus {
    LOCKED("锁定", 2), ACTIVE("正常", 1), DELETED("删除", 0);

    private String name;
    private int status;

    UserStatus() {
    }

    UserStatus(String name, int status) {
        this.name = name;
        this.status = status;
    }

    public static String getName(int index) {
        for (UserStatus c : UserStatus.values()) {
            if (c.getStatus() == index) {
                return c.name;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
