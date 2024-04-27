package com.grocery.enums;

public enum AuthorityType {
    ADMIN("Admin"),
    USER("User");

    private final String displayName;

    AuthorityType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getName() {
        return name();
    }
}
