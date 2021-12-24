package com.model;

public enum UserPermission {
    USER_READ("user:watch"),
    USER_WRITE("user:write"),
    USER_MANAGE("user:manage");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
