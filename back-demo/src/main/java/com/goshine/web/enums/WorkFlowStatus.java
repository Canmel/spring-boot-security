package com.goshine.web.enums;

public enum WorkFlowStatus {
    ACTIVE("正常", 1), DELETED("删除", 0);

    private String name;
    private int status;

    WorkFlowStatus() {
    }

    WorkFlowStatus(String name, int status) {
        this.name = name;
        this.status = status;
    }

    public static String getName(int index) {
        for (WorkFlowStatus c : WorkFlowStatus.values()) {
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
