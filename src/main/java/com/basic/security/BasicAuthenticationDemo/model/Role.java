package com.basic.security.BasicAuthenticationDemo.model;

import java.util.Set;

public enum Role {
    ADMIN(Set.of(Permissions.EMPLOYEE_READ,
            Permissions.EMPLOYEE_WRITE,
            Permissions.EMPLOYEE_DELETE)),
    USER(Set.of(Permissions.EMPLOYEE_READ));

   private final Set<Permissions> permissions;

    Role(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

    public Set<Permissions> getPermissions() {
        return permissions;
    }
}
