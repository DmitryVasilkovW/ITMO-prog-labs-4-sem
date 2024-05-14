package org.lab4.security;

import lombok.Data;

@Data
public class SignupRequest
{
    private String username;
    private String email;
    private String password;
}
