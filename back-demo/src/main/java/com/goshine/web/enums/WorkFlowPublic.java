package com.goshine.web.enums;

public enum WorkFlowPublic {
    PUBLICED("已发布", 1), UNPUBLIC("未发布", 0);

    private String name;
    private int status;

    WorkFlowPublic() {
    }

    WorkFlowPublic(String name, int status) {
        this.name = name;
        this.status = status;
    }

    public static String getName(int index) {
        for (WorkFlowPublic c : WorkFlowPublic.values()) {
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
