package org.lab4.app.models;

import lombok.Data;

@Data
public class SigninRequest
{
    private String ownerName;
    private String password;
}
