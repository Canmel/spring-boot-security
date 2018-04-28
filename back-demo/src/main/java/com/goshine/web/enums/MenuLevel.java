package com.goshine.web.enums;

public enum MenuLevel {
    ONELEVEL("一级菜单", 1), TWOLEVEL("二级菜单", 2);

    private String name;
    private int status;

    MenuLevel() {
    }

    MenuLevel(String name, int status) {
        this.name = name;
        this.status = status;
    }

    public static String getName(int index) {
        for (MenuLevel c : MenuLevel.values()) {
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
