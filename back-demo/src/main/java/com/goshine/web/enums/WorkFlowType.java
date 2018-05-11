package com.goshine.web.enums;

public enum WorkFlowType {
    LEAVE("请假申请", 1), PREPARATIONASSETS("资产申报", 2);

    private String name;
    private int status;

    WorkFlowType() {
    }

    WorkFlowType(String name, int status) {
        this.name = name;
        this.status = status;
    }

    public static String getName(int index) {
        for (WorkFlowType c : WorkFlowType.values()) {
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
