package org.lab4.app.models;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SignupRequest
{
    private String ownerName;
    private String email;
    private LocalDate birthdate;
    private String password;
}
