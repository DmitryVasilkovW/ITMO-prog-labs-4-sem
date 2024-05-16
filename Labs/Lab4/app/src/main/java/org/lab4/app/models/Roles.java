package org.lab4.app.models;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Roles
{
    None,
    Admin,
    Owner;

    @JsonCreator
    public static Roles fromString(String role)
    {
        return Roles.valueOf(role);
    }
}