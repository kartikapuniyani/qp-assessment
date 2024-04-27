package com.grocery.enums;

public enum RoleType {

    ADMIN(null,"Administrator"),
    USER(AuthorityType.USER, "User");

    private final AuthorityType authorityType;
    private final String displayName;

    RoleType(AuthorityType authorityType, String displayName) {
        this.displayName = displayName;
        this.authorityType = authorityType;
    }

    public String getName() {
        return this.name();
    }

    public interface Constants {

        String ADMIN = "ADMIN";
        String USER = "USER";

    }
}

