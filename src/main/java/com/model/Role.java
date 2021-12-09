package com.model;

public enum Role {
    GUEST("guest"),
    USER("user"),
    ADMIN("admin");

    private final String value;

    Role(String value) {
        this.value = value;
    }
}
