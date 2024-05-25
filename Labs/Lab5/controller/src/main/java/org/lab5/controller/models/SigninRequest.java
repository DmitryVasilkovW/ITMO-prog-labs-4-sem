package org.lab5.controller.models;

import lombok.Data;

@Data
public class SigninRequest
{
    private String ownerName;
    private String password;
}
