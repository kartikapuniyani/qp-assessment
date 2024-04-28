package com.grocery.enums;

public enum RoleType {

    ADMIN("ADMIN"),
    USER("USER");

    private final String displayName;

    RoleType(String displayName) {
        this.displayName = displayName;
    }

    public String getName() {
        return this.name();
    }

    public String getDisplayName() {
        return this.displayName;
    }
}

